// http://allaboutscala.com/tutorials/chapter-9-beginner-tutorial-using-scala-futures
println("Step 1: Define a method which returns a Future")
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
def donutStock(donut: String): Future[Int] = Future {
  // assume some long running database operation
  println("checking donut stock")
  10
}

println("\nStep 2: Non blocking future result")
import scala.util.{Failure, Success}
donutStock("vanilla donut").onComplete {
  case Success(stock) => println(s"Stock for vanilla donut = $stock")
  case Failure(e) => println(s"Failed to find vanilla donut stock, exception = $e")
}
Thread.sleep(3000)