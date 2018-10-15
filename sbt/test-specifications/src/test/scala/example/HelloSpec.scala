import org.scalatest._

class HelloSpec extends FlatSpec with Matchers {
    val hello = new Hello
    hello.sayHello("Toan") should equal ("Hello, Toan")
}