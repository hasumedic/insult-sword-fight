package game

object Application extends App {

  val game = new GameEngine
  game.welcome()

  while (!game.isOver) {
    game.nextFight()
  }

  if (game.isWinner) game.goodbyePirate()
  else game.goodbyeLooser()
}

