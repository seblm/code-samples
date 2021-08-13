package observability

import akka.stream.Materializer
import io.opentelemetry.OpenTelemetry
import io.opentelemetry.trace.Span.Kind.SERVER
import io.opentelemetry.trace.StatusCanonicalCode.{ERROR, UNSET}
import io.opentelemetry.trace.Tracer
import io.opentelemetry.trace.attributes.SemanticAttributes
import javax.inject.Inject
import observability.OpenTelemetryFilter.{akkaHttpTracer, removeUselessPrefix, statusFromHttpStatus}
import play.api.Logging
import play.api.http.HeaderNames.{USER_AGENT, X_FORWARDED_FOR}
import play.api.mvc.{Filter, RequestHeader, Result}
import play.api.routing.Router

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try

class OpenTelemetryFilter @Inject() (implicit val mat: Materializer, ec: ExecutionContext) extends Filter with Logging {

  override def apply(next: RequestHeader => Future[Result])(request: RequestHeader): Future[Result] = {
    val maybeRoute: Option[String] = Try(request.attrs(Router.Attrs.HandlerDef)).toOption.map { handlerDef =>
      s"${handlerDef.verb} ${removeUselessPrefix(handlerDef.path)}"
    }

    maybeRoute match {
      case None =>
        next(request)
      case Some(route) =>
        val scheme = s"http${if (request.secure) "s" else ""}"
        val httpURL = s"$scheme://${request.host}${request.target.uriString}"
        val spanBuilder = akkaHttpTracer
          .spanBuilder(route)
          .setSpanKind(SERVER)
          .setAttribute(SemanticAttributes.HTTP_METHOD, request.method)
          .setAttribute(SemanticAttributes.HTTP_SCHEME, scheme)
          .setAttribute(SemanticAttributes.HTTP_URL, httpURL)
          .setAttribute(SemanticAttributes.HTTP_FLAVOR, request.version)
          .setAttribute(SemanticAttributes.NET_PEER_IP, request.remoteAddress)

        request.headers
          .get(X_FORWARDED_FOR)
          .flatMap(_.split(", ").headOption)
          .foreach(`X-Forwarded-For` =>
            spanBuilder.setAttribute[String](SemanticAttributes.HTTP_CLIENT_IP, `X-Forwarded-For`)
          )
        request.headers
          .get(USER_AGENT)
          .foreach(user_agent => spanBuilder.setAttribute[String](SemanticAttributes.HTTP_USER_AGENT, user_agent))

        val span = spanBuilder.startSpan()

        val scope = akkaHttpTracer.withSpan(span)

        next(request)
          .transform(
            result => {
              scope.close()
              span.setAttribute(SemanticAttributes.HTTP_STATUS_CODE, result.header.status)
              span.setStatus(statusFromHttpStatus(result.header.status))
              logger.info(s"span.end(): $span")
              span.end()
              result
            },
            throwable => {
              scope.close()
              span.recordException(throwable)
              span.setStatus(ERROR)
              span.end()
              throwable
            }
          )
    }
  }

}

object OpenTelemetryFilter {

  private val akkaHttpTracer: Tracer = OpenTelemetry.getTracerProvider.get("io.opentelemetry.auto.akka-http-10.0")

  private def removeUselessPrefix(path: String) = path.replaceAll("\\$([\\w]+)<\\[\\^/]\\+>", ":$1")

  /**
    * @see https://github.com/open-telemetry/opentelemetry-java-instrumentation/blob/master/instrumentation-api/src/main/java/io/opentelemetry/instrumentation/api/decorator/HttpStatusConverter.java
    */
  private def statusFromHttpStatus(status: Int) = if (status >= 100 && status < 400) UNSET else ERROR

}
