package org.atnos.eff

import cats._
import cats.syntax.all._

import scala.util.control.NonFatal

case class Last[R](value: Option[Eval[Eff[R, Unit]]]) {
  def interpret[U](n: Eff[R, Unit] => Eff[U, Unit]): Last[U] = ???
  def interpretEff[U](n: Last[R] => Eff[U, Unit]): Last[U] = ???
  def <*(last: Last[R]): Last[R] = ???
  def *>(last: Last[R]): Last[R] = ???
}

object Last {

  def none[R]: Last[R] =
    Last(None)

  def eff[R](e: =>Eff[R, Unit]): Last[R] =
    Last(Option(Eval.later(evaluate(e))))

  def evaluate[R](e: =>Eff[R, Unit]): Eff[R, Unit] = ???
}
