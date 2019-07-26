package net.gemelen.data.processing.api.parsing

import net.gemelen.data.processing.api.Event
import net.gemelen.data.processing.api.errors.ParsingError

trait Parser[E <: ParsingError] {

  def parse(raw: String): Either[E, Event]

}

