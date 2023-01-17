package name.lemerdy.sebastian.future

import java.time.Instant

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._
import scala.util.Random

/**
  * Simple application to implements a first async call that is either a success or an async call to a second one.
  */
object FutureExperiment:

  private val random: Random = Random

  private def log(id: String, message: String): Unit =
    println(s"${Instant.now} $id # $message")

  private def waitFor(id: String): Future[Int] =
    val millisToSleep = (random.nextInt(5) + 1) * 1000
    log(id, s"waiting for ${millisToSleep}ms")
    if random.nextBoolean() then
      Future.successful {
        Thread.sleep(millisToSleep)
        val result = random.nextInt
        log(id, s"success of $result")
        result
      }
    else
      Future.failed {
        Thread.sleep(millisToSleep)
        log(id, "failure")
        new Exception(s"$id failed")
      }

  @main def run(): Unit =

    log("main  ", "start")

    val eventualResult = waitFor("first ").recoverWith { case _ => waitFor("second") }(ExecutionContext.global)

    log("main  ", "waiting for computation")

    val value = Await.result(eventualResult, 10.seconds)

    log("main  ", s"$value")
