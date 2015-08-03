package io.deepsense.akka.cluster

import akka.actor.{Props, ActorSystem}
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
    val system = ActorSystem("EMCluster", config)
    // Create an actor that handles cluster domain events
    system.actorOf(Props[RunningExperimentsActor], name = "runningexperimentsactor")
  }

}
