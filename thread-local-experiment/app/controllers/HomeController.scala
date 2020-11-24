package controllers

import javax.inject._
import play.api.Logging
import play.api.libs.ws.WSClient
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Random

@Singleton
class HomeController @Inject()(ws: WSClient, val controllerComponents: ControllerComponents)(implicit ec: ExecutionContext) extends BaseController with Logging {

  def index(id: Option[String]): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val idAsString = id.map(id => s"$id ").getOrElse("")
    val wait = Random.nextLong(5000)
    logger.info(s"${idAsString}send waitFor ${wait}ms")
    val request = ws.url("http://localhost:9000/waitFor").addQueryStringParameters("millis" -> wait.toString)
    request.get().map { response =>
      logger.info(s"${idAsString}recv ${response.status} ${response.statusText}")
      Ok
    }
  }

  def waitFor(millis: Long): Action[AnyContent] = Action.async {
    logger.info(" " * 9 + s"waitFor ${millis}ms")
    Future(Thread.sleep(millis)).map(_ => logger.info(" " * 17 + s"${millis}ms done")).map(_ => Ok)
  }

}
