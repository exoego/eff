package org.atnos.eff

sealed trait Effect[R, A] {
  type X = A
}

sealed trait Union[R, A] extends Effect[R, A]
