// For expression
val column = List(-1,2,-3,4)

var result = for{
    x <- column
    positive = x
    if(positive > 0)
} yield x

println(result)
