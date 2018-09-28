import java.io.FileReader
import java.io.FileNotFoundException
import java.io.IOException

try {
    val f = new FileReader("input.txt")
// Use and close file
} catch {
    case ex: FileNotFoundException => println("FileNotFoundException")
    case ex: IOException => println("IOException")
}
