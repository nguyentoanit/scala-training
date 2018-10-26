name := """bbs"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.6"

libraryDependencies += guice
libraryDependencies += evolutions
libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.41"
libraryDependencies += specs2 % Test

libraryDependencies ++= Seq(
  "org.scalikejdbc" %% "scalikejdbc"                  % "3.3.0",
  "org.scalikejdbc" %% "scalikejdbc-test"   % "3.3.0"   % "test",
  "org.scalikejdbc" %% "scalikejdbc-config"           % "3.3.0",
  "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.6.0-scalikejdbc-3.3",
  "org.scalikejdbc" %% "scalikejdbc-play-dbapi-adapter" % "2.6.0-scalikejdbc-3.3"
)
import scalariform.formatter.preferences._
scalariformPreferences := scalariformPreferences.value
    .setPreference(AlignSingleLineCaseStatements, true)
    .setPreference(DoubleIndentConstructorArguments, true)
    .setPreference(DanglingCloseParenthesis, Preserve)
// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"
