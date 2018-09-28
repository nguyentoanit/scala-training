trait Rectangular {
def topLeft: Point
def bottomRight: Point
def left = topLeft.x
def right = bottomRight.x
def width = right - left
// and many more geometric methods...
}

class Point(val x: Int, val y: Int)

class Rectangle(val topLeft: Point, val bottomRight: Point) extends Rectangular {

}

val rect = new Rectangle(new Point(1, 1), new Point(10, 10))
println(rect.left)
println(rect.right)
println(rect.width)
