package models

import scalikejdbc._

case class Post(id: Long, email: String, title: String, content: String)

object Post extends SQLSyntaxSupport[Post] {

  val p = Post.syntax("p")
  override val tableName = "posts"
  override val columns = Seq("id", "email", "title", "content")

  def apply(p: SyntaxProvider[Post])(rs: WrappedResultSet): Post = new Post(
    id = rs.get(p.resultName.id),
    email = rs.get(p.resultName.email),
    title = rs.get(p.resultName.title),
    content = rs.get(p.resultName.content)
  )

  //Get all posts
  def findAll()(implicit session: DBSession = autoSession): List[Post] = withSQL {
    select.from(Post as p)
  }.map(Post(p)).list.apply()

  // Get post by post id
  def getByID(id: Long)(implicit session: DBSession = autoSession): Option[Post] = withSQL {
    select.from(Post as p).where.eq(p.id, id)
  }.map(Post(p)).single.apply()

  def create(p: Post)(implicit session: DBSession = autoSession) = withSQL {
    val c = Post.column
    insert.into(Post).columns(c.email, c.title, c.content).values(p.email, p.title, p.content)
  }.update.apply()
}
