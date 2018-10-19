name := """bbs"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.6"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
libraryDependencies += evolutions
libraryDependencies += jdbc
libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.41"

libraryDependencies ++= Seq(
  "com.h2database"  %  "h2"                           % "1.4.197", // your jdbc driver here
  "org.scalikejdbc" %% "scalikejdbc"                  % "3.3.0",
  "org.scalikejdbc" %% "scalikejdbc-config"           % "3.3.0",
  "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.6.0-scalikejdbc-3.3",
  "org.scalikejdbc" %% "scalikejdbc-play-dbapi-adapter" % "2.6.0-scalikejdbc-3.3"
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"
