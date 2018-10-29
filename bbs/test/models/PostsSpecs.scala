package models

import play.api.test._
import play.api.test.Helpers._
import org.specs2.mutable._
import org.specs2.specification.AfterAll
import scalikejdbc._
import traits.DBTestTrait
import play.api.db.evolutions.Evolutions

object PostsSpecs extends Specification with DBTestTrait with AfterAll {
  sequential
  val database = getTestDatabase()
  Evolutions.applyEvolutions(database)
  setTestConnection()

  // Clean Evolutions in Test database.
  def afterAll() = {
    Evolutions.cleanupEvolutions(database)
  }

  "Posts Model Test With data" >> {
    "When get all Posts then result type is List[Posts]" >> {
      val posts = Posts.findAll()
      val result = posts match {
        case _: List[Posts] => true
        case _              => false
      }
      result must beTrue
    }
    "When get a Post then result type is Option[Posts]" >> {
      val post = Posts.find(1)
      val result = post match {
        case _: Option[Posts] => true
        case _                => false
      }
      result must beTrue
    }
  }

  "Posts Model Test No data" >> {
    clearData("posts")
    val posts = Posts.findAll()
    "When get all Posts then Records's amount == 0" >> {
      posts.size == 0
    }
    "When get all Posts then result type is List[Posts]" >> {
      val result = posts match {
        case _: List[Posts] => true
        case _              => false
      }
      result must beTrue
    }
    val post = Posts.find(1)
    "When get a Post then Records's amount == 0" >> {
      post.size == 0
    }
    "When get a Post then result is None" >> {
      val result = post match {
        case Some(_) => false
        case None    => true
      }
      result must beTrue
    }
  }
}
