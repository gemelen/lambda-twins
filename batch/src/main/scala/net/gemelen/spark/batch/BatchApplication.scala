package net.gemelen.spark.batch

import net.gemelen.spark.core.consul.ConsulClient
import net.gemelen.spark.core.log.LazyLogging
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
      .withValue("environment", ConfigValueFactory.fromAnyRef(sys.env("ENVIRONMENT")))
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

    lazy val session: ZIO[Any, Nothing, SparkSession] = ZIO.succeed(
      SparkSession
        .builder()
        .master("local")
        .getOrCreate()
    )

    override def sparkApp: ZIO[Any, Nothing, Int] = {

      for {
        _ <- logger.info("start")
        count <- session.map { s =>
          s.range(1, 100).count()
        }
        _ <- session.map(_.close)
        _ <- logger.error("error!", new Exception("exception message"))
        _ <- logger.info("end")
        _ <- logger.warn(s"count: $count")
      } yield ( SparkApplication.ExitCode.Success.code )
    }

  }
}

object BatchApplication extends AppContext.BatchApplication

