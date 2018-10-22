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
class PostController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
      // Find all posts
      val posts = Post.find()
      Ok(views.html.post(posts))
  }

  // Get Post ID and show detail of Post
  def detail(id: Long) = Action { implicit request: Request[AnyContent] =>
    // Find post by ID
    val post = Post.find(id)

    // Check size of post
    val template = if (post.size == 1) views.html.post_detail(post) else views.html.code404()
    Ok(template)
  }
}
