package com.ssshow.gnumber

import org.specs2.mutable.Specification

/**
 * Created by 1002515 on 2015. 11. 3..
 */
class gNumberSpec extends Specification {

  "gNumberSpec" >> {
    "check Prime" >> {
      gNumber.isPrime(1) must_== true
      // gNumber.isPrime(2) must_== true
      // gNumber.isPrime(9) must_== false
    }

    // "check gNumber" >> {
    //   // gNumber.checkGNumber(2) == true
    //   // gNumber.checkGNumber(3) == true
    //   gNumber.isGNumber(36300) == false
    // }

    // "find loser" >> {
    //   gNumber.findLoser(36300,0) == (0,true)
    // }
    // "getPrimeFactor" >> {
    //   // gNumber.primeFactor(9) must_== 3
    //   gNumber.primeFactor(72) must_== 2
    // }

    "nextValue" >> {
      val nextValue = gNumber.nextValue(72,2)
      nextValue must_== 9
    }

    "start" >> {
      gNumber.findWinner(36) == "Seymour" 
      // gNumber.findWinner(36300) == "Laurence" 
      // gNumber.findWinner(36300) == "Laurence" 
    }
  }
}
