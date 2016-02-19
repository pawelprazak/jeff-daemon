package server

import java.net.InetSocketAddress

import akka.actor.ActorSystem
import akka.io.Tcp.{Connected, Register}
import akka.testkit.{ImplicitSender, TestKit}
import handler.EchoHandlerProps
import org.scalatest.{BeforeAndAfterAll, MustMatchers, WordSpecLike}

class TcpServerSpec(_system: ActorSystem)
  extends TestKit(_system)
    with ImplicitSender
    with WordSpecLike
    with MustMatchers
    with BeforeAndAfterAll {

  def this() = this(ActorSystem("TcpServerSpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "A TcpServer actor" must {

    "register a handler when a client connected" in {
      val server = system.actorOf(TcpServer.props(EchoHandlerProps), "ServerActor")
      server ! Connected(new InetSocketAddress(5555),
        new InetSocketAddress(9000))
      expectMsgPF() { case _: Register => }
    }

  }
}