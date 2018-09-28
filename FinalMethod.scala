abstract class Element {
    def demo() {
        println("Element's implementation invoked")
    }
}

class ArrayElement(conts: Array[String]) extends Element {
    final override def demo() {
        println("ArrayElement's implementation invoked")
    }
}

class LineElement(s: String) extends ArrayElement(Array(s)) {
    override def demo() { // Cannot override because demo method of superclass is final
        println("LineElement's implementation invoked")
    }
}
val e1 = new ArrayElement(Array("hello", "world"))
val e2 = new LineElement("hello")

println(e1.demo)
println(e2.demo)
