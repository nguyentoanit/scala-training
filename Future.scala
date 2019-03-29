import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{ Success, Failure }

/*
def a = Future(100)
def b = Future(throw new NullPointerException("Null Pointer Exception!"))

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

a onComplete {
  case Success(s) => println("aaaaaa");println(s"==============\n$s\n")
  case Failure(exception) => println("aaaaaa");println(s"==============\n${exception.getMessage}\n")
}

b onComplete {
  case Success(s) => println("aaaaaa");print(s"==============\n$s\n")
  case Failure(exception) => println("aaaaaa");print(s"==============\n${exception.getMessage}\n")
}*/
val a = Future {
  Thread.sleep(100)
  100
}
a.onComplete {
  case Success(s) => println(s"==============\n$s\n")
  case Failure(exception) => println(s"==============\n${exception.getMessage}\n")
}
