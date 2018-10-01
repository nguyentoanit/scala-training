def describe(expr: Any) = expr match {
    case 0 => "zero"
    case somethingElse => "not zero: "+ somethingElse
}

println(describe("aaaa"))
// not zero: aaaa
