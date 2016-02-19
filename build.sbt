name := "jeff-daemon"

version := "0.1.1"

scalaVersion := "2.11.6"

resolvers ++= Seq(
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.2.1",
  "com.typesafe.akka" %% "akka-actor" % "2.4.2",
  "com.typesafe.akka" %% "akka-testkit" % "2.4.2" % "test",
  "org.scalatest" % "scalatest_2.11" % "2.2.6",
  "org.mockito" % "mockito-all" % "1.10.19" % "test"
)

//seq(ScctPlugin.instrumentSettings : _*)

//seq(com.github.theon.coveralls.CoverallsPlugin.coverallsSettings: _*)
