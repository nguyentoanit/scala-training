package models

import scalikejdbc._

case class Post(id: Long, title: String, content: String)

object Post extends SQLSyntaxSupport[Post] {

  // If the table name is same as snake_case'd name of this companion object,
  // you don't need to specify tableName explicitly.
  override val tableName = "post"
  override val columns = Seq("id", "title", "content")

  val p = Post.syntax("p")

  // If you use NamedDB for this entity, override connectionPoolName
  //override val connectionPoolName = 'anotherdb

    def apply(s: SyntaxProvider[Post])(rs: WrappedResultSet): Post = apply(s.resultName)(rs)
    def apply(s: ResultName[Post])(rs: WrappedResultSet): Post = new Post(
        id = rs.get(s.id),
        title = rs.get(s.title),
        content = rs.get(s.content)
    )
    
    def findAll()(implicit session: DBSession = autoSession): List[Post] = withSQL {
        select.from(Post as p)
    }.map(Post(p)).list.apply()
}
