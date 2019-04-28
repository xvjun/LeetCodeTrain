package com.set

import com.MyLinked.LinkedList

class LinkedListSet[E <: Comparable[E]] extends Set[E]{

  var list = new LinkedList[E]

  override def add(e: E): Unit = {
    if(!list.contains(e)){
      list.addFirst(e)
    }
  }

  override def remove(e: E): Unit = list.removeElement(e)

  override def contains(e: E): Boolean = list.contains(e)

  override def getSize: Int = list.getSize()

  override def isEmpty: Boolean = list.isEmpty()
}
