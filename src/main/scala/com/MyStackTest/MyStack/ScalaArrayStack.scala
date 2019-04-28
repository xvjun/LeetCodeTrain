package com.MyStackTest.MyStack

import com.MyList.ScalaArray

import scala.reflect.ClassTag

class ScalaArrayStack[T:ClassTag]() extends Stack[T]{

  var array = new ScalaArray[T]();

  def this(capacity:Int){
    this()
    array = new ScalaArray[T](capacity);
  }

  override def getSize: Int = {
    array.getSize()
  }

  def getCapacity(): Int ={
    array.getCapacity()
  }

  override def isEmpty: Boolean = {
    array.isEmpty()
  }

  override def push(e: T): Unit = {
    array.addLast(e)
  }

  override def pop(): T = {
    array.removeLast()
  }

  override def peek(): T = {array.getLast()}

  override def toString():String = {
    val res = new StringBuilder
    res.append("Stack: ")
    res.append("[")
    for(i <- Range(0,array.getSize())){
      res.append(array.get(i).toString)
      if(i != array.getSize()-1){
        res.append(",")
      }
    }
    res.append("] Top")
    return res.toString
  }
}

object ScalaArrayStack{
  def main(args: Array[String]): Unit = {
    var stack = new ScalaArrayStack[Int]()
    for(i <- Range(0,5)){
      stack.push(i)
      println(stack)
    }
    stack.pop()
    println(stack)

  }
}