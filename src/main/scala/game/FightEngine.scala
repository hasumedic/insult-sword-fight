package game

import model.{Opponent, Player, InsultRepository}

class FightEngine(repository: InsultRepository) {

  def playerWantsToFight(): Boolean = {
    val response = askPlayerForAFight()
    if (response == 'Y') true else false
  }

  def findOpponent(): Opponent = {
    Opponent.buildFrom(repository.takeRandom(3))
  }

  def startFight(player: Player): Player = {
    val opponent = findOpponent()
    Fight(player, opponent)
  }

  private def didNotUnderstand(): Char = {
    println("Did you drink too much Grog? Let me ask you again...")
    askPlayerForAFight()
  }

  private def askPlayerForAFight(): Char = {
    println("Would you like to start a fight with a pirate? [Y/N]")
    val response = scala.io.StdIn.readChar()
    response match {
      case 'Y' => response
      case 'N' => response
      case _ => didNotUnderstand()
    }
  }

}
