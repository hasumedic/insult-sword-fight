package game

import model.{Fighter, Opponent, Player, InsultRepository}

class FightEngine(repository: InsultRepository) {

  def startFight(player: Player): Player = {
    val opponent = findOpponent()
    Fight(player, opponent).start().player
  }

  def fightMaster(player: Player): FightResult = {
    val master = findMaster()
    FightMaster(player, master).start()
  }

  private def findMaster(): Opponent = {
    Opponent.buildMaster(repository.takeMasterInsults)
  }

  private def findOpponent(): Opponent = {
    Opponent.buildFrom(repository.takeRandom(5))
  }
}

case class FightState(fighter: Fighter, score: Int, hasUpperHand: Boolean)

case class FightResult(winner: Boolean, player: Player)

case class Fight(var player: Player, opponent: Opponent) {

  private def RoundsToWin = 3

  private lazy val firstUpperHand = scala.util.Random.nextBoolean()

  var playerState = FightState(player, 0, firstUpperHand)
  var opponentState = FightState(opponent, 0, !firstUpperHand)

  def start(): FightResult = {
    while (!isThereAWinner) {
      nextRound()
    }
    Prompt.reportFightWinner(isPlayerWinner)
    FightResult(isPlayerWinner, player)
  }

  private def isThereAWinner: Boolean = isPlayerWinner || isOpponentWinner

  private def isPlayerWinner: Boolean = playerState.score == RoundsToWin

  private def isOpponentWinner: Boolean = opponentState.score == RoundsToWin

  private def nextRound(): Unit = {
    val insult = if (playerState.hasUpperHand) player.insult() else opponent.insult()
    val comeback = if (playerState.hasUpperHand) opponent.comeback(insult) else player.comeback(insult)

    player = player.learn(insult, comeback)

    if (insult.matches(comeback)) upperHandLosesRound()
    else upperHandWinsRound()
  }

  private def upperHandLosesRound(): Unit = {
    if (playerState.hasUpperHand) {
      Prompt.missedRound()
      playerState = playerState.copy(fighter = player, hasUpperHand = false)
      opponentState = opponentState.copy(score = opponentState.score + 1, hasUpperHand = true)
    } else {
      Prompt.wonRound()
      playerState = playerState.copy(fighter = player, score = playerState.score + 1, hasUpperHand = true)
      opponentState = opponentState.copy(hasUpperHand = false)
    }
  }

  private def upperHandWinsRound(): Unit = {
    if (playerState.hasUpperHand) {
      Prompt.wonRound()
      playerState = playerState.copy(fighter = player, score = playerState.score + 1)
    } else {
      Prompt.missedRound()
      opponentState = opponentState.copy(score = opponentState.score + 1)
    }
  }
}

case class FightMaster(var player: Player, master: Opponent) {

  private def RoundsToWin = 5

  var playerRounds = 0
  var masterRounds = 0

  def start(): FightResult = {
    while (!isThereAWinner) {
      nextRound()
    }
    Prompt.reportFightMasterWinner(isPlayerWinner)

    FightResult(isPlayerWinner, player)
  }

  private def isThereAWinner: Boolean = isPlayerWinner || isMasterWinner

  private def isPlayerWinner: Boolean = playerRounds == RoundsToWin

  private def isMasterWinner: Boolean = masterRounds == RoundsToWin

  private def nextRound(): Unit = {
    val insult = master.insult()
    val comeback = player.comeback(insult)

    if (insult.matches(comeback)) {
      Prompt.wonRound()
      playerRounds += 1
    }
    else {
      Prompt.missedRound()
      masterRounds += 1
    }
  }
}
