organization := "com.ivan.nikolov"

name := "scala_test"

scalaVersion := "2.12.5"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

javaOptions ++= Seq("-target", "1.8", "-source", "1.8")

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-swing" % "2.0.3",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test"
)