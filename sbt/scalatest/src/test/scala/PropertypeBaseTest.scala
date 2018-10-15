
import org.scalatest._
import org.scalatest.prop.Checkers

class PropertypeBase extends WordSpec with Checkers {
    "Rectangle result" must {
        "have passed area" in {
            check((length: Int, width: Int) => new Rectangle(length, width).getArea() == length*width)
        }
    }
}