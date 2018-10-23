package models

import play.api.test._
import play.api.test.Helpers._
import org.specs2.mutable._
import models._
import scalikejdbc._
import scalikejdbc.config._
import scalikejdbc.specs2.AutoRollback

object PostTest2 extends Specification {
  sequential
  val url = "jdbc:mysql://localhost:8889/bbs-test"
  val user = "root"
  val password = "root"
  ConnectionPool.singleton(url, user, password)

  "Post Model Test no data" in new AutoRollback {
    val posts = Posts.findAll()
    val result = posts match {
      case _: List[Posts] => true
      case _              => false
    }
    posts.size mustEqual 0
    result must beTrue
  }
}
