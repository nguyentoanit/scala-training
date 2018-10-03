//Repeated parameters
def sum(numbers: Int*) = {
    var sum = 0
    for (number <- numbers) sum += number
    sum
}
println(sum(1,2,3,4))

val arr = Array(1,2,5,6)
println(sum(arr: _*))

// Default parameter values
def hello(name: String = "Toan") = {
    println(s"Hello, ${name}")
}
hello()
hello("")
hello("ABC")
