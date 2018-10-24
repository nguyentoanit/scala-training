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
      val Some(result1) = route(app, FakeRequest(GET, "/"))
      status(result1) must equalTo(200)
    }
    "POST method has HTTP code == 404" in new WithApplication {
      val Some(result2) = route(app, FakeRequest(POST, "/"))
      status(result2) must equalTo(404)
    }
    "PUT method has HTTP code == 404" in new WithApplication {
      val Some(result3) = route(app, FakeRequest(PUT, "/"))
      status(result3) must equalTo(404)
    }
    "DELETE method has HTTP code == 404" in new WithApplication {
      val Some(result4) = route(app, FakeRequest(DELETE, "/"))
      status(result4) must equalTo(404)
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
