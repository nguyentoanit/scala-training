def tupleDemo(expr: Any) = expr match {
    case (a, b, c) => println("matched "+ a + b + c)
    case _ => println("Not matched")
}

tupleDemo(("a ", 3, "-tuple"))
