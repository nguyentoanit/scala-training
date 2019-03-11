import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import scala.util.{ Success, Failure }

implicit val ec = scala.concurrent.ExecutionContext.global

def a = Future { Thread.sleep(1000); 100 }
def b = Future { Thread.sleep(1000); throw new NullPointerException("Null Pointer Exception!!!") }

val readyA = Await.ready(a, Duration.Inf) // Success(100)
readyA onComplete {
  case Success(s) => print(s"==============\n$s\n")
  case Failure(exception) => print(s"==============\n${exception.getMessage}\n")
}

val readyB = Await.ready(b, Duration.Inf) // Failure(java.lang.NullPointerException)
readyB onComplete {
  case Success(s) => print(s"==============\n$s\n")
  case Failure(exception) => print(s"==============\n${exception.getMessage}\n")
}

val resultA = Await.result(a, Duration.Inf) // 100
print(s"==============\n$resultA\n")

val resultB = Await.result(b, Duration.Inf) // crash with java.lang.NullPointerException
print(s"==============\n$resultB\n")
