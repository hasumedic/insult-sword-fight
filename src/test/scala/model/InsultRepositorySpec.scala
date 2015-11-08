package model

import org.scalatest._

class InsultRepositorySpec extends FlatSpec with Matchers {

  def json3Insults = "[" +
    "{\"id\": 1, \"common-insult\": \"hey\", \"master-insult\": \"hey-master\", \"comeback\": \"comeback\"}," +
    "{\"id\": 2, \"common-insult\": \"hey2\", \"master-insult\": \"hey-master2\", \"comeback\": \"comeback2\"}," +
    "{\"id\": 3, \"common-insult\": \"hey3\", \"master-insult\": \"hey-master3\", \"comeback\": \"comeback3\"}" +
    "]"

  "An InsultRepository" should "can be initialized with an empty list of insults" in {
    val repository = new InsultRepository("")
    repository.insults.size should be (0)
  }

  it should "initialize a list of insults from a JSON string" in {
    val json = json3Insults
    val repository = new InsultRepository(json)
    repository.insults.size should be(3)
  }
}
