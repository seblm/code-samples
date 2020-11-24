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
    Future(id.map(id => s"$id ").getOrElse(""))
      .map { id =>
        logger.info(s"${id}start")
        id
      }
      .map(id => (id, Random.nextLong(5000)))
      .map { case (id, wait) =>
        logger.info(s"${id}wait ${wait}ms")
        (id, wait)
      }
      .flatMap { case (id, wait) =>
        logger.info(s"${id}flatMap ws")
        ws.url("http://localhost:9000/waitFor")
          .addQueryStringParameters("millis" -> wait.toString)
          .get()
          .map(response => {
            logger.info(s"${id}status ${response.status} ${response.statusText}")
            id
          })
      }
      .map(id => logger.info(s"${id}end"))
      .map(_ => Ok)
  }

  def waitFor(millis: Long): Action[AnyContent] = Action.async {
    logger.info(" " * 30 + s"waitFor $millis")
    Future(Thread.sleep(millis)).map(_ => logger.info(" " * 30 + "done")).map(_ => Ok)
  }

}
