package controllers

import javax.inject.Inject

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import slick.driver.JdbcProfile
import play.api._
import play.api.mvc._
import play.api.db.slick.DatabaseConfigProvider
import datamodel.latest.schema.tables._
import slick.driver.H2Driver.api._

class Application @Inject()(dbConfigProvider: DatabaseConfigProvider)
    extends Controller {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  def index = Action.async { implicit request =>
    val resultingUsers: Future[Seq[UsersRow]] = dbConfig.db.run(Users.result)
    resultingUsers.map(users => Ok(views.html.index(users)))
  }
}
