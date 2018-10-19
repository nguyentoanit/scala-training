package controllers

import play.api.test._
import play.api.test.Helpers._
import org.specs2.mutable._

object PostTest extends Specification {
  // Post Controller test
  "PostController#List" in new WithApplication {
    val controller = app.injector.instanceOf[controllers.PostController]
    val result = controller.index()(FakeRequest())

    status(result) must equalTo(OK)
    // contentAsString(result) must contain("Title")
  }

  // Post Template test
  "render post template" in new WithApplication {
    val html = views.html.post(List())
    contentAsString(html) must contain("Title")
    contentAsString(html) must contain("Content")
  }
}