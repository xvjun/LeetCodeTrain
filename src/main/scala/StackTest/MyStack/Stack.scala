package StackTest.MyStack

trait Stack[T] {

  def getSize: Int

  def isEmpty: Boolean

  def push(e: T): Unit

  def pop: T

  def peek: T

}
