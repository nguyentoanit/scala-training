// For expression
val column = List(-1,2,-3,4)
val row = List(1,2,3,4,5)

var result = for{
    x <- column
    positive = x
    if(positive > 0)
} yield x

var matrix = for{
    x <- row
    y <- column
} yield (y,x)

println(result)
println(matrix)
