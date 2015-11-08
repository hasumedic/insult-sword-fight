package model

import argonaut.Argonaut._
import argonaut.CodecJson

case class FullInsult(id: Int, commonInsult: String, masterInsult: String, comeback: String)

object FullInsult {
  implicit def FullInsultCodecJson: CodecJson[FullInsult] =
    casecodec4(FullInsult.apply, FullInsult.unapply)("id", "common-insult", "master-insult", "comeback")

  def extractInsults(fullInsults: List[FullInsult]): List[Insult] = {
    fullInsults map (i => Insult(i.id, i.commonInsult))
  }

  def extractComebacks(fullInsults: List[FullInsult]): List[Comeback] = {
    fullInsults map (i => Comeback(i.id, i.comeback))
  }
}

case class Insult(id: Int, insult: String)

case class Comeback(id: Int, comeback: String)

case class Player(knownInsults: List[Insult], knownComebacks: List[Comeback])

object Player {
  def buildFrom(fullInsults: List[FullInsult]): Player = {
    new Player(
      FullInsult.extractInsults(fullInsults),
      FullInsult.extractComebacks(fullInsults)
    )
  }
}
