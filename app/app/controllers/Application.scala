package controllers

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import datamodel.latest.schema.tables._
import javax.inject.Inject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.mvc._
import slick.jdbc.JdbcProfile

class Application @Inject()(
  val cc: ControllerComponents,
  protected val dbConfigProvider: DatabaseConfigProvider
) extends AbstractController(cc) with HasDatabaseConfigProvider[JdbcProfile] {
  import dbConfig.profile.api._

  def index: Action[AnyContent] = Action.async { implicit request =>
    val resultingUsers: Future[Seq[UsersRow]] = dbConfig.db.run(Users.result)
    resultingUsers.map(users => Ok(views.html.index(users)))
  }
}
