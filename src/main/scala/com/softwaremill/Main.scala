package com.softwaremill

import org.apache.pekko.actor.ActorSystem
import org.apache.pekko.http.scaladsl.Http
import sttp.tapir.server.pekkohttp.PekkoHttpServerInterpreter

import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.StdIn

@main def run(): Unit =
  implicit val actorSystem: ActorSystem = ActorSystem()

  val route = PekkoHttpServerInterpreter().toRoute(Endpoints.all)

  val port = sys.env.get("http.port").map(_.toInt).getOrElse(8080)

  val bindingFuture = Http()
    .newServerAt("localhost", port)
    .bindFlow(route)
    .map { binding =>
      println(s"Server started at http://localhost:${binding.localAddress.getPort}. Press ENTER key to exit.")
      binding
    }

  StdIn.readLine()

  bindingFuture.flatMap(_.unbind()).onComplete(_ => actorSystem.terminate())
