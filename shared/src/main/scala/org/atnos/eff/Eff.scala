package org.atnos.eff

import cats._
import cats.syntax.all._

import scala.concurrent.duration.FiniteDuration

sealed trait Eff[R, A] {
  def addLast(l: =>Eff[R, Unit]): Eff[R, A] =
    addLast(Last.eff(l))

  private[eff] def addLast(l: Last[R]): Eff[R, A]

}

case class Pure[R, A](value: A, last: Last[R] = Last.none[R]) extends Eff[R, A] {
  def addLast(l: Last[R]): Eff[R, A] =
    Pure(value, last <* l)
}

case class Impure[R, X, A](union: Effect[R, X], continuation: Continuation[R, X, A], last: Last[R] = Last.none[R]) extends Eff[R, A] {
  def addLast(l: Last[R]): Eff[R, A] =
    Impure[R, X, A](union, continuation, last <* l)
}

case class ImpureAp[R, X, A](unions: Unions[R, X], continuation: Continuation[R, Vector[Any], A], last: Last[R] = Last.none[R]) extends Eff[R, A] {
  def toMonadic: Eff[R, A] =
    Impure[R, unions.X, A](unions.first, unions.continueWith(continuation), last)

  def addLast(l: Last[R]): Eff[R, A] =
    ImpureAp[R, X, A](unions, continuation, last <* l)
}

trait EffImplicits {

  private[this] final val effApplicativeUnsafeImpl: Applicative[Eff[AnyRef, *]] = new Applicative[Eff[AnyRef, *]] {

    def pure[A](a: A): Eff[AnyRef, A] =
      Pure[AnyRef, A](a)

    override def product[A, B](fa: Eff[AnyRef, A], fb: Eff[AnyRef, B]): Eff[AnyRef, (A, B)] =
      ap(map(fb)(b => (a: A) => (a, b)))(fa)

    def ap[A, B](ff: Eff[AnyRef, A => B])(fa: Eff[AnyRef, A]): Eff[AnyRef, B] =
      fa match {
        case Pure(a, last) => ???
//          ff match {
//            case Pure(f, last1)                   => Pure(f(a), last1 *> last)
//            case Impure(NoEffect(f), c, last1)    => Impure(NoEffect[AnyRef, Any](f), c.append(f1 => pure(f1(a))), c.onNone).addLast(last1 *> last)
//            case Impure(u: Union[_, _], c, last1) => ImpureAp(Unions(u, Vector.empty), c.dimapEff((_:Vector[Any]).head)(_.map(_(a))), last1 *> last)
//            case ImpureAp(u, c, last1)            => ImpureAp(u, c.map(_(a)), last1 *> last)
//          }

        case Impure(NoEffect(a), c, last) =>
          ap(ff)(c(a).addLast(last))

        case Impure(u: Union[_, _], c, last) => ???
//          ff match {
//            case Pure(f, last1)                     => ImpureAp(Unions(u, Vector.empty), c.contramap((_:Vector[Any]).head).map(f), last1 *> last)
//            case Impure(NoEffect(f), c1, last1)     => Impure(u, c.append(x => c1(f).map(_(x)))).addLast(last1 *> last)
//            case Impure(u1: Union[_, _], c1, last1) => ImpureAp(Unions(u, Vector(u1)),  Continuation.lift(ls => ap(c1(ls(1)))(c(ls.head)), c.onNone), last1 *> last)
//            case ImpureAp(u1, c1, last1)            => ImpureAp(Unions(u, u1.unions), Continuation.lift(ls => ap(c1(ls.drop(1)))(c(ls.head)), c.onNone), last1 *> last)
//          }

        case ImpureAp(unions, c, last) => ???
//          ff match {
//            case Pure(f, last1)                    => ImpureAp(unions, c map f, last1 *> last)
//            case Impure(NoEffect(f), c1, last1)    => ImpureAp(unions, c.append(x => c1(f).map(_(x)))).addLast(last1 *> last)
//            case Impure(u: Union[_, _], c1, last1) => ImpureAp(Unions(unions.first, unions.rest :+ u), Continuation.lift(ls => ap(c1(ls.last))(c(ls.dropRight(1))), c.onNone), last1 *> last)
//            case ImpureAp(u, c1, last1)            => ImpureAp(u append unions, Continuation.lift({ xs =>
//              val usize = u.size
//              val (taken, dropped) = xs.splitAt(usize)
//              // don't recurse if the number of effects is too large
//              // this will ensure stack-safety on large traversals
//              // and keep enough concurrency on smaller traversals
//              if (xs.size > 10)
//                Eff.impure(taken, Continuation.lift((xs1: Vector[Any]) => ap(c1(xs1))(c(dropped)), c1.onNone))
//              else
//                ap(c1(taken))(c(dropped))
//            }, c.onNone), last1 *> last)
//          }

      }
  }

  final def EffApplicative[R]: Applicative[Eff[R, *]] = effApplicativeUnsafeImpl.asInstanceOf[Applicative[Eff[R, *]]]

}