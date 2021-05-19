package org.atnos.eff

import cats._

import scala.annotation.tailrec

case class Continuation[R, A, B](functions: Vector[Any => Eff[R, Any]], onNone: Last[R] = Last.none[R]) extends (A => Eff[R, B]) {
  def append[C](f: B => Eff[R, C]): Continuation[R, A, C] = ???
  def mapLast[C](f: Eff[R, B] => Eff[R, C]): Continuation[R, A, C] =  ???
  def map[C](f: B => C): Continuation[R, A, C] = ???
  def apply(a: A): Eff[R, B] = ???
  def applyEval(a: A): Eff[R, B] = ???
  def contramap[C](f: C => A): Continuation[R, C, B] = ???
  def contraFlatMap[C](f: C => Eff[R, A]): Continuation[R, C, B] = ???
  def dimapEff[C, D](f: C => A)(g: Eff[R, B] => Eff[R, D]): Continuation[R, C, D] = ???
  def interpret[U, C](map: Eff[R, B] => Eff[U, C])(mapOnNone: Last[R] => Last[U]): Continuation[U, A, C] = ???
  def interpretEff[U, C](map: Eff[R, B] => Eff[U, C])(mapOnNone: Eff[R, Unit] => Eff[U, Unit]): Continuation[U, A, C] = ???
  def runOnNone: Eff[R, Unit] = ???
}

object Continuation {
  def lift[R, A, B](f: A => Eff[R, B], otherwise: Last[R] = Last.none[R]): Continuation[R, A, B] = ???
  def unit[R, A]: Continuation[R, A, A] = ???
  implicit def ArrsFunctor[R, X]: Functor[Continuation[R, X, *]] = new Functor[Continuation[R, X, *]] {
    def map[A, B](fa: Continuation[R, X, A])(f: A => B): Continuation[R, X, B] =
      fa.map(f)
  }
}


