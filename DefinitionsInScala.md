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

# Extractor object
An extractor object is an object with an unapply method. Whereas the apply method is like a constructor which takes arguments and creates an object, the unapply takes an object and tries to give back the arguments. This is most often used in pattern matching and partial functions.

```
import scala.util.Random

object CustomerID {

  def apply(name: String) = s"$name--${Random.nextLong}"

  def unapply(customerID: String): Option[String] = {
    val stringArray: Array[String] = customerID.split("--")
    if (stringArray.tail.nonEmpty) Some(stringArray.head) else None
  }
}

val customer1ID = CustomerID("Sukyoung")  // Sukyoung--23098234908
customer1ID match {
  case CustomerID(name) => println(name)  // prints Sukyoung
  case _ => println("Could not extract a CustomerID")
}
```
> The apply method creates a CustomerID string from a name. The unapply does the inverse to get the name back. When we call CustomerID("Sukyoung"), this is shorthand syntax for calling CustomerID.apply("Sukyoung"). When we call case CustomerID(name) => println(name), we’re calling the unapply method.

# Generic class
Generic classes are classes which take a type as a parameter. They are particularly useful for collection classes.

Generic classes take a type as a parameter within square brackets []. One convention is to use the letter A as type parameter identifier, though any parameter name may be used.

```
class Stack[A] {
  private var elements: List[A] = Nil
  def push(x: A) { elements = x :: elements }
  def peek: A = elements.head
  def pop(): A = {
    val currentTop = peek
    elements = elements.tail
    currentTop
  }
}

val stack = new Stack[Int]
stack.push(1)
stack.push(2)
println(stack.pop)  // prints 2
println(stack.pop)  // prints 1
```

# Variance
Variance is the correlation of subtyping relationships of complex types and the subtyping relationships of their component types. Scala supports variance annotations of type parameters of generic classes, to allow them to be covariant, contravariant, or invariant if no annotations are used. The use of variance in the type system allows us to make intuitive connections between complex types, whereas the lack of variance can restrict the reuse of a class abstraction.

```
class Foo[+A] // A covariant class
class Bar[-A] // A contravariant class
class Baz[A]  // An invariant class
```

## Covariance
If S is subtype of T, then List[S] is also subtype of List[T]

```
class Animal[+T](val animial:T)

class Dog
class Puppy extends Dog

class AnimalCarer(val dog:Animal[Dog])

object ScalaCovarianceTest{
  def main(args: Array[String]) {
    val puppy = new Puppy
    val dog = new Dog

    val puppyAnimal:Animal[Puppy] = new Animal[Puppy](puppy)
    val dogAnimal:Animal[Dog] = new Animal[Dog](dog)

    val dogCarer = new AnimalCarer(dogAnimal)
    val puppyCarer = new AnimalCarer(puppyAnimal)

    println("Done.")
  }
}
```

## Contravariant
If S is subtype of T, then List[T] is also subtype of List[S]

```

abstract class Type [-T]{
  def typeName : Unit
}

class SuperType extends Type[AnyVal]{
  override def typeName: Unit = {
    println("SuperType")
  }
}
class SubType extends Type[Int]{
  override def typeName: Unit = {
    println("SubType")
  }
}

class TypeCarer{
  def display(t: Type[Int]){
    t.typeName
  }
}

object ScalaContravarianceTest {

  def main(args: Array[String]) {
    val superType = new SuperType
    val subType = new SubType

    val typeCarer = new TypeCarer

    typeCarer.display(subType)
    typeCarer.display(superType)
  }

}
```
> As we define Contravariance in Type[-T], it works well. TypeCarer.display() is defined with Type[Int] i.e. SubType, but still it accepts Type[AnyVal] because Scala Contravariance subtyping.

## Invariance
Generic classes in Scala are invariant by default. This means that they are neither covariant nor contravariant. In the context of the following example, Container class is invariant. A Container[Cat] is not a Container[Animal], nor is the reverse true.

# Upper Type Bounds
In Scala, type parameters and abstract types may be constrained by a type bound. Such type bounds limit the concrete values of the type variables and possibly reveal more information about the members of such types. An upper type bound T <: A declares that type variable T refers to a subtype of type A

