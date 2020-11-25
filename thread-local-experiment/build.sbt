name := "thread-local-experiment"
organization := "name.lemerdy.sebastian"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.3"

libraryDependencies += guice
libraryDependencies += ws

libraryDependencies += "io.opentelemetry" % "opentelemetry-api" % "0.9.1"
