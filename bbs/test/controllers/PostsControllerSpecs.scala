package controllers

import play.api.test._
import play.api.test.Helpers._
import org.specs2.mutable._
import org.specs2.specification.AfterAll
import org.specs2.control.Debug
import scalikejdbc._
import play.api.mvc._
import models.Post
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.db.evolutions.Evolutions
import play.api.test.CSRFTokenHelper._
import traits.DBTestTrait
import scalikejdbc.specs2.mutable.AutoRollback
import org.specs2.mock.Mockito

object PostsControllerSpecs extends Specification with DBTestTrait with AfterAll with Mockito {

  val database = getTestDatabase()
  Evolutions.applyEvolutions(database)
  setTestConnection()

  def afterAll() = {
    Evolutions.cleanupEvolutions(database)
    database.shutdown()
  }
  "Posts Controller" >> {
    val injector = new GuiceApplicationBuilder().injector()
    val cc: ControllerComponents = injector.instanceOf[ControllerComponents]
    val controller = new PostsController(cc)
    "index method" >> {
      "When send GET request Return HTTP code == 200" in new WithApplication {
        val result = controller.index()(FakeRequest())
        status(result) must equalTo(200)
      }
    }
    "getByID method" >> {
      "When send GET request with valid post id Return HTTP code == 200" in new WithApplication {
        val result = controller.getByID(1)(FakeRequest())
        status(result) must equalTo(200)
      }
      "When send GET request with invalid post id Return HTTP code == 404" in new WithApplication {
        val result = controller.getByID(0)(FakeRequest())
        status(result) must equalTo(404)
      }
      "When send GET request with invalid post id Return \"404 Not Found!\" message" in new WithApplication {
        val result = controller.getByID(0)(FakeRequest())
        contentAsString(result) must contain("404 Not Found!")
      }
    }
    "form method" >> {
      "When send GET request Return HTTP code == 200" in new AutoRollback {
        val result = controller.form()(FakeRequest("GET", "/").withCSRFToken)
        status(result) must equalTo(200)
      }
    }
    "create method" >> {
      "When send POST request with post form Then insert a new record into DB" in new WithApplication {
        // implicit val mat = NoMaterializer.withNamePrefix("")
        val old = Post.findAll().size.toString
        val request = FakeRequest("POST", "/post/create").withFormUrlEncodedBody("email" -> "example@example.com", "title" -> "example", "content" -> "example").withCSRFToken
        val result = call(controller.create, request)
        val newVal = Post.findAll().size.toString
        Debug.pp(old)
        Debug.pp(newVal)
        status(result) must equalTo(303)
      }
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
