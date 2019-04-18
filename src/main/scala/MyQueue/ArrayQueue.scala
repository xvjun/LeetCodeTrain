package MyQueue

import List.ScalaArray

import scala.reflect.ClassTag

class ArrayQueue[T:ClassTag]() extends Queue[T]{

  var array = new ScalaArray[T]()

  def this(capacity:Int){
    this()
    array = new ScalaArray[T](capacity)
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

  override def enqueue(e: T): Unit = {
    array.addLast(e)
  }

  override def dequeue: T = {
    array.removeFirst()
  }

  override def getFront: T = {
    array.getFirst()
  }

  override def toString: String = {
    val res = new StringBuilder
    res.append("Queue: ")
    res.append("front[")
    for(i <- Range(0,array.getSize())){
      res.append(array.get(i).toString)
      if(i != array.getSize()-1){
        res.append(",")
      }
    }
    res.append("] tail")
    return res.toString
  }
}

object ArrayQueue{
  def main(args: Array[String]): Unit = {
    var queue = new ArrayQueue[Int]()
    for(i <- Range(0,5)){
      queue.enqueue(i)
      println(queue)
    }
    queue.dequeue
    println(queue)
  }
}