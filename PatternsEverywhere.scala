// Patterns in variable definitions
val myTuple = (123, "abc")
val (number, string) = myTuple
println(number)
println(string)

// Case sequences as partial functions
val withDefault: Option[Int] => Int = {
    case Some(x) => x
    case None => 0
}
println(withDefault(Some(10)))
println(withDefault(None))

// Patterns in for expressions
val capitals = Map("France" -> "Paris", "Japan" -> "Tokyo")
for ((country, city) <- capitals) println("The capital of "+ country +" is "+ city)
