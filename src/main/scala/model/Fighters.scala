package model

trait Fighter

case class Player(knownInsults: List[Insult], knownComebacks: List[Comeback]) extends Fighter {
  def learn(insult: Insult, comeback: Comeback): Player = {
    Player(
      if (knownInsults.contains(insult)) knownInsults else insult :: knownInsults,
      if (knownComebacks.contains(comeback)) knownComebacks else comeback :: knownComebacks
    )
  }

  def comeback(insult: Insult): Comeback = {
    println(Console.YELLOW + "What's your answer?" + Console.RESET)
    printComebacks()
    val chosenComeback = scala.io.StdIn.readLine().toInt
    if (!isValidComeback(chosenComeback)) comeback(insult)
    else lookupComeback(chosenComeback)
  }

  def insult(): Insult = {
    printInsults()
    val chosenInsult = scala.io.StdIn.readLine().toInt
    if (!isValidInsult(chosenInsult)) insult()
    else lookupInsult(chosenInsult)
  }

  private def printInsults() = {
    knownInsults.foreach { i =>
      println(s"[${i.id}]    ${i.insult}")
    }
  }

  def printComebacks() = {
    knownComebacks.foreach { i =>
      println(s"[${i.id}]    ${i.comeback}")
    }
  }

  private def isValidInsult(chosenInsult: Int): Boolean = knownInsults exists (_.id == chosenInsult)

  private def isValidComeback(chosenComeback: Int): Boolean = knownComebacks exists (_.id == chosenComeback)

  private def lookupInsult(chosenInsult: Int): Insult = (knownInsults filter (_.id == chosenInsult)).head

  private def lookupComeback(chosenComeback: Int): Comeback = (knownComebacks filter (_.id == chosenComeback)).head

}

object Player {
  def buildFrom(fullInsults: List[FullInsult]): Player = {
    new Player(
      FullInsult.extractInsults(fullInsults),
      FullInsult.extractComebacks(fullInsults)
    )
  }
}

case class Opponent(knownInsults: List[Insult], knownComebacks: List[Comeback]) extends Fighter {
  def comeback(insult: Insult): Comeback = {
    if (knownInsults.contains(insult)) knownComebacks.filter(_.id == insult.id).head
    else scala.util.Random.shuffle(knownComebacks).head
  }

  def insult(): Insult = {
    val insult = scala.util.Random.shuffle(knownInsults).head
    println(Console.YELLOW + "A pirate spits at you:" + Console.RESET)
    println(insult.insult)
    insult
  }
}

object Opponent {
  def buildFrom(fullInsults: List[FullInsult]): Opponent =
    Opponent(
      FullInsult.extractInsults(fullInsults),
      FullInsult.extractComebacks(fullInsults)
    )
}
