package game

import model._

import scala.io.Source

class GameEngine {

  lazy private val repository = new InsultRepository(
    Source.fromURL(getClass.getResource("/monkey-island.json")).mkString
  )

  lazy private val fightEngine = new FightEngine(repository)

  private var over = false
  private var winner = false
  private var player = Player.buildFrom(repository.takeRandom(2))

  def isOver: Boolean = over

  def isWinner: Boolean = winner

  def nextFight(): Unit = {
    if (playerIsReadyToFaceMaster() && Interaction.playerWantsToFightMaster()) {
      val fightResult = fightEngine.fightMaster(player)
      if (fightResult.winner) {
        winner = true
        gameOver()
      }
    }
    else if (Interaction.playerWantsARegularFight()) {
      player = fightEngine.startFight(player)
    }
    else gameOver()
  }

  private def gameOver(): Unit = over = true

  /** A player is ready to face the Master when knows at least 75% of the insults */
  private def playerIsReadyToFaceMaster(): Boolean = {
    (player.totalKnownInsults.toFloat / repository.insults.size) * 100 >= 75
  }
}
