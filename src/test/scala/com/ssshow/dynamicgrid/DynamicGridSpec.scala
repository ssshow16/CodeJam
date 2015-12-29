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

      val link = DynamicGrid.makeLink(grid)

      link must_== Array(
        Array(-1, 1,-1, 3),
        Array(-1,-1, 6,-1),
        Array(-1,9,-1,-1),
        Array(9,9,9,9))
    }

  }
}
