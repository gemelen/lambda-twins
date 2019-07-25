package net.gemelen.kit.log

import com.typesafe.scalalogging.{Logger => OriginalLogger}
import org.slf4j.{LoggerFactory, Marker}
import zio.{ IO, UIO, ZIO }

trait Logging[R] extends Serializable {
    // Error
    def error(message: String): ZIO[R, Nothing, Unit]
    def error(message: String, cause: Throwable): ZIO[R, Nothing, Unit]
    def error(message: String, args: Any*): ZIO[R, Nothing, Unit]
    def error(marker: Marker, message: String): ZIO[R, Nothing, Unit]
    def error(marker: Marker, message: String, cause: Throwable): ZIO[R, Nothing, Unit]
    def error(marker: Marker, message: String, args: Any*): ZIO[R, Nothing, Unit]

    // Warn
    def warn(message: String): ZIO[R, Nothing, Unit]
    def warn(message: String, cause: Throwable): ZIO[R, Nothing, Unit]
    def warn(message: String, args: Any*): ZIO[R, Nothing, Unit]
    def warn(marker: Marker, message: String): ZIO[R, Nothing, Unit]
    def warn(marker: Marker, message: String, cause: Throwable): ZIO[R, Nothing, Unit]
    def warn(marker: Marker, message: String, args: Any*): ZIO[R, Nothing, Unit]

    // Info
    def info(message: String): ZIO[R, Nothing, Unit]
    def info(message: String, cause: Throwable): ZIO[R, Nothing, Unit]
    def info(message: String, args: Any*): ZIO[R, Nothing, Unit]
    def info(marker: Marker, message: String): ZIO[R, Nothing, Unit]
    def info(marker: Marker, message: String, cause: Throwable): ZIO[R, Nothing, Unit]
    def info(marker: Marker, message: String, args: Any*): ZIO[R, Nothing, Unit]

    // Debug
    def debug(message: String): ZIO[R, Nothing, Unit]
    def debug(message: String, cause: Throwable): ZIO[R, Nothing, Unit]
    def debug(message: String, args: Any*): ZIO[R, Nothing, Unit]
    def debug(marker: Marker, message: String): ZIO[R, Nothing, Unit]
    def debug(marker: Marker, message: String, cause: Throwable): ZIO[R, Nothing, Unit]
    def debug(marker: Marker, message: String, args: Any*): ZIO[R, Nothing, Unit]

    // Trace
    def trace(message: String): ZIO[R, Nothing, Unit]
    def trace(message: String, cause: Throwable): ZIO[R, Nothing, Unit]
    def trace(message: String, args: Any*): ZIO[R, Nothing, Unit]
    def trace(marker: Marker, message: String): ZIO[R, Nothing, Unit]
    def trace(marker: Marker, message: String, cause: Throwable): ZIO[R, Nothing, Unit]
    def trace(marker: Marker, message: String, args: Any*): ZIO[R, Nothing, Unit]
}

trait LazyLogging {

  @transient
  private lazy val log: OriginalLogger = OriginalLogger(LoggerFactory.getLogger(getClass.getName))

  @transient
  protected lazy val logger = new Logging[Any] {
    // Error
    final def error(message: String): UIO[Unit] = IO.effectTotal(log.error(message))
    final def error(message: String, cause: Throwable): UIO[Unit] = IO.effectTotal(log.error(message, cause))
    final def error(message: String, args: Any*): UIO[Unit] = IO.effectTotal(log.error(message, args))
    final def error(marker: Marker, message: String): UIO[Unit] = IO.effectTotal(log.error(marker, message))
    final def error(marker: Marker, message: String, cause: Throwable): UIO[Unit] = IO.effectTotal(log.error(marker, message, cause))
    final def error(marker: Marker, message: String, args: Any*): UIO[Unit] = IO.effectTotal(log.error(marker, message, args))

    // Warn
    final def warn(message: String): UIO[Unit] = IO.effectTotal(log.warn(message))
    final def warn(message: String, cause: Throwable): UIO[Unit] = IO.effectTotal(log.warn(message, cause))
    final def warn(message: String, args: Any*): UIO[Unit] = IO.effectTotal(log.warn(message, args))
    final def warn(marker: Marker, message: String): UIO[Unit] = IO.effectTotal(log.warn(marker, message))
    final def warn(marker: Marker, message: String, cause: Throwable): UIO[Unit] = IO.effectTotal(log.warn(marker, message, cause))
    final def warn(marker: Marker, message: String, args: Any*): UIO[Unit] = IO.effectTotal(log.warn(marker, message, args))

    // Info
    final def info(message: String): UIO[Unit] = IO.effectTotal(log.info(message))
    final def info(message: String, cause: Throwable): UIO[Unit] = IO.effectTotal(log.info(message, cause))
    final def info(message: String, args: Any*): UIO[Unit] = IO.effectTotal(log.info(message, args))
    final def info(marker: Marker, message: String): UIO[Unit] = IO.effectTotal(log.info(marker, message))
    final def info(marker: Marker, message: String, cause: Throwable): UIO[Unit] = IO.effectTotal(log.info(marker, message, cause))
    final def info(marker: Marker, message: String, args: Any*): UIO[Unit] = IO.effectTotal(log.info(marker, message, args))

    // Debug
    final def debug(message: String): UIO[Unit] = IO.effectTotal(log.debug(message))
    final def debug(message: String, cause: Throwable): UIO[Unit] = IO.effectTotal(log.debug(message, cause))
    final def debug(message: String, args: Any*): UIO[Unit] = IO.effectTotal(log.debug(message, args))
    final def debug(marker: Marker, message: String): UIO[Unit] = IO.effectTotal(log.debug(marker, message))
    final def debug(marker: Marker, message: String, cause: Throwable): UIO[Unit] = IO.effectTotal(log.debug(marker, message, cause))
    final def debug(marker: Marker, message: String, args: Any*): UIO[Unit] = IO.effectTotal(log.debug(marker, message, args))

    // Trace
    final def trace(message: String): UIO[Unit] = IO.effectTotal(log.trace(message))
    final def trace(message: String, cause: Throwable): UIO[Unit] = IO.effectTotal(log.trace(message, cause))
    final def trace(message: String, args: Any*): UIO[Unit] = IO.effectTotal(log.trace(message, args))
    final def trace(marker: Marker, message: String): UIO[Unit] = IO.effectTotal(log.trace(marker, message))
    final def trace(marker: Marker, message: String, cause: Throwable): UIO[Unit] = IO.effectTotal(log.trace(marker, message, cause))
    final def trace(marker: Marker, message: String, args: Any*): UIO[Unit] = IO.effectTotal(log.trace(marker, message, args))
  }
}

