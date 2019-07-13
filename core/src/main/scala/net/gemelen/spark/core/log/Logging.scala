package net.gemelen.spark.core.log

import zio.{ IO, UIO, ZIO }

trait Logger extends Serializable {
  val logger: Logger.Service[Any]
}

object Logger extends Serializable {
  trait Service[R] {
    def info(message: String): ZIO[R, Nothing, Unit]
    def warn(message: String): ZIO[R, Nothing, Unit]
    def error(message: String): ZIO[R, Nothing, Unit]
  }

  trait Live extends Logger {
    val logger: Service[Any] = new Service[Any] {
      final def info(message: String): UIO[Unit] = IO.effectTotal(Console.println("info: " + message))
      final def warn(message: String): UIO[Unit] = IO.effectTotal(Console.println("warn: " + message))
      final def error(message: String): UIO[Unit] = IO.effectTotal(Console.println("error: " + message))
    }
  }

  object Live extends Live
}
    
