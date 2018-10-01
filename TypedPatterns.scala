def generalSize(x: Any) = x match {
    case s: String => s.length
    case m: Map[_, _] => m.size
    case _ => -1
}
println(generalSize("abc")) // Int = 3
println(generalSize(Map(1 -> 'a', 2 -> 'b'))) // Int = 2
println(generalSize(math.Pi)) // Int = -1
