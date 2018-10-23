package controllers

import play.api.test._
import play.api.test.Helpers._
import org.specs2.mutable._
import models._
import scalikejdbc._

object PostTest1 extends Specification {

  "Post Controller Test" in new WithApplication {
    val controller = app.injector.instanceOf[controllers.PostController]
    val result = controller.index()(FakeRequest())

    status(result) must equalTo(OK)
  }

  "Post Controller Test system error" in new WithApplication {
    val controller = app.injector.instanceOf[controllers.PostController]
    val result = controller.index()(FakeRequest(GET, "/adjflajkdfjkdf"))

    status(result) must equalTo(OK)
  }

  "Posts Template Test" in new WithApplication {
    val html = views.html.post(List())
    contentAsString(html) must contain("Title")
    contentAsString(html) must contain("Content")
  }

  "Posts Model Test with data" in new WithApplication {
    val posts = Posts.findAll()
    val result = posts match {
      case _: List[Posts] => true
      case _              => false
    }
    result must beTrue
  }
}
