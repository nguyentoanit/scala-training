package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import scalikejdbc._
import models._
import scala.concurrent._
import ExecutionContext.Implicits.global
import play.api.data._
import play.api.data.Forms._
import play.api.data.validation.Constraints._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's Post page.
 */
@Singleton
class PostsController @Inject() (cc: ControllerComponents) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    val posts = Post.findAll()
    Ok(views.html.posts(posts))
  }

  // Get Post ID and show detail of Post
  def getPostByID(id: Long) = Action.async { implicit request: Request[AnyContent] =>
    Future {
      Post.getPostByID(id)
    } map {
      post =>
        {
          val Some(p) = post
          Ok(views.html.post_detail(p))
        }
    } recover {
      case e: Exception => NotFound("404 Not Found!")
    }
  }

  def form() = Action { implicit request =>
    Ok(views.html.post_form())
  }

  case class PostData(email: String, title: String, content: String)
  val postForm = Form(
    mapping(
      "email" -> text,
      "title" -> text,
      "content" -> text
    )(PostData.apply)(PostData.unapply)
  )

  def create() = Action(parse.form(postForm)) { implicit request =>
    val postData = request.body

    Ok("OK:" + postData.email + ", " + postData.title + ", " + postData.content)
    // Redirect(routes.Application.home(id))
  }
}
