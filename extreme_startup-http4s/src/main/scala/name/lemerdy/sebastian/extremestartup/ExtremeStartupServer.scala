package name.lemerdy.sebastian.extremestartup

import cats.effect.Async
import com.comcast.ip4s.*
import fs2.io.net.Network
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.implicits.*
import org.http4s.server.middleware.Logger

object ExtremeStartupServer:

  def run[F[_]: Async: Network]: F[Nothing] = {
    val httpApp = ExtremeStartupRoutes.extremeStartupRoutes[F].orNotFound
    val finalHttpApp = Logger.httpApp(true, true)(httpApp)
    for {
      _ <- EmberServerBuilder.default[F].withHost(ipv4"0.0.0.0").withPort(port"8080").withHttpApp(finalHttpApp).build
    } yield ()
  }.useForever
