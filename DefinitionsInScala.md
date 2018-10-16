# Definition in Scala
## Class
Classes in Scala are blueprints for creating objects. They can contain methods, values, variables, types, objects, traits, and classes which are collectively called members. Types, objects, and traits will be covered later in the tour.
```
class User

val user1 = new User
```

# Trait
Traits are used to share interfaces and fields between classes. They are similar to Java 8’s interfaces. Classes and objects can extend traits but traits cannot be instantiated and therefore have no parameters.

```
trait Iterator[A] {
  def hasNext: Boolean
  def next(): A
}

class IntIterator(to: Int) extends Iterator[Int] {
  private var current = 0
  override def hasNext: Boolean = current < to
  override def next(): Int =  {
    if (hasNext) {
      val t = current
      current += 1
      t
    } else 0
  }
}


val iterator = new IntIterator(10)
iterator.next()  // returns 0
iterator.next()  // returns 1
```

# Mixin
In object-oriented programming languages, a Mixin is a class that contains methods for use by other classes without having to be the parent class of those other classes. How those other classes gain access to the mixin's methods depends on the language. Mixins are sometimes described as being "included" rather than "inherited".

# Case class
Case classes are like regular classes with a few key differences which we will go over. Case classes are good for modeling immutable data. 

```
case class Book(isbn: String)

val frankenstein = Book("978-0486282114")
```
> Notice how the keyword new was not used to instantiate the Book case class. This is because case classes have an apply method by default which takes care of object construction.

> When you create a case class with parameters, the parameters are public vals.

```
case class Message(sender: String, recipient: String, body: String)
val message1 = Message("guillaume@quebec.ca", "jorge@catalonia.es", "Ça va ?")

println(message1.sender)  // prints guillaume@quebec.ca
message1.sender = "travis@washington.us"  // this line does not compile
```

# Pattern matching
Pattern matching is a mechanism for checking a value against a pattern. A successful match can also deconstruct a value into its constituent parts. It is a more powerful version of the switch statement in Java and it can likewise be used in place of a series of if/else statements.

```
import scala.util.Random

val x: Int = Random.nextInt(10)

x match {
  case 0 => "zero"
  case 1 => "one"
  case 2 => "two"
  case _ => "many"
}
```

## Pattern guards
Pattern guards are simply boolean expressions which are used to make cases more specific. Just add `if <boolean expression>` after the pattern.
```
def showImportantNotification(notification: Notification, importantPeopleInfo: Seq[String]): String = {
  notification match {
    case Email(email, _, _) if importantPeopleInfo.contains(email) =>
      "You got an email from special someone!"
    case SMS(number, _) if importantPeopleInfo.contains(number) =>
      "You got an SMS from special someone!"
    case other =>
      showNotification(other) // nothing special, delegate to our original showNotification function
  }
}

val importantPeopleInfo = Seq("867-5309", "jenny@gmail.com")

val someSms = SMS("867-5309", "Are you there?")
val someVoiceRecording = VoiceRecording("Tom", "voicerecording.org/id/123")
val importantEmail = Email("jenny@gmail.com", "Drinks tonight?", "I'm free after 5!")
val importantSms = SMS("867-5309", "I'm here! Where are you?")

println(showImportantNotification(someSms, importantPeopleInfo))
println(showImportantNotification(someVoiceRecording, importantPeopleInfo))
println(showImportantNotification(importantEmail, importantPeopleInfo))
println(showImportantNotification(importantSms, importantPeopleInfo))
```

## Matching on type only
```
abstract class Device
case class Phone(model: String) extends Device{
  def screenOff = "Turning screen off"
}
case class Computer(model: String) extends Device {
  def screenSaverOn = "Turning screen saver on..."
}

def goIdle(device: Device) = device match {
  case p: Phone => p.screenOff
  case c: Computer => c.screenSaverOn
}
```

## Sealed classes
Traits and classes can be marked sealed which means all subtypes must be declared in the same file. This assures that all subtypes are known.
```
sealed abstract class Furniture
case class Couch() extends Furniture
case class Chair() extends Furniture

def findPlaceToSit(piece: Furniture): String = piece match {
  case a: Couch => "Lie on the couch"
  case b: Chair => "Sit on the chair"
}
```
> This is useful for pattern matching because we don’t need a “catch all” case.

# Singleton object
An object is a class that has exactly one instance. It is created lazily when it is referenced, like a lazy val.

As a top-level value, an object is a singleton.

As a member of an enclosing class or as a local value, it behaves exactly like a lazy val.

## Companion objects
An object with the same name as a class is called a companion object. Conversely, the class is the object’s companion class. A companion class or object can access the private members of its companion. Use a companion object for methods and values which are not specific to instances of the companion class.
```
import scala.math._

case class Circle(radius: Double) {
  import Circle._
  def area: Double = calculateArea(radius)
}

object Circle {
  private def calculateArea(radius: Double): Double = Pi * pow(radius, 2.0)
}

val circle1 = new Circle(5.0)

circle1.area
```

# Regular expressions
Regular expressions are strings which can be used to find patterns (or lack thereof) in data. Any string can be converted to a regular expression using the .r method.
```
import scala.util.matching.Regex

val numberPattern: Regex = "[0-9]".r

numberPattern.findFirstMatchIn("awesomepassword") match {
  case Some(_) => println("Password OK")
  case None => println("Password must contain a number")
}
```

# References
- https://docs.scala-lang.org/
