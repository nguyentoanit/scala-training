val increase = (x: Int) => { 
    println("We") 
    println("are") 
    println("here!") 
    x + 1 
}
increase(123123)

val someNumbers = List(-11, -10, -5, 0, 5, 10)
println(someNumbers.filter((x: Int) => x > 0))

// Placeholder syntax
println(someNumbers.filter(_ > 0))
val f = (_: Int) + (_: Int)
println(f(5, 10))
