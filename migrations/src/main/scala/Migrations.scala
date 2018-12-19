import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile
import com.liyaos.forklift.slick._

object MyMigrations extends App
    with SlickMigrationCommandLineTool
    with SlickMigrationCommands
    with SlickMigrationManager
    with Codegen {
  override lazy val dbConfig = DatabaseConfig.forConfig[JdbcProfile](
    "migrations.slick.dbs.default"
  )

  MigrationSummary
  execCommands(args.toList)
}
