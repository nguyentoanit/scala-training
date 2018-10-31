package controllers

import play.api.test._
import play.api.test.Helpers._
import org.specs2.mutable._
import org.specs2.mock.Mockito
import scalikejdbc._
import play.api.mvc._
import models.Post
import play.api.inject.guice.GuiceApplicationBuilder

object PostsControllerSpecs extends Specification with Mockito {

  "Posts Controller" >> {
    val injector = new GuiceApplicationBuilder().injector()
    val cc: ControllerComponents = injector.instanceOf[ControllerComponents]
    val controller = new PostsController(cc)
    "When send GET request to index method Return HTTP code == 200" in new WithApplication {
      val result = controller.index()(FakeRequest())
      status(result) must equalTo(200)
    }
    "When send GET request to detail method with valid post id Return HTTP code == 200" in new WithApplication {
      val result = controller.getPostByID(1)(FakeRequest())
      status(result) must equalTo(200)
    }
    "When send GET request to detail method with invalid post id Return HTTP code == 404" in new WithApplication {
      val result = controller.getPostByID(0)(FakeRequest())
      status(result) must equalTo(404)
    }
    "When send GET request to detail method with invalid post id Return \"404 Not Found!\" message" in new WithApplication {
      val result = controller.getPostByID(0)(FakeRequest())
      contentAsString(result) must contain("404 Not Found!")
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
    "When [Request(GET, /post/1)] return HTTP code == 200" in new WithApplication {
      val Some(result) = route(app, FakeRequest(GET, "/post/1"))
      status(result) must equalTo(200)
    }
    "When [Request(GET, /post/1)] return Charset is UTF-8" in new WithApplication {
      val Some(result) = route(app, FakeRequest(GET, "/post/1"))
      charset(result) must beSome("utf-8")
    }
    "When [Request(GET, /post/)] return HTTP code == 404" in new WithApplication {
      val Some(result) = route(app, FakeRequest(GET, "/post/"))
      status(result) must equalTo(404)
    }
    "When [Request(GET, /post/xxx)] return HTTP code == 400" in new WithApplication {
      val Some(result) = route(app, FakeRequest(GET, "/post/xxx"))
      status(result) must equalTo(400)
    }
    "When [Request(GET, /post/0)] return HTTP code == 404" in new WithApplication {
      val Some(result) = route(app, FakeRequest(GET, "/post/0"))
      status(result) must equalTo(404)
    }
    "When [Request(GET, /post/0)] return \"404 Not Found!\" message" in new WithApplication {
      val Some(result) = route(app, FakeRequest(GET, "/post/0"))
      contentAsString(result) must contain("404 Not Found!")
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

  "Post detail Template" >> {
    val html = views.html.post_detail(Post(1, "example@example.com", "Title Example", "Content Example"))
    "When Render Template return title of post" >> {
      contentAsString(html) must contain("Title Example")
    }
    "When Render Template return content of post" >> {
      contentAsString(html) must contain("Content Example")
    }
    "When Render Template return email of author" >> {
      contentAsString(html) must contain("example@example.com")
    }
  }
}
