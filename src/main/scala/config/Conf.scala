package config

import com.typesafe.config.ConfigFactory

object Conf {

  val config = ConfigFactory.load
  config.checkValid(ConfigFactory.defaultReference)

  val appHostName = config.getString("jeff-daemon.app.hostname")
  val appPort = config.getInt("jeff-daemon.app.port")
}
