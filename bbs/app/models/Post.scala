package models

import scalikejdbc._

case class Post(id: Long, title: String, content: String, user_id: Long, email: String)

object Post extends SQLSyntaxSupport[Post] {

  val (p, u) = (Post.syntax("p"), User.syntax("u"))
  override val tableName = "posts"
  override val columns = Seq("id", "user_id", "title", "content")

  def apply(p: SyntaxProvider[Post], u: SyntaxProvider[User])(rs: WrappedResultSet): Post = apply(p.resultName, u.resultName)(rs)
  def apply(p: ResultName[Post], u: ResultName[User])(rs: WrappedResultSet): Post = new Post(
    id = rs.get(p.id),
    title = rs.get(p.title),
    content = rs.get(p.content),
    user_id = rs.get(p.user_id),
    email = rs.get(u.email)
  )

  //Get all posts
  def findAll()(implicit session: DBSession = autoSession): List[Post] = withSQL {
    select.from(Post as p).leftJoin(User as u).on(p.user_id, u.id)
  }.map(Post(p, u)).list.apply()

  // Get post by post id
  def getPostByID(id: Long)(implicit session: DBSession = autoSession): Option[Post] = withSQL {
    select.from(Post as p).leftJoin(User as u).on(p.user_id, u.id).where.eq(p.id, id)
  }.map(Post(p, u)).single.apply()

}
