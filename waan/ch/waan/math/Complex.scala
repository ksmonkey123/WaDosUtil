package ch.waan.math

import java.util.Objects
import java.util.Arrays

object ComplexMath {

    def cis(d : Double) = Complex(Math cos d, Math sin d)
    def sqrt(c : Complex) = nroot(Complex(2, 0))(c)
    def nroot(n : Complex)(x : Complex) = pow(x)(n.reciprocal)
    def pow(x : Complex)(y : Complex) = new PreComplex(Math.pow(x.abs, y.re) * Math.exp(-y.im * x.arg)) * cis(y.re * x.arg + y.im * Math.log(x.abs))
    def exp(c : Complex) = pow(Complex(Math.E, 0))(c)

}

private  class PreComplex(re : Double) extends Complex(re, 0) {
    def i(d : Double) = new Complex(re, d)
}

object Complex {
    def abs(c : Complex) = c.abs
    def re(c : Complex) = c.re
    def im(c : Complex) = c.im
    implicit def doubleToPreComplex(d : Double) = new PreComplex(d)
    val i = 0 i 1;
}


case class Complex(val re : Double, val im : Double) {
    override def toString = if (re != 0 && im != 0) re + " + " + im + "i" else if (im == 0) re + "" else im + "i"
    def ==(c : Complex) = c != null && c.im == im && c.re == re
    override def equals(o : Any) = {
        o match {
            case Complex(r, i) => (r == re && i == im)
            case _             => false
        }
    }
    override def hashCode = Arrays.hashCode(Array(re, im))

    /* UNARY OPERATIONS */
    def con = conjugate
    def conjugate = Complex(re, -im)
    def rec = Complex(1, 0) / this
    def reciprocal = rec
    def unary_- = Complex(-re, -im)
    def abs = Math.sqrt(re * re + im * im)
    def arg = Math atan2 (im, re)
    /* ARITHMETICS */
    def +(other : Complex) = Complex(re + other.re, im + other.im)
    def -(other : Complex) = this + -other
    def *(other : Complex) = Complex(re * other.re - im * other.im, re * other.im + im * other.re)
    def /(other : Complex) = Complex(re * other.re + im * other.im, im * other.re - re * other.im) * new PreComplex(1 / (other.re * other.re + other.im * other.im))
    def ^(other : Complex) = ComplexMath.pow(this)(other)
}