package org.atnos.eff

import cats._

case class Unions[R, A](first: Union[R, A], rest: Vector[Union[R, Any]]) {
  type X = A

  def continueWith[B](continuation: Continuation[R, Vector[Any], B]): Continuation[R, A, B] = ???
}
