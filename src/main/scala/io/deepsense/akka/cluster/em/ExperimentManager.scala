package io.deepsense.akka.cluster.em

import akka.actor.{ActorSystem, Props}
import akka.contrib.pattern.ClusterReceptionistExtension
import com.typesafe.config.ConfigFactory

object ExperimentManager {

  def main(args: Array[String]): Unit = {
    if (args.isEmpty) {
      println("Provide port number")
      sys.exit(1)
    }

    val port = args(0).toInt

    val config = ConfigFactory.parseString("akka.remote.netty.tcp.port=" + port).
      withFallback(ConfigFactory.load())

    // Create an Akka system
    val system = ActorSystem("ClusterSystem", config)
    // Create an actor that handles cluster domain events
    val actor = system.actorOf(Props[GraphExecutorClientActor], name = "runningexperimentsactor")
    ClusterReceptionistExtension(system).registerService(actor)

    var ok = true
    while (ok) {
      println("=== Enter command: ")
      val ln = scala.io.StdIn.readLine()
      ok = ln != null
      if (ok) {
        println(s"- Sending to GE: $ln")
        val path = PathDao.getRunningExperimentAddress()
        val geActor = system.actorSelection(path)
        geActor ! ln
      }
    }
  }

}
