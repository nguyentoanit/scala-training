// Bitset
println("===========Bitset==============")
val bits = scala.collection.immutable.BitSet.empty
println(bits)

val moreBits = bits + 3 + 4 + 4 + 5
println(moreBits)
println(moreBits(2))
println(moreBits(3))

// String builders
println("===========StringBuilder==============")
val buf = new StringBuilder
buf += 'T'
buf ++= "oan"
println(buf)

// Queues
println("===========Queues==============")
val queue = new scala.collection.mutable.Queue[String]
queue += "One"
queue ++= "Two" :: "Three" :: Nil
println(queue)
println(queue.dequeue)
println(queue)

// Stacks
println("===========Stacks==============")
val stack = new scala.collection.mutable.Stack[String]
stack.push("A")
stack.push("B")
println(stack)
println(stack.top)
println(stack.pop)
println(stack)
