package io.deepsense.akka.cluster.ge

import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory

object GraphExecutor {

  def main(args: Array[String]): Unit = {
    if (args.isEmpty) {
      println("Provide port number")
      sys.exit(1)
    }

    val port = args(0).toInt

    val config = ConfigFactory.parseString("akka.remote.netty.tcp.port=" + port).
      withFallback(ConfigFactory.load("client.conf"))

    // Create an Akka system
    val system = ActorSystem("GraphExecutor", config)

    val geReceiver = system.actorOf(Props[GraphExecutorActor], "gereceiver")

    var ok = true
    while (ok) {
      println("=== Enter command: ")
      val ln = scala.io.StdIn.readLine()
      ok = ln != null
      if (ok) {
        println(s"- Sending: $ln")
        geReceiver ! (geReceiver, ln)
      }
    }
  }
}
