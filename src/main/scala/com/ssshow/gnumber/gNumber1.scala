package com.ssshow.gnumber

/**
 * Created by 1002515 on 2015. 11. 16..
 */
object gNumber {

  def start(N: Long, index: Int) = {

    var C = N
    var currentTurn = true

    while (!checkGNumber(C)) {

      println(s"Start : $C")

      // val pf = primeFactor(C)

      val factors = factorization(C).groupBy(x=>x) map { case (k,v) => (k,v.size) } toList

      println(s"factors : $factors")

      val result = factors.find{ case (f,s) => checkGNumber(nextValue(C,f))}

      C = result.map{ case(f,s) => nextValue(C,f) }.getOrElse(nextValue(C,factors(0)._1))

      println(s"nextValue : $C")
      
      currentTurn = !currentTurn

      println(s"currentTurn : $currentTurn")
    }

    println(s"Case #$index: ${getName(!currentTurn)}")
  }

  def nextValue(value:Long, primeFactor:Long) : Long =
    if((value % primeFactor) != 0) value
    else nextValue(value / primeFactor, primeFactor)

  def getName(currentTurn: Boolean): String =
    if (currentTurn) "Laurence"
    else "Seymour"

  def checkGNumber(value: Long): Boolean = isPrime(sumDigit(value))

  def sumDigit(value:Long) : Long = {
    val s = value.toString.map(c => c.toString.toInt).sum
    println(s"sum:$s")
    s
  }

  def isPrime(value: Long): Boolean =
    if(value == 1) true
    else !(2L to Math.sqrt(value).toLong).exists(value % _ == 0)

  // def primeFactor(value:Long): Long = {
  //   (2L to Math.sqrt(value).toLong).filter(value % _ == 0)(0)
  // }

  def factorization(n:Long):List[Long] = {
    (2L to Math.sqrt(n).toLong).find(0 == n % _).map(x => x +: factorization(n / x)).getOrElse(List(n))
  }
}
