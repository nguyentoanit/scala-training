package models

import scalikejdbc._

case class Posts(id: Long, user_id: Long, title: String, content: String)

object Posts extends SQLSyntaxSupport[Posts] {

  // If the table name is same as snake_case'd name of this companion object,
  // you don't need to specify tableName explicitly.
  override val tableName = "posts"
  override val columns = Seq("id", "user_id", "title", "content")

  val p = Posts.syntax("p")

  // If you use NamedDB for this entity, override connectionPoolName
  //override val connectionPoolName = 'anotherdb

  def apply(s: SyntaxProvider[Posts])(rs: WrappedResultSet): Posts = apply(s.resultName)(rs)
  def apply(s: ResultName[Posts])(rs: WrappedResultSet): Posts = new Posts(
    id = rs.get(s.id),
    user_id = rs.get(s.user_id),
    title = rs.get(s.title),
    content = rs.get(s.content))

  def findAll()(implicit session: DBSession = autoSession): List[Posts] = sql"SELECT ${p.result.*} FROM ${Posts.as(p)}".map(Posts(p)).list.apply()

  def create(p: Posts)(implicit session: DBSession = autoSession) = withSQL {
    insert.into(Posts).namedValues(
      column.user_id -> p.user_id,
      column.title -> p.title,
      column.content -> p.content
    )
  }.update.apply()
}
