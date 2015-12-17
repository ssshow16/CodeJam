package com.ssshow.gmatrix

import java.io.{PrintStream, File}
import scala.io.Source
    
case class TestCase(row:Array[Int], col:Array[Int], n:Int, c:Int, x:Int, k:Int)

object gMatrix{

  def main(args: Array[String]) {
    println(" == gMatrix Start  == ")

    val in = Source.fromFile(new File("resource/gmatrix/in/D-small-practice.in"))
    val out = new PrintStream(new File("resource/gmatrix/out/D-small-practice.out"))
    // val in = Source.fromFile(new File("resource/gmatrix/in/D-large-practice.in"))
    // val out = new PrintStream(new File("resource/gmatrix/out/D-large-practice.out"))
    // val in = Source.fromFile(new File("resource/gmatrix/in/large.in"))
    // val out = new PrintStream(new File("resource/gmatrix/out/large.out"))
    try {
      process(in.getLines) { s: String => out.println(s) }
    } finally {
        out.flush; out.close
    }

    println(" == gMatrix End  == 1")
  }

  def process(iter: Iterator[String])(pr: String => Unit) {

    def readTestCase(iter: Iterator[String]) : Seq[TestCase] = {
      val count = iter.next.toInt

      def splitToInt(iter: Iterator[String]) : Array[Int] = iter.next.split(" ").map(_.toInt)

      for(i <- 0 to count - 1) yield {

        val firstLine = splitToInt(iter)
        val n = firstLine(0)
        val k = firstLine(1)
        val c = firstLine(2)
        val x = firstLine(3)

        val row = splitToInt(iter)
        val col = splitToInt(iter)

        TestCase(row,col,n,c,x,k)
      }
    }

    val testCases = readTestCase(iter)
    for (i <- 1 to testCases.size) yield {
        println(">>" + i)
      pr(s"Case #$i: ${getMaxSum(testCases(i - 1))}")      
    }
  } 

  private[gmatrix] def getMaxSum(testCase:TestCase) : Long = {
    val matrix = makeMatrix(testCase)
    findMaxValuesInSubMatrix(matrix,testCase.n,testCase.k).sum
  }

  private[gmatrix] def makeMatrix(testCase:TestCase) : Array[Array[Int]] = {
     println(" == makeMatrix start  == 1")
    val matrix = Array.ofDim[Int](testCase.n, testCase.n)
    for(i <- 1 to testCase.n){// 행
      for(j <- 1 to testCase.n){// 열
        matrix(i - 1)(j - 1) = (testCase.row(i - 1) * i + testCase.col(j - 1) * j + testCase.c) % testCase.x
      }
    }   
    println(" == makeMatrix End  == 1")
    matrix
  }

  private[gmatrix] def getSubMatrix(offsetX:Int, offsetY:Int, matrix:Array[Array[Int]], k:Int):Int = {

    println(" == getSubMatrix start == ")

    // val subMatrix = Array.ofDim[Int](k,k)
    var max = 0
    for{
      i <- 0 until k
      j <- 0 until k
    } yield {
      if(matrix(offsetX + i)(offsetY + j) > max) max = matrix(offsetX + i)(offsetY + j)
      // subMatrix(i)(j) = matrix(offsetX + i)(offsetY + j)
      0
    }
     println(" == getSubMatrix end ==")
    // printMatrix(subMatrix)
    max
  }

  private[gmatrix] def findMaxValuesInSubMatrix(matrix:Array[Array[Int]], n:Int, k:Int) : Seq[Long] = {
    // println(s"findMaxValuesInSubMatrix n:$n, k:$k")
    for{
      i <- 0 to (n - k)
      j <- 0 to (n - k)
    } yield getSubMatrix(i,j, matrix, k).toLong
  }

  private def printMatrix(matrix:Array[Array[Int]]) {
    println(" === Matrix ===")
    matrix.foreach(x => {
        x.foreach(c => 
          print(c + " ")
        )
        println("")
      } 
    )
    println(" === End    ===")
  }
}