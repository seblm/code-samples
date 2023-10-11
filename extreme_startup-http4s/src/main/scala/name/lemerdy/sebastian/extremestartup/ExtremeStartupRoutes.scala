package name.lemerdy.sebastian.extremestartup

import cats.effect.Sync
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.log4s.{Logger, getLogger}

object ExtremeStartupRoutes:

  private val logger: Logger = getLogger(getClass)

  def extremeStartupRoutes[F[_]: Sync]: HttpRoutes[F] =
    val dsl = new Http4sDsl[F] {}
    import dsl.*
    object Query extends QueryParamDecoderMatcher[String]("q")
    HttpRoutes.of[F] { case GET -> Root :? Query(query) =>
      logger.info(query)
      val response = ExtremeStartupResolver
        .resolve(query.trim)
        .fold {
          logger.error(query)
          "ok"
        } { response =>
          logger.info(response)
          response
        }
      Ok(response)
    }
