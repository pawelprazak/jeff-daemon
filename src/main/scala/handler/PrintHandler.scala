package handler

import akka.actor._
import akka.util.ByteString
import akka.io.Tcp.Write

object PrintHandlerProps extends HandlerProps {
  def props(connection: ActorRef) = Props(classOf[PrintHandler], connection)
}

class PrintHandler(connection: ActorRef) extends Handler(connection) {

  /**
   * OK and print incoming message.
   */
  def received(data: String) = {
    println(s"Received: $data")
    connection ! Write(ByteString("OK\n"))
  }
}