package net.gemelen.spark.batch

import net.gemelen.kit.consul.ConsulClient
import net.gemelen.kit.log.LazyLogging
import net.gemelen.spark.core.SparkApplication
import org.apache.spark.sql.SparkSession
import com.typesafe.config.{Config, ConfigFactory, ConfigValueFactory}
import zio.{Task, ZIO}

object AppContext extends ConsulClient {

  /*
  * Application config from resource "application.conf"
  * with injected values from environment.
  */
  lazy val mainConfig: Task[Config] = Task.effect(
    ConfigFactory
      .load()
      .withFallback(ConfigFactory.systemEnvironment())
  )

  /*
  * Application config from Consul.
  */
  lazy val consulConfig: Task[Config] = mainConfig.flatMap { conf =>
    val consulHost: String = conf.getString("consul.host")
    val consulPath: String = conf.getString("consul.path")

    externalConfig(host = consulHost, path = consulPath)
  }

  /*
  * Merged configuration from all sources.
  */
  lazy val config: Task[Config] =
    for {
      mc <- mainConfig
      cc <- consulConfig
    } yield ( mc.withFallback(cc) )

  class BatchApplication extends SparkApplication with LazyLogging {

    lazy val session: Task[SparkSession] = mainConfig.flatMap { conf =>
      ZIO.effect(
        SparkSession
          .builder()
          .master("local[4]")
          .appName(conf.getString("application.name"))
          .getOrCreate()
        )
    }

    override def sparkApp: ZIO[Any, Nothing, Int] = {

      for {
        _ <- logger.info("start")
        count <- session.map { _.range(1, 100).count() } <> ZIO.succeed(0L)
        _ <- session.map(_.close) <> ZIO.succeed( SparkApplication.ExitCode.Error.code )
        _ <- logger.error("error!", new Exception("exception message"))
        _ <- logger.info("end")
        _ <- logger.warn(s"count: $count")
      } yield ( SparkApplication.ExitCode.Success.code ) 

    }

  }
}

object BatchApplication extends AppContext.BatchApplication

