package com.ssshow.dynamicgrid

import java.io.{PrintStream, File}
import scala.io.Source

sealed trait Operation{
	def apply(linkMatrix:Array[Int])
}

case class M(x:Int, y:Int, z:Int, column:Int) extends Operation {
	def apply(linkMatrix:Array[Int]){
		linkMatrix(x * column + y) = z
	}
}

case class Q() extends Operation {
	def apply(linkMatrix:Array[Int]){
		linkMatrix.toList.groupBy(x => x).filterKeys(_ != -1).size
	}
}

case class TestCase(row:Int, column:Int, grid:Array[Array[Int]], linkedMatrix:Array[Int], operations:Array[Operation])

object DynamicGrid{
		
  def main(args: Array[String]) {
    println(" == DynamicGrid Start  == ")

    val in = Source.fromFile(new File("resource/dynamicgrid/in/one.in"))
    val out = new PrintStream(new File("resource/dynamicgrid/out/one.out"))
    try {
      process(in.getLines) { s: String => out.println(s) }
    } finally {
        out.flush; out.close
    }

    println(" == DynamicGrid End  == ")
  }

  def makeLinkMatrix(grid:Array[Array[Int]], row:Int, column:Int) {
  	val linkMatrix = Array.fill(row * column)(-1)

  	def findNeighbor(x:Int, y:Int): Int = 
  		if(x - 1 > 0 && grid(x-1)(y) == 1) y * row + (x - 1)
  		else if(y - 1 > 0 && grid(x)(y-1) == 1) (y-1) * row + x
  		else x + y * row

  	for{
  		y <- 0 until row
			x <- 0 until column
			if(grid(x)(y) == 1)
			index = findNeighbor(x,y)
			if(index > 0)
  	} yield {
  		linkMatrix(x + y * row) = index
  	}
  }

  def process(iter: Iterator[String])(pr: String => Unit) {

    def readTestCase(iter: Iterator[String]) : Seq[TestCase] = {
      val tCnt = iter.next.toInt
      def splitToInt(iter: Iterator[String]) : Array[Int] = iter.next.split(" ").map(_.toInt)

      for(i <- 0 to tCnt - 1) yield {

        val firstLine = splitToInt(iter)
        val row = firstLine(0)
        val column = firstLine(1)

        val grid = for(j <- 0 to row - 1) yield {
        	splitToInt(iter)
        }

				val opCnt = iter.next.toInt

				val operations = for(j <- 0 to opCnt - 1) yield {
						val op = iter.next.split(" ")
						op(0) match{
							case "Q" => Q()
							case "M" => M(op(1).toInt, op(2).toInt, op(3).toInt, column)
						}
        }

        TestCase(row, column, grid.toArray, Array.fill(row * column)(-1), operations.toArray)
      }
    }

    val testCases = readTestCase(iter)
    println(testCases)
    // for (i <- 1 to testCases.size) yield {
    //     println(">>" + i)
    //   pr(s"Case #$i: ${getMaxSum(testCases(i - 1))}")      
    // }
  } 
}