package org.atnos.eff

sealed trait Effect[R, A] {
  type X = A
}

case class NoEffect[R, A](a: A) extends Effect[R, A]

sealed trait Union[R, A] extends Effect[R, A]  {
}
