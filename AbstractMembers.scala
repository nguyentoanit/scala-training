trait Abstract {
    type T
    def transform(x: T): T
    val initial: T
    var current: T
}
class Concrete extends Abstract {
    type T = String
    def transform(x: String) = x + x
    val initial = "hi"
    var current = initial
}

// abstract vars
trait AbstractTime {
    def hour: Int // getter for ‘hour’
    def hour_=(x: Int) // setter for ‘hour’
    def minute: Int // getter for ‘minute’
    def minute_=(x: Int) // setter for ‘minute’
}

trait RationalTrait {
    val numerArg: Int
    val denomArg: Int
    require(denomArg != 0)
    private val g = gcd(numerArg, denomArg)
    val numer = numerArg / g
    val denom = denomArg / g
    private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
    override def toString = numer +"/"+ denom
}
//Pre-initialized fields
object twoThirds extends {
    val numerArg = 2
    val denomArg = 3
} with RationalTrait

//  Abstract types
class Food
abstract class Animal {
    type SuitableFood <: Food // upper bound
    def eat(food: SuitableFood)
}
class Grass extends Food
class Grass2 extends Grass
class Cow extends Animal {
    type SuitableFood = Grass
    override def eat(food: Grass) {println("I can eat them")}
}

val grass = new Grass()
val grass2 = new Grass2()
val cow = new Cow()

cow eat grass
cow eat grass2
