package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import scalikejdbc._
import models._

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
    val posts = Posts.findAll()
    Ok(views.html.posts(posts))
  }

  // Get Post ID and show detail of Post
  def detail(id: Long) = Action { implicit request: Request[AnyContent] =>
    // Find post by ID
    val Some(post) = Posts.find(id)
    Ok(views.html.post_detail(post))
  }
}
