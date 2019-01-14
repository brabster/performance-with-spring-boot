package hello

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

class LoadTest extends Simulation {

  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl("http://localhost:8080")

  object HelloWorldResource {
    val get: ChainBuilder = exec(http("HelloWorld")
      .get("/")
      .basicAuth("user", "24gh39ugh0"))
  }

  val myScenario: ScenarioBuilder = scenario("RampUpUsers")
    .exec(HelloWorldResource.get)

  setUp(myScenario.inject(
    incrementUsersPerSec(20)
      .times(5)
      .eachLevelLasting(5 seconds)
      .separatedByRampsLasting(5 seconds)
      .startingFrom(20)
  )).protocols(httpProtocol)
    .assertions(global.successfulRequests.percent.is(100))
}
