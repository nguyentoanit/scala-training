package models

import scalikejdbc._

case class Post(id: Long, email: String, title: String, content: String)

object Post extends SQLSyntaxSupport[Post] {

  val p = Post.syntax("p")
  override val tableName = "posts"
  override val columns = Seq("id", "email", "title", "content")

  def apply(p: SyntaxProvider[Post])(rs: WrappedResultSet): Post = apply(p.resultName)(rs)
  def apply(p: ResultName[Post])(rs: WrappedResultSet): Post = new Post(
    id = rs.get(p.id),
    email = rs.get(p.email),
    title = rs.get(p.title),
    content = rs.get(p.content)
  )

  //Get all posts
  def findAll()(implicit session: DBSession = autoSession): List[Post] = withSQL {
    select.from(Post as p)
  }.map(Post(p)).list.apply()

  // Get post by post id
  def getPostByID(id: Long)(implicit session: DBSession = autoSession): Option[Post] = withSQL {
    select.from(Post as p).where.eq(p.id, id)
  }.map(Post(p)).single.apply()

}
