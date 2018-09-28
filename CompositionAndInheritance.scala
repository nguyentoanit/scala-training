abstract class Element {
    def contents: Array[String]
    def height: Int = contents.length
    def width: Int = if (height == 0) 0 else contents(0).length
    def demo() {
        println("Element's implementation invoked")
    }
}

class ArrayElement(conts: Array[String]) extends Element {
    def contents: Array[String] = conts
    override def demo() {
        println("ArrayElement's implementation invoked")
    }
}

class LineElement(s: String) extends ArrayElement(Array(s)) {
    override def width = s.length
    override def demo() {
        println("LineElement's implementation invoked")
    }
}

class UniformElement(ch: Char, override val width: Int, override val height: Int) extends Element {
    private val line = ch.toString * width
    def contents = Array.fill(height)(line)
}

val e1 = new ArrayElement(Array("hello", "world"))
val e2 = new LineElement("hello")
val e3 = new UniformElement('x', 2, 3)

println(e1.demo)
println(e2.demo)
println(e3.demo)
