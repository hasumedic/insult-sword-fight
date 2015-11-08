package model

case class Opponent(insults: List[Insult], comeback: List[Comeback])

object Opponent {
  def buildFrom(fullInsults: List[FullInsult]): Opponent =
    Opponent(
      FullInsult.extractInsults(fullInsults),
      FullInsult.extractComebacks(fullInsults)
    )
}
