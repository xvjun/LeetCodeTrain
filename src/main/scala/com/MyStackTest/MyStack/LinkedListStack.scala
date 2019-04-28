package com.MyStackTest.MyStack

import Linked.LinkedList

import scala.reflect.ClassTag

class LinkedListStack[T:ClassTag]() extends Stack[T]{

  var list = new LinkedList[T]()

  override def getSize: Int = {
    list.getSize
  }

  override def isEmpty: Boolean = {list.isEmpty}

  override def push(e: T): Unit = {list.addFirst(e)}

  override def pop(): T = {list.removeFirst()}

  override def peek(): T = {list.getFirst}

  override def toString: String = {
    val res = new StringBuilder
    res.append("Stack : Top ")
    res.append(list)
    res.toString()
  }
}

object LinkedListStack{
  def main(args: Array[String]): Unit = {
    var stack = new LinkedListStack[Int]
    for(i <- Range(0,5)){
      stack.push(i)
      println(stack)
    }
    stack.pop()
    println(stack)

  }
}
