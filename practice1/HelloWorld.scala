/*
    print "Hello World!" with line break, five times.
*/
class HelloWorld (content: String, times: Int) {
    def show {
        for(time <- 1 to times) println(s"Time $time: $content")
    }
}

val objectHW = new HelloWorld("Hello World!", 5)
objectHW.show
