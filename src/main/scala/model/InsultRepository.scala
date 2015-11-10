package model

import argonaut._, Argonaut._
import scala.util._

class InsultRepository(json: String) {

  def insults: List[FullInsult] = {
    json.decodeOption[List[FullInsult]].getOrElse(Nil)
  }

  def takeRandom(howMany: Int): List[FullInsult] = {
    Random.shuffle(insults).take(howMany)
  }
}
