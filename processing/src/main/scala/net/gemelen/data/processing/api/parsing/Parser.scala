package net.gemelen.data.processing.api.parsing

import net.gemelen.data.processing.api.Event

trait Parser[E <: ParsingError] {

  def parse(raw: String): Either[E, Event]

}

