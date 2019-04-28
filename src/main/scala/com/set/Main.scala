package com.set

import java.util
import java.util.ArrayList

import Tree.TreeSet.{FileOperation, Set}

object Main {

  private def testSet(set: Set[String], filename: String) = {
    val startTime = System.nanoTime
    System.out.println(filename)
    val words = new util.ArrayList[String]
    if (FileOperation.readFile(filename, words)) {
      System.out.println("Total words:" + words.size)
      import scala.collection.JavaConversions._
      for (word <- words) {
        set.add(word)
      }
      System.out.println("Total different words:" + set.getSize)
    }
    val endTime = System.nanoTime
    (endTime - startTime) / 1000000000.0
  }

  def main(args: Array[String]): Unit = {
    val filename = "com/Tree/txt/pride-and-prejudice.txt"

//    val linkedListSet = new LinkedListSet[String]
//    val t2 = testSet(linkedListSet, filename)
//    System.out.println("Linked set:" + t2 + "s")

    val bstSet = new BSTSet[String]
    val t1 = testSet(bstSet, filename)
    System.out.println("BST set:" + t1 + "s")
    System.out.println()
  }

}
