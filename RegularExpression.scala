val Decimal = """(-)?(\d+)(\.\d*)?""".r
val input = "for -1.0 to 99 by 3 2.123123 "
println(Decimal findAllIn input)
for (s <- Decimal findAllIn input) println(s)
