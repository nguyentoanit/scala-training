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

// Partially applied functions
def sum(a: Int, b: Int, c: Int) = a + b + c
// Assign default values
val a = sum(1, _:Int, _:Int)
val b = sum _
println (a(2,3))
println (b(1,2,3))
