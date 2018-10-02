val capitals = Map("France" -> "Paris", "Japan" -> "Tokyo")
println(capitals get "France")

def show(x: Option[String]) = x match {
    case Some(s) => s
    case None => "?"
}
println(show(capitals get "Japan"))
println(show(capitals get "France"))
println(show(capitals get "North Pole"))
