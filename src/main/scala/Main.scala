import akka.actor.{ Props, ActorSystem }
import handler._
import server.TcpServer

object Main extends App {
  val system = ActorSystem("server")
  val service = system.actorOf(TcpServer.props(PrintHandlerProps), "ServerActor")
}