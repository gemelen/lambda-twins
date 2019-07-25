package net.gemelen.data.processing.api.validation

sealed trait ValidationError {

  def message(details: String): String

}

