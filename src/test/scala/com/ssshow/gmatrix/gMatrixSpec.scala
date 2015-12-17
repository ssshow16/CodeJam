package com.ssshow.gmatrix

import org.specs2.mutable.Specification

/**
 * Created by 1002515 on 2015. 11. 3..
 */
class gMatrixSpec extends Specification {

  "gMatrixSpec" >> {
    "test final" >> {
      val n = 3
      val k = 2
      val c = 3
      val x = 109
      val row = Array(6,4,3)
      val col = Array(2,1,5)

      val testCase = TestCase(row,col,n,c,x,k)

      val result = gMatrix.getMaxSum(testCase)
      result must_== 80
    }

    "getSubMatrix" >> {
      val matrix = Array(
        Array(1,2,3),
        Array(4,5,6),
        Array(7,8,9))

      val subMatrix = gMatrix.getSubMatrix(1,1,matrix,2)
      subMatrix must_== Array(
        Array(5,6),
        Array(8,9))
    }
  }
}
