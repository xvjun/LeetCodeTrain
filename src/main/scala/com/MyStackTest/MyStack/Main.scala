package com.MyStackTest.MyStack

import java.util.Random


object Main {
  // 测试使用stack运行opCount个push和pop操作所需要的时间，单位：秒
  private def testStack(stack: Stack[Int], opCount: Int) = {
    val startTime = System.nanoTime
    val random = new Random

    for(i <- Range(0,opCount)){
        stack.push(random.nextInt(Int.MaxValue))
    }

    for(i <- Range(0,opCount)){
      stack.pop
    }

    val endTime = System.nanoTime
    (endTime - startTime) / 1000000000.0
  }

  def main(args: Array[String]): Unit = {
    val opCount = 1000000

    val arrayStack = new ScalaArrayStack[Int]()
    val time1 = testStack(arrayStack, opCount)
    System.out.println("ArrayStack, time: " + time1 + " s")

    val linkedListStack = new LinkedListStack[Int]
    val time2 = testStack(linkedListStack, opCount)
    System.out.println("LinkedListStack, time: " + time2 + " s")
    // 其实这个时间比较很复杂，因为LinkedListStack中包含更多的new操作
  }
}
