val tapirVersion = "1.10.15"

lazy val rootProject = (project in file(".")).settings(
  Seq(
    name := "responsible-hummingbird",
    version := "0.1.0-SNAPSHOT",
    organization := "com.softwaremill",
    scalaVersion := "3.4.2",
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.apispec" %% "openapi-circe-yaml" % "0.11.3",
      "com.softwaremill.sttp.tapir" %% "tapir-core" % tapirVersion,
      "com.softwaremill.sttp.tapir" %% "tapir-json-circe" % tapirVersion,
      "com.softwaremill.sttp.tapir" %% "tapir-openapi-docs" % tapirVersion,
      "io.circe" %% "circe-generic" % "0.14.9",
    )
  )
)
