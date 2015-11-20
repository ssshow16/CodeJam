package com.ssshow.gwheel

import org.specs2.mutable.Specification

/**
 * Created by 1002515 on 2015. 11. 3..
 */
class gWheelSpec extends Specification {

  "gWheelSpec" >> {
    "Create possible raios" >> {

      // val p = Array(5)
      // val e = Array(4,6)
      // val t = Array(3,5,7)
      // val ratios = gWheel.calcRatios(p,e,t)
      // ratios.size must_== 6
      1 == 1
    }
  }

  "Load TestCase" >> {
    val tcIn = List(
              "1",
              " ",
              "4 4 3",
              "3713 140 1475 9721",
              "5665 8611 8762 7400",
              "5166 3739 853",
              "1",
              "30751629 21365175").toIterator

    val tc = gWheel.readTestCase(tcIn)(0)

    val isPossible = gWheel.isPossible(tc.p,tc.t,tc.e, tc.q(0))

    isPossible must_== false
  }

}
