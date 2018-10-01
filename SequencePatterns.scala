val expr = List(0, 1, 2);
    expr match {
    case List(0, _, _) => println("Found it")
    case _ => println("Not Found")
}

// matches any number of elements within a sequence
expr match {
    case List(0, _*) => println("Found it")
    case _ => println("Not Found")
}
