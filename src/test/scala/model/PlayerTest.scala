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
}
