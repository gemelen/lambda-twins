package net.gemelen.spark.core

import org.apache.spark.sql.SparkSession
import zio.{App, ZIO, ZEnv}

abstract class SparkApplication extends zio.App {

  def sparkApp: ZIO[Any, Throwable, Int]

  import SparkApplication.ExitCode._
  def run(args: List[String]): ZIO[ZEnv, Nothing, Int] =
    sparkApp
    .fold( _ => Error.code , _ => Success.code )

}

object SparkApplication {

  sealed trait ExitCode
  object ExitCode {
    final case object Success extends ExitCode {
      val code: Int = 0
    }
    final case object Error extends ExitCode {
      val code: Int = 1
    }
  }

}

