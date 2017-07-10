lazy val scala = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "name.lemerdy.sebastian",
      scalaVersion := "2.12.2",
      version := "0.1.0-SNAPSHOT"
    ))
  )
