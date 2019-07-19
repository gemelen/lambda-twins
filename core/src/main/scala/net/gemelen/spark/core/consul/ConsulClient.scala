package net.gemelen.spark.core.consul

import com.google.common.net.HostAndPort
import com.orbitz.consul.Consul
import com.typesafe.config.{Config, ConfigFactory}
import zio.{ Managed, Task, UIO }
import java.{util => ju}

trait ConsulClient {

  private def retrieve(host: String, port: Int, path: String): Task[String] = {
    val hp = HostAndPort.fromParts(host, port)
    val consul = Managed.make( Task.effect(Consul.builder().withHostAndPort(hp).build()) )(c => UIO.effectTotal(c.destroy()))
    val rawConfig: Task[ju.Optional[String]] = consul.use(client => Task.effect(client.keyValueClient().getValueAsString(path)))
    rawConfig.map(_.get())
  }

  /**
  *   Retrives typesafe Config string representation from KV storage in Consul
  *   and parses it into Config object
  *   @param    host   - host to connect Consul client to
  *   @param    port   - port [optional]
  *   @param    path   - path to key in Consul's KV storage
  *   @return   ZIO[Any, Throwable, Config]
  * */
  def externalConfig(host: String, port: Int = Consul.DEFAULT_HTTP_PORT, path: String): Task[Config] = 
    retrieve(host, port, path) map ConfigFactory.parseString

}

