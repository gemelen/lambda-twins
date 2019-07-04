package net.gemelen.spark.core

import org.apache.spark.sql.SparkSession
import com.typesafe.config.Config
import zio.{Task, ZIO}

trait SparkEnvironment {

  def session(config: Config): Task[SparkSession]

}

