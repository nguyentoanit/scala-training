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

// // Converting between mutable and immutable sets and maps
import  scala.collection.mutable.Map
val muta = scala.collection.mutable.Map("i" -> 1, "ii" -> 2)
val immu = scala.collection.immutable.Map.empty ++ muta
println(immu)

