package org.atnos.eff

import cats._
import cats.syntax.all._

import scala.concurrent.duration.FiniteDuration

sealed trait Eff[R, A] {

  /** add one last action to be executed after any computation chained to this Eff value */
  def addLast(l: =>Eff[R, Unit]): Eff[R, A] =
    addLast(Last.eff(l))

  /** add one last action to be executed after any computation chained to this Eff value */
  private[eff] def addLast(l: Last[R]): Eff[R, A]

}

case class Pure[R, A](value: A, last: Last[R] = Last.none[R]) extends Eff[R, A] {
  def addLast(l: Last[R]): Eff[R, A] =
    Pure(value, last <* l)
}

/**
 * Impure is an effect (encoded as one possibility among other effects, a Union)
 * and a continuation providing the next Eff value.
 *
 * This essentially models a flatMap operation with the current effect
 * and the monadic function to apply to a value once the effect is interpreted
 *
 * One effect can always be executed last, just for side-effects
 */
case class Impure[R, X, A](union: Effect[R, X], continuation: Continuation[R, X, A], last: Last[R] = Last.none[R]) extends Eff[R, A] {
  def addLast(l: Last[R]): Eff[R, A] =
    Impure[R, X, A](union, continuation, last <* l)
}

/**
 * ImpureAp is a list of independent effects and a pure function
 * creating a value with all the resulting values once all effects have
 * been interpreted.
 *
 * This essentially models a sequence + map operation but it is important to understand that the list of
 * Union objects can represent different effects and be like: `Vector[Option[Int], Future[String], Option[Int]]`.
 *
 * Interpreting such an Eff value for a given effect (say Option) consists in:
 *
 *  - grouping all the Option values,
 *  - sequencing them
 *  - pass them to a continuation which will apply the 'map' functions when the other effects (Future in the example
 *  above) will have been interpreted
 *
 * VERY IMPORTANT:
 *
 *  - this object is highly unsafe
 *  - the size of the list argument to 'map' must always be equal to the number of unions in the Unions object
 *  - the types of the elements in the list argument to 'map' must be the exact types of each effect in unions.unions
 *
 */
case class ImpureAp[R, X, A](unions: Unions[R, X], continuation: Continuation[R, Vector[Any], A], last: Last[R] = Last.none[R]) extends Eff[R, A] {
  def toMonadic: Eff[R, A] =
    Impure[R, unions.X, A](unions.first, unions.continueWith(continuation), last)

  def addLast(l: Last[R]): Eff[R, A] =
    ImpureAp[R, X, A](unions, continuation, last <* l)
}

trait EffImplicits {

  @deprecated(message = "use EffApplicative", since = "")
  final def effApplicativeUnsafe: Applicative[Eff[AnyRef, *]] = effApplicativeUnsafeImpl

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

object EffImplicits extends EffImplicits

trait EffCreation {
  /** create an Eff[R, A] value from an effectful value of type T[V] provided that T is one of the effects of R */
  def send[T[_], R, V](tv: T[V])(implicit member: T |= R): Eff[R, V] =
    ImpureAp(Unions(member.inject(tv), Vector.empty), Continuation.lift(xs => pure[R, V](xs.head.asInstanceOf[V])))

  /** create a pure value */
  def pure[R, A](a: A): Eff[R, A] =
    Pure(a)

  /** create a impure value from an union of effects and a continuation */
  def impure[R, X, A](union: Union[R, X], continuation: Continuation[R, X, A]): Eff[R, A] =
    Impure[R, X, A](union, continuation)

  /** create a delayed impure value */
  def impure[R, A, B](value: A, continuation: Continuation[R, A, B]): Eff[R, B] =
    Impure(NoEffect(value), continuation)

  /** create a delayed impure value */
  def impure[R, A, B](value: A, continuation: Continuation[R, A, B], map: B => B): Eff[R, B] =
    Impure(NoEffect(value), Continuation.lift((a: A) => continuation(a), continuation.onNone).map(map))

  /** apply a function to an Eff value using the applicative instance */
  def ap[R, A, B](a: Eff[R, A])(f: Eff[R, A => B]): Eff[R, B] =
    EffImplicits.EffApplicative[R].ap(f)(a)


  /** use the applicative instance of Eff to traverse a list of values, then flatten it */
  def flatTraverseA[R, F[_], A, B](fs: F[A])(f: A => Eff[R, F[B]])(implicit FT: Traverse[F], FM: FlatMap[F]): Eff[R, F[B]] =
    FT.flatTraverse[Eff[R, *], A, B](fs)(f)(EffImplicits.EffApplicative[R], FM)

  /** use the applicative instance of Eff to sequence a list of values, then flatten it */
  def flatSequenceA[R, F[_], A](fs: F[Eff[R, F[A]]])(implicit FT: Traverse[F], FM: FlatMap[F]): Eff[R, F[A]] =
    FT.flatSequence[Eff[R, *], A](fs)(EffImplicits.EffApplicative[R], FM)

}

object EffCreation extends EffCreation
