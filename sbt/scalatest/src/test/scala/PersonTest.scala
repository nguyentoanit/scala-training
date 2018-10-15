import org.scalatest._

class PersonTest extends FunSuite {
    test("Person.getFirstChar") {
        val obj = new Person()

        // Test success
        assert(obj.getFirstChar("Toan") === 'T')
        
        // Test failed
        // assert(obj.getFirstChar("Toan") === 'x')
    }
}