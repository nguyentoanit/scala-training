class FizzBuzz {
    def three = "Fizz"
    def five = "Buzz"
    def calculate (number: Int) = {
        if (number % 15 == 0) {
            this.three + this.five
        } else if (number % 5 == 0) {
            this.five
        } else if (number % 3 == 0) {
            this.three
        } else number
    }
}

val fB = new FizzBuzz
for(i <- 1 to 30){
    println(fB.calculate(i))
}
