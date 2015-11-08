package model

import org.scalatest.{Matchers, FunSuite}

class FullInsultSpec extends FunSuite with Matchers {

  val someFullInsults = List(
    FullInsult(1, "insult1", "master-insult", "comeback1"),
    FullInsult(2, "insult2", "master-insult", "comeback2"),
    FullInsult(3, "insult3", "master-insult", "comeback3")
  )

  val someComebacks = List(
    Comeback(1, "comeback1"),
    Comeback(2, "comeback2"),
    Comeback(3, "comeback3")
  )

  val someInsults = List(
    Insult(1, "insult1"),
    Insult(2, "insult2"),
    Insult(3, "insult3")
  )

  test("testExtractComebacks") {
    assert(FullInsult.extractComebacks(Nil) == Nil)
    assert(FullInsult.extractComebacks(someFullInsults) == someComebacks)
  }

  test("testExtractInsults") {
    assert(FullInsult.extractInsults(Nil) == Nil)
    assert(FullInsult.extractInsults(someFullInsults) == someInsults)
  }
}
