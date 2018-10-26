package controllers

import play.api.test._
import play.api.test.Helpers._
import org.specs2.mutable._
import scalikejdbc._

object PostsControllerSpecs extends Specification {

  "Posts Controller" >> {
    "When [Request(GET, /)] return HTTP code == 200" in new WithApplication {
      val stubCC = stubControllerComponents()
      val controller = new PostsController(stubCC)
      val result = controller.index()(FakeRequest())
      status(result) must equalTo(200)
    }
  }

  "Posts Route" >> {
    "When [Request(GET, /)] return HTTP code == 200" in new WithApplication {
      val Some(result) = route(app, FakeRequest(GET, "/"))
      status(result) must equalTo(200)
    }
    "When [Request(GET, /)] return Charset is UTF-8" in new WithApplication {
      val Some(result) = route(app, FakeRequest(GET, "/"))
      charset(result) must beSome("utf-8")
    }
  }

  "Posts Template" >> {
    val html = views.html.posts(List())
    "When Render Template return Title String" >> {
      contentAsString(html) must contain("Title")
    }
    "When Render Template return Content String" >> {
      contentAsString(html) must contain("Content")
    }
  }
}
