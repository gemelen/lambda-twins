package net.gemelen.spark.batch

import java.util.concurrent.atomic.AtomicReference

import com.typesafe.config.Config

class DriverContext(val config: Config) {

}

object DriverContext {

  val context: AtomicReference[DriverContext] = new AtomicReference()

  def apply(config: Config): DriverContext = synchronized {
    if (Option(context.get()).isEmpty) {
      context.set(new DriverContext(config))
    }
    context.get
  }
}

