package controllers

import javax.inject._
import play.api.Logging
import play.api.libs.ws.WSClient
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Random

@Singleton
class HomeController @Inject() (ws: WSClient, val controllerComponents: ControllerComponents)(implicit
    ec: ExecutionContext
) extends BaseController
    with Logging {

  def index(id: String): Action[AnyContent] =
    Action.async { implicit request: Request[AnyContent] =>
      val wait = Random.nextLong(5000)
      logger.info(s"$id send waitFor ${wait}ms")
      val request = ws.url(s"http://localhost:9001/wait-for/$id").addQueryStringParameters("millis" -> wait.toString)
      request.get().map { response =>
        logger.info(s"$id recv ${response.status} ${response.statusText}")
        Ok
      }
    }

  def waitFor(id: String, millis: Long): Action[AnyContent] =
    Action.async {
      logger.info(s"$id${" " * 9}waitFor ${millis}ms")
      Future(Thread.sleep(millis)).map(_ => logger.info(s"$id${" " * 17}${millis}ms done")).map(_ => Ok)
    }

}
