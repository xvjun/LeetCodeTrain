package com.set

import com.Tree.BST

class BSTSet[E <: Comparable[E]] extends Set[E] {

  var set = new BST[E]

  override def add(e: E): Unit = {set.add(e)}

  override def remove(e: E): Unit = set.remove(e)

  override def contains(e: E): Boolean = set.contains(e)

  override def getSize: Int = set.getSize()

  override def isEmpty: Boolean = set.isEmpty()
}
