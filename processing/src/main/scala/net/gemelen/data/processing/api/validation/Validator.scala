package net.gemelen.data.processing.api.validation

import cats.data.Validated
import net.gemelen.data.processing.api.Event

trait Validator[E <: ValidationError] {

  def validate(event: Event): Validated[E, Event]

}

