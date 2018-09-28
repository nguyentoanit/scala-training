abstract class Element {
    def contents: Array[String]
    def height: Int = contents.length
    def width: Int = if (height == 0) 0 else contents(0).length
}

class ArrayElement(conts: Array[String]) extends Element {
    def contents: Array[String] = conts
}

class LineElement(s: String) extends ArrayElement(Array(s)) {
    override def width = s.length
    def hidden = 2
}

class UniformElement(ch: Char, override val width: Int, override val height: Int) extends Element {
    private val line = ch.toString * width
    def contents = Array.fill(height)(line)
}

val e1 = new ArrayElement(Array("hello", "world"))
val e2 = new LineElement("hello")
val e3 = new UniformElement('x', 2, 3)

println(e1.contents)
println(e2.contents)
println(e3.contents)
