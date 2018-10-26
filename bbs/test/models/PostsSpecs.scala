package models

import play.api.test._
import play.api.test.Helpers._
import org.specs2.mutable._
import org.specs2.specification.AfterAll
import scalikejdbc._
import scalikejdbc.config._
import scalikejdbc.specs2.AutoRollback
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
    val posts = Posts.findAll()
    "Return type is List[Posts]" >> {
      val result = posts match {
        case _: List[Posts] => true
        case _              => false
      }
      result must beTrue
    }
  }

  "Posts Model Test No data" >> {
    clearData("posts")
    val posts = Posts.findAll()
    "Records's number == 0" >> {
      posts.size == 0
    }
    "Return type is List[Posts]" >> {
      val result = posts match {
        case _: List[Posts] => true
        case _              => false
      }
      result must beTrue
    }
  }
}
