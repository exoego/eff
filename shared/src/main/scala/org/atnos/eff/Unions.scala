package org.atnos.eff

import cats._

case class Unions[R, A](first: Union[R, A], rest: Vector[Union[R, Any]]) {
  type X = A

  def size: Int = ???
  def unions: Vector[Union[R, Any]] = ???
  def append[B](others: Unions[R, B]): Unions[R, A] = ???
  def continueWith[B](continuation: Continuation[R, Vector[Any], B]): Continuation[R, A, B] = ???
  def into[S](f: UnionInto[R, S]): Unions[S, A] = ???
  def project[M[_], U](implicit m: Member.Aux[M, R, U]): CollectedUnions[M, R, U] = ???
  def extract[M[_]](implicit m: M /= R): CollectedUnions[M, R, R] = ???
  def transform[M[_]](nat: M ~> M)(implicit m: M /= R): Unions[R, A] = ???
  def transformInto[M[_], N[_], U, S](nat: M ~> N)(implicit m: Member.Aux[M, R, U], n: Member.Aux[N, S, U]): Unions[S, A] = ???
}

object Unions {
  def send[M[_], R, X](mx: M[X])(implicit m: MemberIn[M, R]) =
    Unions[R, X](m.inject(mx), Vector.empty)
}

case class CollectedUnions[M[_], R, U](effects: Vector[M[Any]], otherEffects: Vector[Union[U, Any]], indices: Vector[Int], otherIndices: Vector[Int]) {
  def continuation[A](continueWith: Continuation[R, Vector[Any], A], m: Member.Aux[M, R, U]): Continuation[R, Vector[Any], A] = ???
  def continuation[A](continueWith: Continuation[U, Vector[Any], A]): Continuation[U, Vector[Any], A] = ???
}

trait UnionInto[R, S] {
  def apply[A](union: Union[R, A]): Union[S, A]
  def into[A](e: Eff[R, A]): Eff[S, A] = ???
}
