package game

object Interaction {
  def playerWantsToFightMaster(): Boolean = {
    println("It looks like you're ready to face the Sword Master! Would you like give it a go? [Y/N]")
    val response = handleYesNoQuestion
    response.toLower == 'y'
  }

  def playerWantsARegularFight(): Boolean = {
    println("Would you like to start a fight with a pirate? [Y/N]")
    val response = handleYesNoQuestion
    response.toLower == 'y'
  }

  def handleYesNoQuestion: Char = {
    val response = scala.io.StdIn.readChar()
    response.toLower match {
      case 'y' => response
      case 'n' => response
      case _ => {
        println("Pardon?")
        handleYesNoQuestion
      }
    }
  }
}