```
abstract class Animal {
 def name: String
}

abstract class Pet extends Animal {}

class Cat extends Pet {
  override def name: String = "Cat"
}

class Dog extends Pet {
  override def name: String = "Dog"
}

class Lion extends Animal {
  override def name: String = "Lion"
}

class PetContainer[P <: Pet](p: P) {
  def pet: P = p
}

val dogContainer = new PetContainer[Dog](new Dog)
val catContainer = new PetContainer[Cat](new Cat)
```

# Lower Type Bounds
While upper type bounds limit a type to a subtype of another type, lower type bounds declare a type to be a supertype of another type. The term B >: A expresses that the type parameter B or the abstract type B refer to a supertype of type A. In most cases, A will be the type parameter of the class and B will be the type parameter of a method.

# Abstract Types
Traits and abstract classes can have an abstract type member. This means that the concrete implementations define the actual type. 

```
trait Buffer {
  type T
  val element: T
}
```

# Compound Types
Sometimes it is necessary to express that the type of an object is a subtype of several other types. In Scala this can be expressed with the help of compound types, which are intersections of object types.

```
def cloneAndReset(obj: Cloneable with Resetable): Cloneable = {
  //...
}
```

# Self-type
Self-types are a way to declare that a trait must be mixed into another trait, even though it doesn’t directly extend it. That makes the members of the dependency available without imports.

A self-type is a way to narrow the type of this or another identifier that aliases this. The syntax looks like normal function syntax but means something entirely different.

```
trait User {
  def username: String
}

trait Tweeter {
  this: User =>  // reassign this
  def tweet(tweetText: String) = println(s"$username: $tweetText")
}

class VerifiedTweeter(val username_ : String) extends Tweeter with User {  // We mixin User because Tweeter required it
	def username = s"real $username_"
}

val realBeyoncé = new VerifiedTweeter("Beyoncé")
realBeyoncé.tweet("Just spilled my glass of lemonade")  // prints "real Beyoncé: Just spilled my glass of lemonade"
```
Because we said this: User => in trait Tweeter, now the variable username is in scope for the tweet method. This also means that since VerifiedTweeter extends Tweeter, it must also mix-in User (using with User).

# Implicit Parameters
A method can have an implicit parameter list, marked by the implicit keyword at the start of the parameter list. If the parameters in that parameter list are not passed as usual, Scala will look if it can get an implicit value of the correct type, and if it can, pass it automatically.

The places Scala will look for these parameters fall into two categories:
- Scala will first look for implicit definitions and implicit parameters that can be accessed directly (without a prefix) at the point the method with the implicit parameter block is called.
- Then it looks for members marked implicit in all the companion objects associated with the implicit candidate type.

```
def multiply(implicit by: Int): Int = 10 * by
implicit val multiplier: Int = 2
println(multiply) // 20
```

# Implicit Conversions

```
class a {
  implicit def add(x: Int, y: Int): Int = x+y
  implicit def add(x: String, y: String): String = x concat y
}

val obj = new a()
println(obj.add(1,2)) // 3
println(obj.add("Nguyen","Toan")) // NguyenToan
```

You can only use implicit once in a parameter list and all parameters following it will be implicit. 

```
def example1(implicit x: Int)                       // x is implicit
def example2(implicit x: Int, y: Int)               // x and y are implicit
def example3(x: Int, implicit y: Int)               // wont compile 
def example4(x: Int)(implicit y: Int)               // only y is implicit
def example5(implicit x: Int)(y: Int)               // wont compile
def example6(implicit x: Int)(implicit y: Int)      // wont compile
```

# Type Inference
The Scala compiler can often infer the type of an expression so you don’t have to declare it explicitly.

```
val businessName = "Montreux Jazz Café"
```

For recursive methods, the compiler is not able to infer a result type. Here is a program which will fail the compiler for this reason:

```
def fac(n: Int) = if (n == 0) 1 else n * fac(n - 1)
```

It is also not compulsory to specify type parameters when polymorphic methods are called or generic classes are instantiated. The Scala compiler will infer such missing type parameters from the context and from the types of the actual method/constructor parameters.

```
case class MyPair[A, B](x: A, y: B);
val p = MyPair(1, "scala") // type: MyPair[Int, String]

def id[T](x: T) = x
val q = id(1)              // type: Int
```

# References
- https://docs.scala-lang.org/
