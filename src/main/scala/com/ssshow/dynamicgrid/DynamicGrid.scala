package com.ssshow.dynamicgrid

import java.io.{PrintStream, File}
import scala.io.Source

sealed trait Operation{
  def apply(link:Array[Array[Int]]):Int
}

case class M (x:Int, y:Int, z:Int, column:Int) extends Operation {
  def apply(link:Array[Array[Int]]):Int = {
    link(x)(y) = z
    -1
  }
}

case class Q() extends Operation {
  def apply(link:Array[Array[Int]]):Int = {
    link.flatMap(x => x).groupBy(x => x).filterKeys(_ != -1).size
  }
}

case class TestCase(row:Int, column:Int, grid:Array[Array[Int]], operations:Array[Operation])

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

  def executeOperation(index:Int, testCase:TestCase)(pr: String => Unit){
    pr(s"Case #index:")

    testCase.operations.foreach{ op =>
      op match{
        case M(_,_,_,_) => op.apply(testCase.grid)  // 변경사항을 반영한다.
        case Q() => {
          val link = makeLink(testCase.grid) // grid 상태에 따른 connected link 정보를 새로 분석한다.
          // printLink(link)
          // val result = op.apply(link).toString
          // pr(result)
        }
      }
    }
  }

  def makeLink(grid:Array[Array[Int]]):Array[Array[Int]] = {

    val row = grid.size
    val column = grid(0).size
    val link = Array.fill(row,column)(-1)

    def isLeftConnected(x:Int, y:Int): Boolean = {
      val result = x - 1 >= 0 && grid(y)(x-1) == 1
      // println(s"isLeftConnected : $result")
      result
    }

    def isTopConnected(x:Int, y:Int): Boolean = {
      val result = y - 1 >= 0 && grid(y-1)(x) == 1
      // println(s"isTopConnected : $result")
      result
    }

    for{
      y <- 0 until row
      x <- 0 until column
      if(grid(y)(x) == 1)
    } yield {
      println(x + " <> " + y)

      if(link(y)(x) == -1) link(y)(x) = x + y * column
      if(isLeftConnected(x,y)) union((y,x),(y,x-1), link, row, column)
      if(isTopConnected(x,y))  union((y,x),(y-1,x), link, row, column)
   
      // printLink(link)
    }

    link
  }

  def printLink(arr:Array[Array[Int]]){
    
    val row = arr.size
    val column = arr(0).size

    println("==========START===========")
    for(y <- 0 until row){
      for(x <- 0 until column){
        print(arr(y)(x) + " ")
      }
      println("")
    }
    println("==========END===========")
  }

  def union(p:(Int,Int), q:(Int,Int), link:Array[Array[Int]], row:Int, column:Int){
    val pId = link(p._1)(p._2)
    val qId = link(q._1)(q._2)

    println(s"union >> pId: $pId, qId: $qId")

    // 연결되어 있는 모든 id값을 q로 변환하여, connected 상태로 만든다.
    if(pId != qId){
      for{
        y <- 0 until row
        x <- 0 until column
      } yield {
        if(link(y)(x) == pId) link(y)(x) = qId
      }
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

        val grid = for(j <- 0 to (row - 1)) yield {
          iter.next.toCharArray.map(x => x.toString.toInt)
        }

        // printLink(grid.toArray)

        val opCnt = iter.next.toInt

        val operations = for(j <- 0 to opCnt - 1) yield {
            val op = iter.next.split(" ")
            op(0) match{
              case "Q" => Q()
              case "M" => M(op(1).toInt, op(2).toInt, op(3).toInt, column)
            }
        }

        TestCase(row, column, grid.toArray, operations.toArray)
      }
    }

    val testCases = readTestCase(iter)

    for (i <- 1 to testCases.size) yield {
      executeOperation(i, testCases(i-1))(pr)
    }
  } 
}