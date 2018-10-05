// Array
val fiveToOne = Array(5, "4", 3, true, 1)

// Using maps
import  scala.collection.mutable.Map
var information:Map[String, String] = Map()
information("username") = "username1"
information("password") = "password1"
information("email") = "user@example.com"
information += ("gender" -> "1")
println(information)
