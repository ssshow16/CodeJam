package com.ssshow.dynamicgrid

import org.specs2.mutable.Specification

/**
 * Created by 1002515 on 2015. 11. 3..
 */
class DynamicGridSpec extends Specification {

  "DynamicGridSpec" >> {
    "makeLinkMatrix" >> {
      
      val grid = Array(
        Array(0,1,0,1),
        Array(0,0,1,0),
        Array(0,1,0,0),
        Array(1,1,1,1))

      val linkMatrix = DynamicGrid.makeLinkMatrix(grid,4,4)

      1 == 1
    }

  }
}
