package traits

import scalikejdbc._
import play.api.db.{ Databases, Database }

trait DBTestTrait {
  val driver: String = "com.mysql.jdbc.Driver"
  val url: String = "jdbc:mysql://localhost:8889/bbs-test?useSSL=false"
  val user: String = "root"
  val password: String = "root"
  val name: String = "default"
  val connection = getTestDatabase.getConnection()

  // Set test connection
  def setTestConnection() = {
    ConnectionPool.singleton(url, user, password)
  }

  // Get test database
  def getTestDatabase(): Database = Databases(
    driver = driver,
    url = url,
    name = name,
    config = Map(
      "username" -> user,
      "password" -> password
    )
  )

  // Clear data of table
  def clearData(table: String) = connection.prepareStatement("DELETE FROM " + table).execute()

}
