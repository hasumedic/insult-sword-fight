package game

object Prompt {

  def welcome(): Unit = {
    printBlue("Welcome to the Monkey Island insult sword-fighting game!")
    println()
    println("So I've heard that you wanted to be a pirate, huh? Shouldn't you stop crawling and learn how to walk first? HA! Just kidding...")
    println("To become a real pirate, you first need to defeat the Sword Master™ in single combat. " +
      "Woah! But stop moving your sword like a feather duster! Combats here are not the way you might think... " +
      "Here we fight with sharp tongues! And brains!")
    println("You'll need to learn proper insults from other pirates before you are ready to face the Sword Master™. " +
      "The more insults you learn, the sooner you'll be ready to face the Sword Master™! Oh look, here comes a fellow pirate!")
    println()
  }

  def goodbyePirate(): Unit = {
    printGreen("Oh! So you learned how to fight in the end! Well done son, use your sharp tongue wisely now!")
  }

  def goodbyeLooser(): Unit = {
    printRed("Coward...")
  }

  def reportFightWinner(isPlayerWinner: Boolean): Unit = {
    if (isPlayerWinner) {
      printGreen("Congratulations! You won this fight!")
      println()
    } else {
      printRed("I'm afraid you lost this one... better luck next time!")
      println()
    }
  }

  def reportFightMasterWinner(isPlayerWinner: Boolean): Unit = {
    if (isPlayerWinner) {
      printGreen("Congratulations! You've defeated the Sword Master!")
      println()
    } else {
      printRed("The Sword Master is laughing at your face! Make sure you practice a bit more before facing her again!")
      println()
    }
  }

  def missedRound(): Unit = printRed("You missed this one!")

  def wonRound(): Unit = printGreen("You got this one!")

  def pirateComeback(): Unit = printYellow("The pirate's comeback:")

  def pirateInsult(): Unit = printYellow("The pirate insults you:")

  private def printGreen(text: String): Unit = {
    printColoured(Console.GREEN, text)
  }

  private def printRed(text: String): Unit = {
    printColoured(Console.RED, text)
  }

  private def printBlue(text: String): Unit = {
    printColoured(Console.BLUE, text)
  }

  private def printYellow(text: String): Unit = {
    printColoured(Console.YELLOW, text)
  }

  private def printColoured(colour: String, text: String): Unit = {
    println(colour + text + Console.RESET)
  }
}
