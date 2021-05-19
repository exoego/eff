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

object Union {
  def one[M[_], A](value: M[A]): Union[Fx1[M], A] =
    UnionTagged(value, 1)

  def twoL[M[_], T[_], A](value: M[A]): Union[Fx2[M, T], A] =
    UnionTagged(value, 1)

  def twoR[M[_], T[_], A](value: T[A]): Union[Fx2[M, T], A] =
    UnionTagged(value, 2)

  def threeL[M[_], T[_], N[_], A](value: M[A]): Union[Fx3[M, T, N], A] =
    UnionTagged(value, 1)

  def threeM[M[_], T[_], N[_], A](value: T[A]): Union[Fx3[M, T, N], A] =
    UnionTagged(value, 2)

  def threeR[M[_], T[_], N[_], A](value: N[A]): Union[Fx3[M, T, N], A] =
    UnionTagged(value, 3)

  def appendL[L, R, A](union: Union[L, A]): Union[FxAppend[L, R], A] =
    UnionAppendL(union)

  def appendR[L, R, A](union: Union[R, A]): Union[FxAppend[L, R], A] =
    UnionAppendR(union)
}

