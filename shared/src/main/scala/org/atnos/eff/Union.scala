package org.atnos.eff

sealed trait Effect[R, A] {
  type X = A
}

case class NoEffect[R, A](a: A) extends Effect[R, A]

sealed trait Union[R, A] extends Effect[R, A]  {
  final private[eff] def forget[E, B]: Union[E, B] =
    asInstanceOf[Union[E, B]]

  final private[eff] def tagged: UnionTagged[R, A] =
    this.asInstanceOf[UnionTagged[R, A]]
}

case class UnionTagged[R, A] (valueUnsafe: Any, index: Int) extends Union[R, A] {
  private[eff] def increment[E]: Union[E, A] = copy(index = index + 1)
  private[eff] def decrement[E]: Union[E, A] = copy(index = index - 1)
}
case class UnionAppendL[L, R, A](value: Union[L, A]) extends Union[FxAppend[L, R], A]
case class UnionAppendR[L, R, A](value: Union[R, A]) extends Union[FxAppend[L, R], A]

