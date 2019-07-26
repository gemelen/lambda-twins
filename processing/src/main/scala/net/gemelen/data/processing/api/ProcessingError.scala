package net.gemelen.data.processing.api

trait ProcessingError extends Exception {

  def message(details: String): String

}

