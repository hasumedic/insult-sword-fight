package model

import argonaut._, Argonaut._
import scala.io.Source

case class FullInsult(id: Int, commonInsult: String, masterInsult: String, comeback: String)

object FullInsult {
  implicit def FullInsultCodecJson: CodecJson[FullInsult] =
    casecodec4(FullInsult.apply, FullInsult.unapply)("id", "common-insult", "master-insult", "comeback")
}

class InsultRepository {
  val insults = {
    val json = Source.fromURL(getClass.getResource("/monkey-island.json")).mkString
    json.decodeOption[List[FullInsult]].getOrElse(Nil)
  }
}
