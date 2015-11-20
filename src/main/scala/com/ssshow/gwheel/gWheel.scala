package com.ssshow.gwheel


import java.io.{PrintStream, File}
import scala.io.Source


object gWheel{

	def gcd(a: Long,b: Long): Long = if(b ==0) a else gcd(b, a%b)

	def possibleExtPair(e:Array[Long])  : Seq[(Long,Long)] = for{
		v1 <- e
		v2 <- e
		if(v1 != v2)
	} yield (v1 / gcd(v1,v2), v2 / gcd(v1,v2))

	def isPossible(p:Array[Long], t:Array[Long], e:Array[Long], q:(Long,Long)) : Boolean = {

			val pair = for{
				v1 <- p
				v2 <- t

				g = gcd(v1 * q._2, v2 * q._1)
				vv1 = (v1 * q._2) / g
				vv2 = (v2 * q._1) / g

			} yield (vv2,vv1)
			val extPairs = possibleExtPair(e).toSet
			pair.par.exists(p => extPairs.par.contains(p))
	}

	def ratio(p:(Long,Long)) : (Long,Long) = {
		val g = gcd(p._1, p._2)
		(p._1 / g, p._2 / g)
	}

  def readTestCase(iter: Iterator[String]) : Seq[TestCase] = {

  	val count = iter.next.toInt

  	def splitToLong(iter: Iterator[String]) : Array[Long] = iter.next.split(" ").map(_.toLong)

  	for(i <- 0 to count - 1) yield {
  		iter.next.split(" ") // ignore
  		iter.next.split(" ") // ignore
  		val p = splitToLong(iter)
  		val e = splitToLong(iter)
  		val t = splitToLong(iter)
 		
  		val q = for(j <- 0 to iter.next.toInt - 1) yield {
  			// println(j)
  			val Array(x,y) = splitToLong(iter)
  			(x,y)
  		}

  		TestCase(p,e,t,q.toArray)
  	}
  }


	def main(args: Array[String]) {
    val in = Source.fromFile(new File("B-large-practice.in"))
    val out = new PrintStream(new File("B-large-practice.out2"))
    try {
      process(in.getLines) { s: String => out.println(s) }
    } finally {
      out.flush; out.close
    }
  }

	def process(iter: Iterator[String])(pr: String => Unit) {

		def result(isPossible:Boolean) = if(isPossible) "Yes" else "No"

		val testCases = readTestCase(iter)
		for (i <- 1 to testCases.size) yield {
			pr(s"Case #$i:")
			val tc = testCases(i - 1)
			tc.q.foreach(qq => pr(s"${result(isPossible(tc.p,tc.t,tc.e, qq))}"))
		}
	}	

	case class TestCase(
	  	p:Array[Long],
	  	e:Array[Long],
	  	t:Array[Long],
	  	q:Array[(Long,Long)])
}