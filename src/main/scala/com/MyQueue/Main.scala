package com.MyQueue

import java.util.Random


object Main {
  // 测试使用stack运行opCount个push和pop操作所需要的时间，单位：秒
  private def testStack(queue: Queue[Int], opCount: Int) = {
    val startTime = System.nanoTime
    val random = new Random

    for(i <- Range(0,opCount)){
      queue.enqueue(random.nextInt(Int.MaxValue))
    }

    for(i <- Range(0,opCount)){
      queue.dequeue
    }

    val endTime = System.nanoTime
    (endTime - startTime) / 1000000000.0
  }

  def main(args: Array[String]): Unit = {
    val opCount = 10000

    val ArrayQueue = new ArrayQueue[Int]()
    val time1 = testStack(ArrayQueue, opCount)
    System.out.println("ArrayQueue, time: " + time1 + " s")

    val LoopQueue = new LoopQueue[Int]()
    val time2 = testStack(LoopQueue, opCount)
    System.out.println("arrayQueue, time: " + time2 + " s")

    val LinkedListQueue = new LinkedListQueue[Int]()
    val time3 = testStack(LinkedListQueue, opCount)
    System.out.println("LinkedListQueue, time: " + time3 + " s")


    // 其实这个时间比较很复杂，因为LinkedListStack中包含更多的new操作
  }
}
