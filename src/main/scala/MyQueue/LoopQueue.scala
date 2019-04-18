package MyQueue

import scala.reflect.ClassTag

class LoopQueue[T:ClassTag](length:Int) extends Queue[T] {
  private var data = new Array[T](length+1)
  private var front = 0
  private var tail = 0
  private var size = 0

  def this(){
    this(10)
  }

  def getCapacity(): Int = {data.length-1}

  override def getSize: Int = {size}

  override def isEmpty: Boolean = {front==tail}

  def resize(reCapacity: Int) = {
    var newData = new Array[T](reCapacity+1)
    for(i <- Range(0,size)){
      newData(i) = data((i+front) % data.length)
    }
    data = newData
    front=0
    tail=size
  }

  override def enqueue(e: T): Unit = {
    if((tail+1) % data.length == front){
      resize(getCapacity()*2)
    }
    data(tail) = e
    tail = (tail+1)%data.length
    size+=1
  }

  override def dequeue: T = {
    if (isEmpty) {
      throw new IllegalArgumentException("Cannot dequeue from an empty queue")
    }
    var ret = data(front)
    front = (front+1) % data.length
    size-=1
    if(size == getCapacity()/4 && getCapacity()/2 != 0){
      resize(getCapacity()/2)
    }
    ret
  }

  override def getFront: T = {
    if (isEmpty) {
      throw new IllegalArgumentException("Cannot dequeue from an empty queue")
    }
    data(front)
  }

  override def toString: String = {
    var res = new StringBuilder()
    res.append(String.format("Queue: size=%d,capacity=%d\n",Int.box(size),Int.box(data.length)))
    res.append("front [")
    var i = front
    while (i != tail) {
      res.append(data(i).toString)
      if ((i + 1) % data.length != tail) res.append(",")

      i = (i + 1) % data.length
    }
    res.append("] tail")
    res.toString()
  }
}

object LoopQueue{
  def main(args: Array[String]): Unit = {
    val queue = new LoopQueue[Int]
    for(i <- Range(0,15)){
      queue.enqueue(i)
      println(queue)
    }
    for(i <- Range(0,15)){
      queue.dequeue
      println(queue)
    }
  }
}