package List

import scala.reflect.ClassTag


class ScalaArray[T:ClassTag](length:Int) {

  private var data = new Array[T](length)
  private var Size = 0

  /**
    *辅助构造器，等同于java的无参构造函数，默认大小10
    */
  def this(){
    this(10)
  }

  def getSize(): Int ={
    Size
  }

  def getFirst(): T ={
    get(Size-1)
  }

  def getLast(): T ={
    get(0)
  }

  def getCapacity(): Int ={
    data.length
  }

  def isEmpty(): Boolean ={
    Size == 0
  }

  def get(index:Int): T ={
    if(index < 0 || index >= Size)
      throw new IllegalArgumentException(String.format("Get faild,need index in [0,%d]", Int.box(Size)))
    data(index)
  }

  def set(index:Int,e:T): Unit ={
    if(index < 0 || index >= Size)
      throw new IllegalArgumentException(String.format("Set faild,need index in [0,%d]", Int.box(Size)))
    data(index) = e
  }

  def add(index:Int,e:T): Unit ={
    if(Size == data.length){
      reSize(2*data.length)
    }
    if(index < 0 || index > Size)
      throw new IllegalArgumentException(String.format("Add faild,need index in [0,%d]", Int.box(Size)))
    for(i <- Range(index,Size).reverse){
      data(i+1) = data(i)
    }
    data(index) = e
    Size+=1
  }

  private def reSize(resize:Int): Unit ={
    var newData = new Array[T](resize)
    for(i <- Range(0,Size)){
      newData(i) = data(i)
    }
    data=newData
  }

  def addLast(e:T): Unit ={
    add(Size,e)
  }

  def addFirst(e:T): Unit ={
    add(0,e)
  }

  override def toString: String = {
    var res = new StringBuilder()
    res.append(String.format("Array: size=%d,capacity=%d\n",Int.box(Size),Int.box(data.length)))
    res.append("[")
    for(i <- Range(0,Size)){
      res.append(data(i).toString)
      if(i != Size-1){
        res.append(",")
      }
    }
    res.append("]")
    res.toString()
  }

  def contains(e:T): Boolean ={
    for(i <- data)
      if(i.equals(e)) true
    false
  }

  def find(e:T): Int ={
    for(i <- Range(0,Size))
      if(data(i).equals(e)) i
    -1
  }

  def remove(index:Int): T ={
    if(index < 0 || index >= Size)
      throw new IllegalArgumentException(String.format("remove faild,need index in [0,%d]", Int.box(Size)))
    val ret = data(index)
    for(i <- Range(index+1,Size))
      data(i-1) = data(i)
    Size-=1
//    data(Size)=None
    if(Size == data.length/3){
      reSize(data.length/2)
    }
    ret
  }

  def removeFirst(): T ={
    remove(0)
  }

  def removeLast():T={
    remove(Size-1)
  }

  def removeElement(e:T): Boolean ={
    for(i <- Range(0,Size))
      if(data(i).equals(e)){
        remove(i)
        true
      }
    false
  }

  def removeAllElement(e:T): Boolean ={
    var sum = 0
    for(i <- Range(0,Size).reverse)
      if(data(i).equals(e)){
        remove(i)
        sum+=1
      }
    if(sum==0) false
    else true
  }

}

object ScalaArray {
  def main(args: Array[String]): Unit = {
    var array = new ScalaArray[Int](10)
    for(i <- Range(0,10)){
      array.addLast(i)

    }
    array.remove(2)
    println(array)
    array.set(1,100)
    println(array)
    array.addFirst(10)
    array.addLast(10)
    array.addLast(10)
    println(array)
    array.removeAllElement(10)
    println(array)
    array.remove(2)
    array.remove(2)
    array.remove(2)
    array.remove(2)
    array.remove(2)
    array.remove(2)
    array.remove(2)
    println(array)


  }
}
