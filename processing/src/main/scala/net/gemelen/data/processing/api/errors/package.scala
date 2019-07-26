package net.gemelen.data.processing.api.errors

sealed trait ProcessingError extends Exception {

  def message(details: String): String

}

trait ParsingError extends ProcessingError
trait ValidationError extends ProcessingError
trait EnrichmentError extends ProcessingError
