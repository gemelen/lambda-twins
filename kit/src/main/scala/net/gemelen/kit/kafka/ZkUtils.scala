package net.gemelen.kit.kafka

import kafka.zk.KafkaZkClient
import org.apache.kafka.common.utils.Time
import zio.{ Managed, Task }

import scala.concurrent.duration.Duration
import scala.concurrent.duration._

object ZkUtils {

  /**
  * Obtains list of brokers in cluster in format "host:port"
  * @param    zkConnectionString    - connection string of Zookeeper cluster (or at least its quorum)
  * @param    connectionTimeout     - timeout for Zookeeper cluster connection [optional]
  * @param    sessionTimeout        - timeout for session to Zookeeper         [optional]
  * @param    requestsOnWire        - maximum of requests in in-flight status  [optional]
  * @returns  Task[List[String]]
  */
  def brokersConnectionString(
        zkConnectionString: String,
        connectionTimeout: Duration = 10.seconds,
        sessionTimeout: Duration = 1.minute,
        requestsOnWire: Integer = Integer.MAX_VALUE): Task[List[String]] = {

    val zkClient = Managed.make (
      Task.effect(
        KafkaZkClient(
          zkConnectionString,
          isSecure = false,
          connectionTimeoutMs = connectionTimeout.toMillis.toInt,
          sessionTimeoutMs = sessionTimeout.toMillis.toInt,
          maxInFlightRequests = requestsOnWire,
          time = Time.SYSTEM
        )
      )
    ) (client => Task.effectTotal(client.close()))

    zkClient.use { client =>
      Task.effect(
        client
          .getAllBrokersInCluster
          .flatMap(_.endPoints)
          .map { endpoint => s"${endpoint.host}:${endpoint.port}" }
          .toList
      )
    }
  }

}

