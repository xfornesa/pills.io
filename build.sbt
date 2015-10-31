name := """pills.io"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs
)

libraryDependencies += "info.cukes" % "cucumber-java" % "1.2.4" % "test"
libraryDependencies += "info.cukes" % "cucumber-junit" % "1.2.4" % "test"
libraryDependencies += "org.mockito" % "mockito-core" % "1.+" % "test"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
