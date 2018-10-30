package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import scalikejdbc._
import models._
import scala.concurrent._
import ExecutionContext.Implicits.global

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
}
