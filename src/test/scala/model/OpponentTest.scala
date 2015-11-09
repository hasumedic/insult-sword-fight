package model

import org.scalatest.FunSuite

class OpponentTest extends FunSuite {

  val fewInsults = List(
    FullInsult(1, "common1", "master1", "comeback1"),
    FullInsult(2, "common2", "master2", "comeback2"),
    FullInsult(3, "common3", "master3", "comeback3")
  )

  test("builFrom Nil") {
    val opponent = Opponent.buildFrom(Nil)
    assert(opponent.knownInsults == Nil)
    assert(opponent.knownComebacks == Nil)
  }

  test("builFrom fewInsults") {
    val opponent = Opponent.buildFrom(fewInsults)
    assert(opponent.knownInsults.size == 3)
    assert(opponent.knownComebacks.size == 3)
  }
}
