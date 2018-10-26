package models

import scalikejdbc._

case class Posts(id: Long, title: String, content: String, user_id: Long, email: String)
case class Users(id: Long, email: String)

object Users extends SQLSyntaxSupport[Users]

object Posts extends SQLSyntaxSupport[Posts] {

  val (p, u) = (Posts.syntax("p"), Users.syntax("u"))

  def apply(p: SyntaxProvider[Posts], u: SyntaxProvider[Users])(rs: WrappedResultSet): Posts = apply(p.resultName, u.resultName)(rs)
  def apply(p: ResultName[Posts], u: ResultName[Users])(rs: WrappedResultSet): Posts = new Posts(
    id = rs.get(p.id),
    title = rs.get(p.title),
    content = rs.get(p.content),
    user_id = rs.get(p.user_id),
    email = rs.get(u.email)
  )

  //Get all posts
  def findAll()(implicit session: DBSession = autoSession): List[Posts] = withSQL {
    select.from(Posts as p).leftJoin(Users as u).on(p.user_id, u.id)
  }.map(Posts(p, u)).list.apply()

  // Get post by post id
  def find(id: Long)(implicit session: DBSession = autoSession): Option[Posts] = withSQL {
    select.from(Posts as p).leftJoin(Users as u).on(p.user_id, u.id).where.eq(p.id, id)
  }.map(Posts(p, u)).single.apply()

}
