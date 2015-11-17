package com.ssshow.gnumber

/**
 * Created by 1002515 on 2015. 11. 16..
 */
object gNumber2 {

  // def nextValue(value:Long, factor:Long) : Long =
  //   if((value % factor) != 0) value
  //   else nextValue(value / factor, factor)

  // def getName(currentTurn: Int) =
  //   if (currentTurn == 0) "Laurence"
  //   else "Seymour"

  // def isGNumber(value:Long): Boolean = {
  //   println(s"isGNumber value: $value")
  //   val sum = value.toString.map(c => c.toString.toInt).sum
  //   println(s"isGNumber.sum : $sum")
  //   val result = isPrime(sum)

  //   println(s"isGNumber.result : $result")
  //   result
  // }

  // def nextTurn(turn:Int) = if(turn == 1) 0 else 1

  // def findLoser(v:Long, turn:Int) : (Int,Boolean) = {

  //   println(s"findLoser.v:$v, turn:$turn")

  //   if(isGNumber(v)) (turn,true) // 이번 turn이 gNumber이면 loser, 아니면 다음 turn으로 넘긴다.
  //   else {

  //     val factors = factorization(v).groupBy(x=>x) map { case (k,v) => (k,v.size) } toList

  //     println(s"findLoser.factors : $factors")

  //     val result = factors.map{ // gNumber를 찾은 첫번째를 반환한다. 없으면 false를 반환한다.이때 v 값은 의미가 없다.
  //       x => {
  //         println(s"findLoser.factor : ${x._1}" )
  //         val nv = nextValue(v,x._1)
  //         println(s"findLoser.nextValue : $nv" )
  //         val t,f = findLoser(nv, nextTurn(turn))
  //         (t,f,nv)
  //       }
  //     }.find{ case (t,f,nv) => f == true }

  //     result.getOrElse()


  //   } 
  // }

  // def winner(value:Long) : String = {


  //   // var loser = findLoser(value,0) // Laurence(0)이 먼저 시작한다.
  //   // getName(nextTurn(loser._1))
  //   getName(1)
  // }

  // // def winner(value: Long): Boolean = {

  // //   println(s"checkGNumber value: $value")

  // //   val sum = value.toString.map(c => c.toString.toInt).sum
  // //   println(s"checkGNumber.sum : $sum")

  // //   if(isPrime(sum)) true
  // //   else {
  // //     val factors = factorization(sum).groupBy(x=>x) map { case (k,v) => (k,v.size) } toList

  // //     println(s"checkGNumber.factors : $factors")
  // //     val result = factors.exists(
  // //       x => {
  // //         println(s"factor : ${x._1}" )
  // //         val nv = nextValue(sum,x._1)
  // //         println(s"nextValue : $nv" )
  // //         checkGNumber(nv) 
  // //       }
  // //     )

  // //     println(s"checkGNumber.result : $result")

  // //     result
  // //   }
  // // }

  // def isPrime(value: Int): Boolean =
  //   if(value == 1) true
  //   else !(2L to Math.sqrt(value).toLong).exists(value % _ == 0)

  // def factorization(n:Long):List[Long] = {
  //   (2L to Math.sqrt(n).toLong).find(0 == n % _).map(x => x +: factorization(n / x)).getOrElse(List(n))
  // }

}
