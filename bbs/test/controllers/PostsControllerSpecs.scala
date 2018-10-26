package controllers

import play.api.test._
import play.api.test.Helpers._
import org.specs2.mutable._
import scalikejdbc._

object PostsControllerSpecs extends Specification {

  "Posts Controller" >> {
    "HTTP code == 200" in new WithApplication {
      val stubCC = stubControllerComponents()
      val controller = new PostsController(stubCC)
      val result = controller.index()(FakeRequest())
      status(result) must equalTo(200)
    }
  }

  "Posts Route" >> {
    "GET method has HTTP code == 200" in new WithApplication {
      val Some(result) = route(app, FakeRequest(GET, "/"))
      status(result) must equalTo(200)
    }
    "Charset is UTF-8" in new WithApplication {
      val Some(result) = route(app, FakeRequest(GET, "/"))
      charset(result) must beSome("utf-8")
    }
  }

  "Posts Template" >> {
    val html = views.html.posts(List())
    "Must contain Title String" >> {
      contentAsString(html) must contain("Title")
    }
    "Must contain Content String" >> {
      contentAsString(html) must contain("Content")
    }
  }
}
