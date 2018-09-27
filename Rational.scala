class Rational(n: Int, d: Int) {
    require(d != 0)
    val numer: Int = n
    val denom: Int = d
}

val rational1 = new Rational(1,2)
val rational2 = new Rational(3,2)
