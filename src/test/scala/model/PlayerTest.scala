package model

import org.scalatest.{Matchers, FunSuite}

class PlayerTest extends FunSuite with Matchers {

  val fewInsults = List(
    FullInsult(1, "common1", "master1", "comeback1"),
    FullInsult(2, "common2", "master2", "comeback2"),
    FullInsult(3, "common3", "master3", "comeback3")
  )

  test("builFrom Nil") {
    val player = Player.buildFrom(Nil)
    assert(player.knownInsults == Nil)
    assert(player.knownComebacks == Nil)
  }

  test("builFrom fewInsults") {
    val player = Player.buildFrom(fewInsults)
    assert(player.knownInsults.size == 3)
    assert(player.knownComebacks.size == 3)
  }

  test("learn first insults or comebacks") {
    val player = Player.buildFrom(Nil)
    val learnedPlayer = player.learn(Insult(1, "Insult"), Comeback(2, "Comeback2"))
    assert(learnedPlayer.knownInsults.size == 1)
    assert(learnedPlayer.knownComebacks.size == 1)
  }

  test("learn new insults and comebacks") {
    val player = Player(List(Insult(1, "Insult")), List(Comeback(1, "Comeback")))
    val learnedPlayer = player.learn(Insult(2, "Insult2"), Comeback(2, "Comeback2"))
    assert(learnedPlayer.knownInsults.size == 2)
    assert(learnedPlayer.knownComebacks.size == 2)
  }

  test("doesn't learn already known insults and comebacks") {
    val player = Player(List(Insult(1, "Insult")), List(Comeback(1, "Comeback")))
    val learnedPlayer = player.learn(Insult(1, "Insult"), Comeback(1, "Comeback"))
    assert(learnedPlayer.knownInsults.size == 1)
    assert(learnedPlayer.knownComebacks.size == 1)
  }
}
