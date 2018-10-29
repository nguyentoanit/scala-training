package models

import scalikejdbc._

case class User(id: Long, email: String)
object User extends SQLSyntaxSupport[User] {
  override val tableName = "users"
  override val columns = Seq("id", "email")
}

