package handler

import akka.actor.ActorSystem
import akka.io.Tcp.{Received, _}
import akka.testkit.{ImplicitSender, TestKit}
import akka.util.ByteString
import org.scalatest.{WordSpecLike, BeforeAndAfterAll, MustMatchers}

class PrintHandlerSpec(_system: ActorSystem)
  extends TestKit(_system)
    with ImplicitSender
    with WordSpecLike
    with MustMatchers
    with BeforeAndAfterAll {

  def this() = this(ActorSystem("PrintHandlerSpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "A PrintHandler" must {

    "OK the message" in {
      val handler = system.actorOf(PrintHandlerProps.props(testActor))
      val data = ByteString("hello")
      handler ! Received(data)
      expectMsg(Write(ByteString("OK\n")))
    }

    "send Close message to connection if close message is received" in {
      val handler = system.actorOf(PrintHandlerProps.props(testActor))
      watch(handler)
      val data = ByteString("close")
      handler ! Received(data)
      expectMsg(Close)
    }

    "close itself if ErrorClosed is received" in {
      val handler = system.actorOf(PrintHandlerProps.props(testActor))
      watch(handler)
      handler ! ErrorClosed
      expectTerminated(handler)
    }

    "close itself if Closed is received" in {
      val handler = system.actorOf(PrintHandlerProps.props(testActor))
      watch(handler)
      handler ! Closed
      expectTerminated(handler)
    }

    "close itself if peer closed" in {
      val handler = system.actorOf(PrintHandlerProps.props(testActor))
      watch(handler)
      handler ! PeerClosed
      expectTerminated(handler)
    }

    "close itself if confirmed closed" in {
      val handler = system.actorOf(PrintHandlerProps.props(testActor))
      watch(handler)
      handler ! ConfirmedClosed
      expectTerminated(handler)
    }

    "close itself if aborted" in {
      val handler = system.actorOf(PrintHandlerProps.props(testActor))
      watch(handler)
      handler ! Aborted
      expectTerminated(handler)
    }

  }

}
