val Decimal = """(-)?(\d+)(\.\d*)?""".r
val input = "for -1.0 to 99 by 3 2.123123 "
println(Decimal findAllIn input)
for (s <- Decimal findAllIn input) println(s)

// Extracting with regular expressions
val Decimal(sign, integerpart, decimalpart) = "-1.23"
println(sign)
println(integerpart)
println(decimalpart)

for (Decimal(s, i, d) <- Decimal findAllIn input)
println("sign: "+ s +", integer: "+ i +", decimal: "+ d)
