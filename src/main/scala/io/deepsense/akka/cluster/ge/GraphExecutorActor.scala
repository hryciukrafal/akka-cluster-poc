package io.deepsense.akka.cluster.ge

import akka.actor.{Actor, ActorRef}
import akka.contrib.pattern.ClusterClient

class GraphExecutorActor extends Actor {

  val initialContacts = Set(
    context.system.actorSelection("akka.tcp://ClusterSystem@127.0.0.1:2551/user/receptionist"),
    context.system.actorSelection("akka.tcp://ClusterSystem@127.0.0.1:2552/user/receptionist"))

  val em = context.system.actorOf(ClusterClient.props(initialContacts))

  override def receive: Receive = {
    //internal message
    case p: (ActorRef, String) => {
      em ! ClusterClient.Send("/user/runningexperimentsactor", p, false)
    }
    case s: String => println(s"---- GE RECEIVED: $s----")
    case _ =>
  }

}
