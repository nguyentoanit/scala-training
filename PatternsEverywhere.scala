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


new PartialFunction[List[Int], Int] {
    def apply(xs: List[Int]) = xs match {
        case x :: y :: _ => y
    }
    def isDefinedAt(xs: List[Int]) = xs match {
        case x :: y :: _ => true
        case _ => false
    }
}
