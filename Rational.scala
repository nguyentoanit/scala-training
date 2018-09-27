class Rational(n: Int, d: Int) {
    require(d != 0)
    val numer: Int = n
    val denom: Int = d
    override def toString = numer +"/"+ denom
    def add(that: Rational): Rational = new Rational(numer * that.denom + that.numer * denom,denom * that.denom)
}

val rational1 = new Rational(1,2)
val rational2 = new Rational(3,2)
val result = rational1 add rational2
println(result)
