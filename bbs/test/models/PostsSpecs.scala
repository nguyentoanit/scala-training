package models

import play.api.test._
import play.api.test.Helpers._
import org.specs2.mutable._
import scalikejdbc._
import scalikejdbc.config._
import scalikejdbc.specs2.AutoRollback

object PostsSpecs extends Specification {
  sequential
  val url = "jdbc:mysql://localhost:8889/bbs-test?useSSL=false"
  val user = "root"
  val password = "root"
  ConnectionPool.singleton(url, user, password)

  "Posts Model Test no data" >> {
    "Return type is List[Posts]" in new AutoRollback {
      val posts = Posts.findAll()
      val result = posts match {
        case _: List[Posts] => true
        case _              => false
      }
      result must beTrue
    }
  }

  "Posts Model Test with data" >> {
    "Return type is List[Posts]" in new AutoRollback {
      for (i <- 1 to 10) Posts.create(Posts(i, i, "Title" + i, "Content" + i))
      val posts = Posts.findAll()
      val result = posts match {
        case _: List[Posts] => true
        case _              => false
      }
      result must beTrue
    }
  }
}
