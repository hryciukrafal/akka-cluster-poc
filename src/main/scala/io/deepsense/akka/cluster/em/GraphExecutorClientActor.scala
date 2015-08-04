package io.deepsense.akka.cluster.em

import akka.actor.{Actor, ActorRef}

class GraphExecutorClientActor extends Actor {
  override def receive: Receive = {
    case p: (ActorRef, String) => {
      println(s"=====================================        Received ${p._2}        ============================================")
      PathDao.saveRunningExperimentAddress(p._1.path.toSerializationFormat)
      sender() ! s"EM received ${p._2}"
    }
    case _ =>
  }
}
