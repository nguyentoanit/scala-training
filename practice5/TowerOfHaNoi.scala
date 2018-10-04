move2("A", "B", "C", 4)

def move2(a: String, b: String, c: String, number: Int) {
    
    number match {
        case 1 => println(s"Disk 1: From ${a} to ${c}")
        case _ => {
            move2(a, c, b, number-1)
            println(s"Disk ${number}: From ${a} to ${c}")
            move2(b, a, c, number-1)
        }
    }
}
