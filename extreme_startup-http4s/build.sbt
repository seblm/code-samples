val Http4sVersion = "0.23.23"
val CirceVersion = "0.14.6"
val MunitVersion = "0.7.29"
val LogbackVersion = "1.4.11"
val MunitCatsEffectVersion = "1.0.7"

lazy val root = (project in file("."))
  .settings(
    organization := "name.lemerdy.sebastian",
    name := "extreme_startup-http4s",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "3.3.0",
    libraryDependencies ++= Seq(
      "co.fs2"          %% "fs2-io"              % "3.7.0",
      "com.comcast"     %% "ip4s-core"           % "3.3.0",
      "io.circe"        %% "circe-core"          % CirceVersion,
      "org.http4s"      %% "http4s-circe"        % Http4sVersion,
      "org.http4s"      %% "http4s-client"       % Http4sVersion,
      "org.http4s"      %% "http4s-core"         % Http4sVersion,
      "org.http4s"      %% "http4s-dsl"          % Http4sVersion,
      "org.http4s"      %% "http4s-ember-client" % Http4sVersion,
      "org.http4s"      %% "http4s-ember-server" % Http4sVersion,
      "org.http4s"      %% "http4s-server"       % Http4sVersion,
      "org.typelevel"   %% "case-insensitive"    % "1.4.0",
      "org.typelevel"   %% "cats-core"           % "2.9.0",
      "org.typelevel"   %% "cats-effect"         % "3.5.0",
      "org.typelevel"   %% "cats-effect-kernel"  % "3.5.1",
      "org.scalameta"   %% "munit"               % MunitVersion           % Test,
      "org.typelevel"   %% "munit-cats-effect-3" % MunitCatsEffectVersion % Test,
      "ch.qos.logback"  %  "logback-classic"     % LogbackVersion         % Runtime,
    ),
    assembly / assemblyMergeStrategy := {
      case "module-info.class" => MergeStrategy.discard
      case x => (assembly / assemblyMergeStrategy).value.apply(x)
    }
  )
