package model

// These two classes look very very similar, they might be actually the same thing?

case class Player(knownInsults: List[Insult], knownComebacks: List[Comeback])

object Player {
  def buildFrom(fullInsults: List[FullInsult]): Player = {
    new Player(
      FullInsult.extractInsults(fullInsults),
      FullInsult.extractComebacks(fullInsults)
    )
  }
}

case class Opponent(knownInsults: List[Insult], knownComebacks: List[Comeback])

object Opponent {
  def buildFrom(fullInsults: List[FullInsult]): Opponent =
    Opponent(
      FullInsult.extractInsults(fullInsults),
      FullInsult.extractComebacks(fullInsults)
    )
}
