package model

case class Opponent(knownInsults: List[Insult], knownComebacks: List[Comeback])

object Opponent {
  def buildFrom(fullInsults: List[FullInsult]): Opponent =
    Opponent(
      FullInsult.extractInsults(fullInsults),
      FullInsult.extractComebacks(fullInsults)
    )
}
