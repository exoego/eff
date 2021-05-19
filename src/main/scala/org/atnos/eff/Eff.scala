package org.atnos.eff

import cats._
import cats.syntax.all._

case class Last[R](value: Option[Eval[Eff[R, Unit]]]) {
  def *>(other: Last[R]): Last[R] = ???
}
object Last {
  def none[R]: Last[R] = ???
}

sealed trait Effect[R, A]
sealed trait Union[R, A] extends Effect[R, A]
case class Unions[R, A](first: Union[R, A], rest: Vector[Union[R, Any]])

case class Continuation[R, A, B](functions: Vector[Any => Eff[R, Any]], onNone: Last[R] = Last.none[R]) {
  def dimapEff[C, D](f: C => A)(g: Eff[R, B] => Eff[R, D]): Continuation[R, C, D] = ???
}

sealed trait Eff[R, A]
case class Pure[R, A](value: A, last: Last[R] = Last.none[R]) extends Eff[R, A]
case class Impure[R, X, A](union: Effect[R, X], continuation: Continuation[R, X, A], last: Last[R] = Last.none[R]) extends Eff[R, A]
case class ImpureAp[R, X, A](unions: Unions[R, X], continuation: Continuation[R, Vector[Any], A], last: Last[R] = Last.none[R]) extends Eff[R, A]

trait EffImplicits {
  final def EffApplicative[R]: Applicative[Eff[R, *]] = effApplicativeUnsafeImpl.asInstanceOf[Applicative[Eff[R, *]]]

  private[this] final val effApplicativeUnsafeImpl: Applicative[Eff[AnyRef, *]] = new Applicative[Eff[AnyRef, *]] {
    def pure[A](a: A): Eff[AnyRef, A] = ???
    override def product[A, B](fa: Eff[AnyRef, A], fb: Eff[AnyRef, B]): Eff[AnyRef, (A, B)] = ???

    def ap[A, B](ff: Eff[AnyRef, A => B])(fa: Eff[AnyRef, A]): Eff[AnyRef, B] =
      fa match {
        case Pure(a, last) =>
          ???
//          ff match {
//            case Pure(f, last1)                   => Pure(f(a), last1 *> last)
//            case Impure(u: Union[_, _], c, last1) => ImpureAp(Unions(u, Vector.empty), c.dimapEff((_:Vector[Any]).head)(_.map(_(a))), last1 *> last)
//            case ImpureAp(u, c, last1)            => ImpureAp(u, c.map(_(a)), last1 *> last)
//          }
        case Impure(u: Union[_, _], c, last) => ???
        case ImpureAp(unions, c, last) => ???
      }
  }
}
