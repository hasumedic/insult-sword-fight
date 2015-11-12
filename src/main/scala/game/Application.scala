package game

object Application extends App {

  welcome()

  val game = new GameEngine

  while (!game.isOver) {
    game.nextFight()
  }

  if (game.isWinner) goodbyePirate()
  else goodbyeLooser()


  def welcome(): Unit = {
    println(Console.BLUE + "Welcome to the Monkey Island insult sword-fighting game!" + Console.RESET)
    println()
    println("So I've heard that you wanted to be a pirate, huh? Shouldn't you stop crawling and learn how to walk first? HA! Just kidding...")
    println("To become a real pirate, you first need to defeat the Sword Master™ in single combat. " +
      "Woah! But stop moving your sword like a feather duster! Combats here are not the way you might think... " +
      "Here we fight with sharp tongues! And brains!")
    println("You'll need to learn proper insults from other pirates before you are ready to face the Sword Master™. " +
      "The more insults you learn, the sooner you'll be ready to face the Sword Master™! Oh look, here comes a fellow pirate!")
    println()
  }

  def goodbyePirate() = {
    println(Console.GREEN + "Oh! So you learned how to fight in the end! Well done son, use your sharp tongue wisely now!" + Console.RESET)
  }

  def goodbyeLooser() = {
    println(Console.RED + "Coward..." + Console.RESET)
  }
}
