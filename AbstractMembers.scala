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

// Abstract vals
abstract class Fruit {
    val v: String // ‘v’ for value
    def m: String // ‘m’ for method
}
abstract class Apple extends Fruit {
    val v: String
    val m: String // OK to override a ‘def’ with a ‘val’
}
abstract class BadApple extends Fruit {
    def v: String // ERROR: cannot override a ‘val’ with a ‘def’
    def m: String
}

// abstract vars
trait AbstractTime {
    def hour: Int // getter for ‘hour’
    def hour_=(x: Int) // setter for ‘hour’
    def minute: Int // getter for ‘minute’
    def minute_=(x: Int) // setter for ‘minute’
}
