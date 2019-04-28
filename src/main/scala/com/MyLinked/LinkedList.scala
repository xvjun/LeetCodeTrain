package com.MyLinked

import scala.reflect.ClassTag

class LinkedList[T] {

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

  private var dummyHead = new Node()
  private var size = 0

  def getSize(): Int = {size}

  def isEmpty(): Boolean = {size==0}

  def add(index:Int,e:T): Unit ={
    if(index < 0 || index > size) throw new IllegalArgumentException("add failed,index need in [0,%d]".format(size))

    var prev = dummyHead
    for(i <- Range(0,index)){
        prev = prev.next
    }
    prev.next = new Node(e,prev.next)
    size+=1
  }

  def addFirst(e:T): Unit ={
    add(0,e)
  }

  def addLast(e:T): Unit ={
    add(size,e)
  }

  def get(index:Int):T = {
    if(index < 0 || index >= size) throw new IllegalArgumentException("GET failed,index need in [0,%d)".format(size))

    var cur = dummyHead.next
    for(i <- Range(0,index)){
        cur = cur.next
    }
    cur.e
  }

  def getFirst():T = {get(0)}
  def getLast():T = {get(size-1)}

  def set(index:Int,e:T): Unit ={
    if(index < 0 || index >= size) throw new IllegalArgumentException("SET failed,index need in [0,%d)".format(size))

    var cur = dummyHead.next
    for(i <- Range(0,index)){
        cur = cur.next
    }
    cur.e=e
  }

  def contains(e:T):Boolean = {
    var cur = dummyHead.next
    while (cur != null) {
      if (cur.e.equals(e)) {
        true
      }
      cur = cur.next
    }
    false
  }

  def remove(index:Int): T = {
    if(index < 0 || index >= size) throw new IllegalArgumentException("REmove failed,index need in [0,%d)".format(size))

    var prev = dummyHead
    for(i <- Range(0,index)){
        prev = prev.next
    }
    val retNode = prev.next
    prev.next = retNode.next
    retNode.next = null
    size-=1
    retNode.e
  }

  def removeFirst():T = {remove(0)}
  def removeLast():T = {remove(size-1)}

  def removeElement(e: T): Unit = {
    var prev = dummyHead
    var tp:Node = null
    while (prev.next != null && tp != null) {
      if (prev.next.e == e) tp = prev //todo: break is not supported
      prev = prev.next
    }
    if (prev.next != null) {
      val delNode = prev.next
      prev.next = delNode.next
      delNode.next = null
    }
  }

  override def toString: String = {
    var res = new StringBuilder()
    var cur = dummyHead.next
    while (cur != null) {
      res.append(cur + "->")
      cur = cur.next
    }
    res.append("NULL")
    res.toString()
  }

}

object LinkedList{
  def main(args: Array[String]): Unit = {

    var linkedList = new LinkedList[Int]()

    for(i <- Range(0,5)){
        linkedList.addFirst(i)
        println(linkedList)
    }
    linkedList.add(2,444)
    println(linkedList)

    linkedList.remove(2)
    println(linkedList)

    linkedList.removeFirst()
    println(linkedList)

    linkedList.removeLast()
    println(linkedList)


  }
}
