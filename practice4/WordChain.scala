/*
- the format of dictionary file is carridge return separated text file that only includes alphabets.
- specs of this program are
    - decide first player with random number.
    - show any random character if the user is first player, or show any random word if computer is first player.
    - show message that encourage user to input again when user input some word that doesn't included in dictionary file. 
    - same message will be shown when the user inputs some non-alphabet characters.
    - finish program with below message when user input the word that already used.
        - message : you lose!
            - the word that you inputted has used by {you / me} at turn {number of turn}. {number of words that used} words have used in this game.
    - finish program with below message when there is no word remaining in dictionary file.
        - message : you win! {number of words that used} words have used in this game.

*/

import scala.io.Source
import java.io.FileNotFoundException
import java.io.IOException
import scala.util.Random
import scala.collection.mutable.ListBuffer
import scala.util.matching.Regex
import util.control.Breaks.break

var dictionary:ListBuffer[String] = ListBuffer()
var userDic:ListBuffer[String] = ListBuffer()
var botDic:ListBuffer[String] = ListBuffer()

try {
    // Read dictionary and store value into the map
    for(line <- Source.fromFile("dictionary.txt" ).getLines()) {
        dictionary += line.toLowerCase.capitalize
    }
} catch {
    case ex: FileNotFoundException => println("Error: FileNotFoundException")
    case ex: IOException => println("Error: IOException")
}

// Get first character of word
def getLastChar(list: ListBuffer[String]) = {
    val lastWord = list.last
    lastWord(lastWord.length - 1)
}

// Random possition of word in dictionary
val randomWord = (randomObject: Random, max: Int) => {randomObject.nextInt(max)}

// Processing of Bot
def processBot(dictionary: ListBuffer[String], botDic: ListBuffer[String], possition: Int) = {
    println(s"Bot:${dictionary(possition)}")
    botDic += dictionary(possition)
    dictionary.remove(possition)
    getLastChar(botDic)
}

def printMessageLose(user: String, turn: Int, total: Int) = {
    println("You Lose!")
    println(s"Your word that you inputted has used by ${user} at turn ${turn+1}. ${total} words have used in this game!")
}

def play(dictionary: ListBuffer[String], userDic: ListBuffer[String], botDic: ListBuffer[String], player: Boolean, lastChar: Char): Unit = {
    println("=================")

    // is there word which start with lastChar on the dictionary?
    var possition = dictionary.indexWhere(_(0) == lastChar.toUpper)
    // Check player
    if (dictionary.size > 0 && possition >= 0) {
        var newLastChar: Char = 'A'
        player match {
            case true => {
                val input = scala.io.StdIn.readLine("Your word: ").toLowerCase.capitalize
                // Check input empty
                if (input.isEmpty) {
                    println("Your input is Empty!")
                    play(dictionary, userDic, botDic, player, lastChar)
                } else {
                    // Check input is non-alphabet
                    nonAlphabetPattern.findFirstMatchIn(input) match {
                        case Some(_) => {
                            println("Your input is not Alphabet!")
                            play(dictionary, userDic, botDic, player, lastChar)
                        }
                        case None => {
                            // Check input of user is existed
                            (userDic.indexOf(input),botDic.indexOf(input)) match {
                                case (-1,-1) => {
                                    // Input again
                                    var nextPlayer = player
                                    newLastChar = lastChar

                                    // Check with last word
                                    if (input(0) != lastChar.toUpper) println("Your input is not match last character!")
                                    else {
                                        // Find possition in dictionary
                                        possition = dictionary.indexOf(input)
                                        possition match {
                                            case -1 => println("Not found in Dictionary!")
                                            case _ => {
                                                userDic += input 
                                                dictionary.remove(possition)
                                                newLastChar = getLastChar(userDic)
                                                nextPlayer = !player
                                            }
                                        }
                                    }
                                    // Repeat
                                    play(dictionary, userDic, botDic, nextPlayer, newLastChar)
                                }
                                case (-1, turn) => {
                                    printMessageLose("Bot", turn, userDic.size + botDic.size)

                                }
                                case (turn, -1) => {
                                    printMessageLose("You", turn, userDic.size + botDic.size)
                                }
                                case (_,_) => 
                            }
                        }
                    }
                }
            }
            case false => {
                newLastChar = processBot(dictionary, botDic, possition)
                // Repeat
                play(dictionary, userDic, botDic, !player, newLastChar)
            }
        }

    } else println(s"You Win with ${userDic.length} words!")
}

// Process player
var lastChar: Char = _

// Random player and possition of word
val randomObject = new Random()
var player = randomObject.nextBoolean
var possition = randomWord(randomObject, dictionary.length)
val nonAlphabetPattern: Regex = "[^a-zA-Z]".r
player match {
    case true => {
        lastChar = dictionary(possition)(0)
        println(s"Player: First character is ${lastChar}")
    }
    case false => {
        lastChar = processBot(dictionary, botDic, possition)
    }
}

// Call main function
play(dictionary, userDic, botDic, true, lastChar)

