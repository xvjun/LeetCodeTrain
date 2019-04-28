package com.Leetcode.Linked

import scala.annotation.tailrec

object sum {
  def main(args: Array[String]): Unit = {
    val a = Array(1, 2, 3, 4, 4, 5)
    System.out.println(sum2(a))

//    println(factorial(6))
  }

  def sum(arr: Array[Int]): Int = sum(arr, 0)

  private def sum(arr: Array[Int], l: Int): Int = {
    if (l == arr.length) 0
    arr(l) + sum(arr, l + 1)
  }

  def sum2(arr:Array[Int]): Int ={
    @tailrec
    def sum2(arr:Array[Int],aggr:Int,l:Int):Int = {
      if(l == arr.length){aggr}
      else{
        sum2(arr,arr(l)+aggr,l+1)
      }
    }
    sum2(arr,0,0)
  }


  def factorial(n:Int):Long = {
    @tailrec
    def factorial(main:Int,aggr:Int): Long ={
      if(main <= 0) aggr
      else factorial(main-1,main*aggr)
    }

    factorial(n,1)
  }

}
