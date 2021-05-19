package org.atnos.eff

sealed trait Fx

object Fx {
  type prepend[T[_], R] = FxAppend[Fx1[T], R]
  type append[L, R] = FxAppend[L, R]
}

trait FxAppend[+L, +R] extends Fx

trait Fx1[+F[_]] extends Fx
trait Fx2[+L[_], +R[_]] extends Fx
trait Fx3[+L[_], +M[_], +R[_]] extends Fx

class NoFx extends Fx
object NoFx extends NoFx
