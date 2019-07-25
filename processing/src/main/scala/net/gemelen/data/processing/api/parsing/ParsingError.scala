package net.gemelen.data.processing.api.parsing

sealed trait ParsingError {

  def message(details: String): String

}

