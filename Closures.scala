//Repeated parameters
def sum(numbers: Int*) = {
    var sum = 0
    for (number <- numbers) sum += number
    sum
}

println(sum(1,2,3,4))