package game

import model._
import scala.io.Source

class GameEngine {
  val repository = {
    val json = Source.fromURL(getClass.getResource("/monkey-island.json")).mkString
    new InsultRepository(json)
  }

  private val fightEngine = new FightEngine(repository)
  private var over = false
  private var winner = false
  private var player = Player.buildFrom(repository.takeRandom(2))

  private def finishGame(): Unit = over = true

  def isOver: Boolean = over

  def isWinner: Boolean = winner

  def nextFight(): Unit = {
    if (fightEngine.playerWantsToFight()) {
      player = fightEngine.startFight(player)
      finishGame()
    } else finishGame()
  }

  def welcome(): Unit = {
    println(Console.BLUE + "Welcome to the Monkey Island insult sword-fighting game!" + Console.RESET)
    println()
    println("So I've heard that you wanted to be a pirate, huh? Shouldn't you stop crawling and learn how to walk first? HA! Just kidding...")
    println("To become a real pirate, you first need to defeat the Sword Master™ in single combat. " +
      "Woah! But stop moving your sword like a feather duster! Combats here are not the way you might think... " +
      "Here we fight with sharp tongues! And brains!")
    println("You'll need to learn proper insults from other pirates before you are ready to face the Sword Master™. " +
      "The more insults you learn, the sooner you'll be ready to face the Sword Master™! Oh look, here comes a fellow pirate!")
  }

  def goodbyePirate() = {
    println("Oh! So you learned how to fight in the end! Well done son, use your sharp tongue wisely now!")
  }

  def goodbyeLooser() = {
    println("Coward...")
  }
}
