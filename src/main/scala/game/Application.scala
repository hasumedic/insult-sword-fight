package game

object Application extends App {

  Prompt.welcome()

  val game = new GameEngine

  while (!game.isOver) {
    game.nextFight()
  }

  if (game.isWinner) Prompt.goodbyePirate()
  else Prompt.goodbyeLooser()
}
