package models

import scalikejdbc._

case class Post(id: Long, title: String, content: String, userid: Long, email: String)
case class User(id: Long, email: String)

object User extends SQLSyntaxSupport[User]

object Post extends SQLSyntaxSupport[Post] {

  val (p,u) = (Post.syntax("p"), User.syntax("u"))

  def apply(p: SyntaxProvider[Post], u:SyntaxProvider[User])(rs: WrappedResultSet): Post = apply(p.resultName, u.resultName)(rs)
  def apply(p: ResultName[Post], u: ResultName[User])(rs: WrappedResultSet): Post = new Post(
    id = rs.get(p.id),
    title = rs.get(p.title),
    content = rs.get(p.content),
    userid = rs.get(p.userid),
    email = rs.get(u.email)
  )

  // Find posts
  def find(id: Long = 0)(implicit session: DBSession = autoSession): List[Post] = id match {
    case 0 => withSQL {
        select.from(Post as p).leftJoin(User as u).on(p.userid, u.id)
      }.map(Post(p, u)).list.apply()
    case _ => withSQL {
        select.from(Post as p).leftJoin(User as u).on(p.userid, u.id).where.eq(p.id, id)
      }.map(Post(p, u)).list.apply()
  }
}
