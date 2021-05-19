package org.atnos.eff

import cats._
import cats.data._
import cats.syntax.all._

trait Interpret {
  def transform[SR, BR, U1, U2, TS[_], TB[_], A](effect: Eff[SR, A], nat: TS ~> TB)
                                               (implicit sr: Member.Aux[TS, SR, U1],
                                                         br: Member.Aux[TB, BR, U2]): Eff[BR, A] = {
    ???
  }
}

object Interpret extends Interpret
