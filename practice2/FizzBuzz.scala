def fizzBuzz(number: Int) = (number % 3, number % 5) match {
    case(0, 0) => "FizzBuzz"
    case(0, _) => "Fizz"
    case(_, 0) => "Buzz"
    case _ => number
}

for(i <- 1 to 30){
    println(fizzBuzz(i))
}
