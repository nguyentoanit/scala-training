var numbers = (1 :: 2 :: 3 :: 4 :: Nil)

println(numbers.head)
println(numbers.tail)
println(numbers.isEmpty)

// List patterns
var names = "Nguyen" :: "Toan" :: Nil
var List(lastName, firstName) = names
println(lastName)
println(firstName)

var lastName2 :: unknowParameter = names
println(lastName2)
println(unknowParameter)

// Concatenating two lists
var concat = List(3, 4, 5) ::: List(1, 2)
println(concat)
