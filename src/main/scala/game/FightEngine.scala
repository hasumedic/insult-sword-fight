package game

import model.{Fighter, Opponent, Player, InsultRepository}

class FightEngine(repository: InsultRepository) {

  def playerWantsToFight(): Boolean = {
    val response = askPlayerForAFight()
    if (response.toLower == 'y') true else false
  }

  def findOpponent(): Opponent = {
    Opponent.buildFrom(repository.takeRandom(5))
  }

  def startFight(player: Player): Player = {
    val opponent = findOpponent()
    Fight(player, opponent).start()
  }

  private def didNotUnderstand(): Char = {
    println("Did you drink too much Grog? Let me ask you again...")
    askPlayerForAFight()
  }

  private def askPlayerForAFight(): Char = {
    println("Would you like to start a fight with a pirate? [Y/N]")
    val response = scala.io.StdIn.readChar()
    response.toLower match {
      case 'y' => response
      case 'n' => response
      case _ => didNotUnderstand()
    }
  }

}

case class Fight(var player: Player, opponent: Opponent) {

  private def RoundsToWin = 3

  var playerState = FightState(player, 0, hasUpperHand = false)
  var opponentState = FightState(opponent, 0, hasUpperHand = true)

  private def thereIsWinner = playerState.score == RoundsToWin || opponentState.score == RoundsToWin

  def upperHandLosesRound(): Unit = {
    if (playerState.hasUpperHand) {
      println(Console.RED + "You missed this one!" + Console.RESET)
      playerState = playerState.copy(fighter = player, hasUpperHand = false)
      opponentState = opponentState.copy(score = opponentState.score + 1, hasUpperHand = true)
    } else {
      println(Console.GREEN + "You got this one!" + Console.RESET)
      playerState = playerState.copy(fighter = player, score = playerState.score + 1, hasUpperHand = true)
      opponentState = opponentState.copy(hasUpperHand = false)
    }
  }

  def upperHandWinsRound(): Unit = {
    if (playerState.hasUpperHand) {
      println(Console.GREEN + "You got this one!" + Console.RESET)
      playerState = playerState.copy(fighter = player, score = playerState.score + 1)
    } else {
      println(Console.RED + "You missed this one!" + Console.RESET)
      opponentState = opponentState.copy(score = opponentState.score + 1)
    }
  }

  private def nextRound(): Unit = {
    val insult = if (playerState.hasUpperHand) player.insult() else opponent.insult()
    val comeback = if (playerState.hasUpperHand) opponent.comeback(insult) else player.comeback(insult)

    player = player.learn(insult, comeback)

    if (insult.matches(comeback)) upperHandLosesRound()
    else upperHandWinsRound()
  }

  def start(): Player = {
    while (!thereIsWinner) {
      nextRound()
    }

    player
  }
}

case class FightState(fighter: Fighter, score: Int, hasUpperHand: Boolean)
