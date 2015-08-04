package io.deepsense.akka.cluster.em

object PathDao {

  val file: String = "/tmp/path.txt"

  def saveRunningExperimentAddress(path: String): Unit = {
    import java.io._
    printToFile(new File(file)) { p =>
      p.println(path)
    }
  }

  def getRunningExperimentAddress(): String = {
    scala.io.Source.fromFile(file).getLines().next()
  }

  private def printToFile(f: java.io.File)(op: java.io.PrintWriter => Unit) {
    val p = new java.io.PrintWriter(f)
    try { op(p) } finally { p.close() }
  }
}
