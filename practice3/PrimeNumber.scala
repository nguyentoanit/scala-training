/* 
    1. Check if given number is prime number.
    2. Show all prime numbers less than or same as given number.
*/

import scala.math.sqrt
import scala.math.ceil

def isPrime(n: Int) = {
    val finish = ceil(sqrt(n)).toInt
    // The _ acts as a placeholder for parameters in the anonymous function
    (2 to finish) forall (n % _ != 0)
}
def prime(n: Int) = for (i <- 1 to n) if (isPrime(i)) println(i)

prime(101)
