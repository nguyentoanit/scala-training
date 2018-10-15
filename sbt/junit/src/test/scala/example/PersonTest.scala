import junit.framework.TestCase
import junit.framework.Assert.assertEquals
import junit.framework.Assert.fail
class PersonTest extends TestCase {
    def testGetFirstChar() {
        val obj = new Person()
        assertEquals(obj.getFirstChar("Toan"), 'T')
        // try {
        //     fail()
        // }
        // catch {
        //     case e: IllegalArgumentException => // expected
        // }
    }
}