// Views
println("===========Views==============")
val arr = (0 to 9).toArray

val subarr = arr.view.slice(3, 6)

def plusTwo(xs: collection.mutable.Seq[Int]) = for (i <- 0 until xs.length) xs(i) += 2
plusTwo(subarr)

println(arr.toList)
println(subarr.toList)