class Rational(n: Int, d: Int) {
    require(d != 0)
    val numer: Int = n
    val denom: Int = d
    override def toString = numer +"/"+ denom
    def add(that: Rational): Rational = new Rational(numer * that.denom + that.numer * denom,denom * that.denom)
    def lessThan(that: Rational) = this.numer * that.denom < that.numer * this.denom
    def max(that: Rational) = if (this.lessThan(that)) that else this
}

val rational1 = new Rational(1,2)
val rational2 = new Rational(3,2)
val add = rational1 add rational2
val lessThan = rational1 lessThan rational2
val max = rational1 max rational2
println(add)
println(lessThan)
println(max)
