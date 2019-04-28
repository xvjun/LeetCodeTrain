package com.set

trait Set[E] {

  def add(e: E): Unit

  def remove(e: E): Unit

  def contains(e: E): Boolean

  def getSize: Int

  def isEmpty: Boolean

}
