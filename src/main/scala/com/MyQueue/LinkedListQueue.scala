package com.MyQueue

import scala.reflect.ClassTag

class LinkedListQueue[T:ClassTag] extends Queue[T] {

  private class Node(){
    var e:T = _
    var next:Node = _

    def this (t:T) {
      this ()    //调用主构造函数
      this.e=t
    }

    def this (e:T, node:Node) {
      this (e)    //调用辅助构造函数
      this.next=node
    }

    override def toString: String = {
      e.toString
    }

  }

  private var head:Node = _
  private var tail:Node = _
  private var size:Int = _


  override def getSize: Int = {size}

  override def isEmpty: Boolean = {size==0}

  override def enqueue(e: T): Unit = {
    if (tail == null) {
      tail = new Node(e)
      head = tail
    }else{
      tail.next = new Node(e)
      tail = tail.next
    }
    size+=1
  }

  override def dequeue: T = {
    if (isEmpty) {
      throw new IllegalMonitorStateException("can not dequeue from an empty queue.")
    }
    var retNode = head
    head = head.next
    retNode.next = null
    if (head == null) {
      tail = null
    }
    size-=1
    retNode.e
  }

  override def getFront: T = {
    if (isEmpty) {
      throw new IllegalMonitorStateException("queue is empty")
    }
    head.e
  }

  override def toString: String = {
    var res = new StringBuilder
    res.append("Queue front ")
    var cur = head
    while (cur != null) {
      res.append(cur + "->")
      cur = cur.next
    }
    res.append("NULL tail")
    res.toString()
  }
}

object LinkedListQueue{
  def main(args: Array[String]): Unit = {
    var queue = new LinkedListQueue[Int]()
    for(i <- Range(0,5)){
      queue.enqueue(i)
      println(queue)
    }
    queue.dequeue
    println(queue)
  }
}