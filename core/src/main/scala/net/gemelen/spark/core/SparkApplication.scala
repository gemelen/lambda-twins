package net.gemelen.spark.core

import org.apache.spark.sql.SparkSession
import zio.{App, Task, ZIO}

abstract class SparkApplication extends zio.App {

  def sparkApp: ZIO[Environment, Nothing, Int]

  import SparkApplication._
  def run(args: List[String]): ZIO[Environment, Nothing, Int] =
    sparkApp.fold(
      error => ErrorReturnCode,
      success => SuccessReturnCode
    )

}

object SparkApplication {

  val SuccessReturnCode = 0
  val ErrorReturnCode = 1

}

