package com.ssshow.gnumber

import java.io.{PrintStream, File}
import scala.io.Source

/**
 * Created by 1002515 on 2015. 11. 16..
 */
object gNumber {

  def main(args: Array[String]) {
    val in = Source.fromFile(new File("large.in"))
    val out = new PrintStream(new File("large.out"))
    try {
      process(in.getLines) { s: String => out.println(s) }
    } finally {
      out.flush; out.close
    }
  }

  def process(iter: Iterator[String])(pr: String => Unit) {
    for (i <- 1 to iter.next().toInt) yield {
      pr(s"Case #$i: ${findWinner(iter.next().toLong)}")
    }
  }

  def findWinner(N: Long) : String = {
    def canLaurencedWin(value:Long, isLaurenced:Boolean) : Boolean = {
      if(checkGNumber(value)){
        if(isLaurenced == true) false else true
      } else {
        val factors = factorization(value).groupBy(x=>x) map { case (k,v) => (k,v.size) } toList

        val nextValues = factors.map{ case (f,s) => nextValue(value,f)}
        if(nextValues.exists(checkGNumber)) {
          if(isLaurenced == true) true else false
        }else {
          nextValues.map(nv => canLaurencedWin(nv,!isLaurenced)).exists(_ == true)
        }        
      }
    }

    val isLaurencedWin = canLaurencedWin(N,true)

    getName(isLaurencedWin)
  }

  def nextValue(value:Long, primeFactor:Long) : Long = {
    if((value % primeFactor) != 0) value
    else nextValue(value / primeFactor, primeFactor)
  }

  def getName(isLaurencedWin: Boolean): String =
    if (isLaurencedWin) "Laurence"
    else "Seymour"

  def checkGNumber(value: Long): Boolean = isPrime(sumDigit(value))

  def sumDigit(value:Long) : Long = {
    val s = value.toString.map(c => c.toString.toInt).sum
    s
  }

  def isPrime(value: Long): Boolean =
    if(value == 1) true
    else !(2L to Math.sqrt(value).toLong).exists(value % _ == 0)

  def factorization(n:Long):List[Long] = {
    (2L to Math.sqrt(n).toLong).find(0 == n % _).map(x => x +: factorization(n / x)).getOrElse(List(n))
  }
}
