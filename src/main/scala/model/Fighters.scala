package model

import game.Prompt

trait Fighter

case class Player(knownInsults: List[Insult], knownComebacks: List[Comeback]) extends Fighter {
  def totalKnownInsults: Int = {
    knownInsults.foldLeft(0)((acc, i) => if (knownComebacks.exists(c => i.matches(c))) acc + 1 else acc)
  }

  def learn(insult: Insult, comeback: Comeback): Player = {
    Player(
      if (knownInsults.contains(insult)) knownInsults else insult :: knownInsults,
      if (knownComebacks.contains(comeback)) knownComebacks else comeback :: knownComebacks
    )
  }

  def comeback(insult: Insult): Comeback = {
    println(Console.YELLOW + "What's your reply?" + Console.RESET)
    printComebacks()
    val chosenComeback = scala.io.StdIn.readLine().toInt
    if (!isValidComeback(chosenComeback)) {
      println(Console.RED + "That was not a valid reply..." + Console.RESET)
      comeback(insult)
    }
    else lookupComeback(chosenComeback)
  }

  def insult(): Insult = {
    println(Console.YELLOW + "What's your insult?" + Console.RESET)
    printInsults()
    val chosenInsult = scala.io.StdIn.readLine().toInt
    if (!isValidInsult(chosenInsult)) {
      println(Console.RED + "Opponent could not hear you well..." + Console.RESET)
      insult()
    }
    else lookupInsult(chosenInsult)
  }

  private def printInsults(): Unit = {
    knownInsults.sortBy(_.id).foreach { i =>
      println(s"[${i.id}]    ${i.insult}")
    }
  }

  private def printComebacks(): Unit = {
    knownComebacks.sortBy(_.id).foreach { i =>
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
    val comeback =
      if (knownInsults.contains(insult)) knownComebacks.filter(_.id == insult.id).head
      else scala.util.Random.shuffle(knownComebacks).head

    Prompt.pirateComeback()
    println(comeback.comeback)
    println()

    comeback
  }

  def insult(): Insult = {
    val insult = scala.util.Random.shuffle(knownInsults).head
    Prompt.pirateInsult()
    println(insult.insult)
    println()

    insult
  }
}

object Opponent {
  def buildFrom(fullInsults: List[FullInsult]): Opponent =
    Opponent(
      FullInsult.extractInsults(fullInsults),
      FullInsult.extractComebacks(fullInsults)
    )

  def buildMaster(fullInsults: List[FullInsult]): Opponent =
    Opponent(
      FullInsult.extractMasterInsults(fullInsults),
      FullInsult.extractComebacks(fullInsults)
    )
}
