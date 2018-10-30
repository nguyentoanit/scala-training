package models

import play.api.test._
import play.api.test.Helpers._
import org.specs2.mutable._
import org.specs2.specification.AfterAll
import scalikejdbc._
import traits.DBTestTrait
import play.api.db.evolutions.Evolutions

object PostSpecs extends Specification with DBTestTrait with AfterAll {
  sequential
  val database = getTestDatabase()
  Evolutions.applyEvolutions(database)
  setTestConnection()

  // Clean Evolutions in Test database.
  def afterAll() = {
    Evolutions.cleanupEvolutions(database)
  }

  "Post Model Test With data" >> {
    "When get all Posts then result type is List[Post]" >> {
      val posts = Post.findAll()
      posts must beAnInstanceOf[List[Post]]
    }
    "When get a Post then result type is Option[Post]" >> {
      val post = Post.getPostByID(1)
      post must beAnInstanceOf[Option[Post]]
    }
  }

  "Post Model Test No data" >> {
    clearData("posts")
    val posts = Post.findAll()
    "When get all Posts then Records's amount == 0" >> {
      posts.size == 0
    }
    "When get all Posts then result type is List[Post]" >> {
      posts must beAnInstanceOf[List[Post]]
    }
    val post = Post.getPostByID(1)
    "When get a Post then Records's amount == 0" >> {
      post.size == 0
    }
    "When get a Post then result is None" >> {
      post must beNone
    }
  }
}
