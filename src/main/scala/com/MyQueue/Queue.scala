package com.MyQueue


trait Queue[T] {

  def getSize: Int

  def isEmpty: Boolean

  def enqueue(e: T): Unit

  def dequeue: T

  def getFront: T

}
