name := "akka-cluster-poc"

version := "0.1.0"

scalaVersion := "2.11.7"

val akkaVersion = "2.3.12"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-contrib" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster" % akkaVersion
)

