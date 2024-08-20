package name.lemerdy.sebastian.tapir

import io.circe.generic.auto.given
import sttp.apispec.openapi.circe.yaml.given
import sttp.tapir.*
import sttp.tapir.docs.openapi.OpenAPIDocsInterpreter
import sttp.tapir.generic.Configuration
import sttp.tapir.generic.auto.given
import sttp.tapir.json.circe.jsonBody

import java.nio.file.{Files, Paths}
import scala.util.Using

@main def run(): Unit =

  sealed trait Entity:
    def name: String

  case class Person(name: String, age: Int) extends Entity
  case class Organization(name: String) extends Entity

  implicit val tapirSchemaConfiguration: Configuration =
    Configuration.default.withDiscriminator("kind").withKebabCaseDiscriminatorValues

  val personEndpoint = endpoint.get
    .in("api" / "user" / path["String"]("userId"))
    .in(jsonBody[Person])

  val entityEndpoint = endpoint.get
    .in("api" / "entity" / path["String"]("entityId"))
    .in(jsonBody[Entity])

  val docs: String =
    OpenAPIDocsInterpreter().toOpenAPI(List(personEndpoint, entityEndpoint), "title", "1.0").toYaml

  Using(Files.newBufferedWriter(Paths.get("src/main/resources/openapi.yaml"))): out =>
    out.write(docs)
