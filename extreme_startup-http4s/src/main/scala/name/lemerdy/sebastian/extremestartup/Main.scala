package name.lemerdy.sebastian.extremestartup

import cats.effect.{IO, IOApp}

object Main extends IOApp.Simple:
  val run: IO[Nothing] = ExtremeStartupServer.run[IO]
