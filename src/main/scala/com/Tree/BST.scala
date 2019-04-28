package com.Tree

import java.util

class BST[E <: Comparable[E]] {

   class Node(e1: E){
    var e:E = e1
    var left:Node = null
    var right:Node = null

  }

  private var root:Node = _
  private var size = 0

  def getSize():Int = {size}
  def isEmpty():Boolean = {size==0}

  def add(e:E): Unit ={
    root = add(root,e)
  }
  private def add(node:Node,e:E): Node ={
    if (node == null) {
      size+=1
      return new Node(e);
    }
    if(e.compareTo(node.e) < 0){
      node.left = add(node.left,e)
    }else if(e.compareTo(node.e) > 0){
      node.right = add(node.right,e)
    }
    node
  }

  def contains(e:E): Boolean ={
    contains(root,e)
  }
  private def contains(node:Node , e:E): Boolean ={
    if (node == null) {
      return false
    }
    var cmp = e.compareTo(node.e)
    if(cmp == 0){
      return true
    }else if(cmp < 0){
      contains(node.left,e)
    }else{
      contains(node.right,e)
    }
  }

  def preOrder(): Unit = {
    preOrder(root)
  }

  /**
    * 前序遍历（递归）
    */
  private def preOrder(node: Node): Unit = {
    if (node == null) return
    System.out.println(node.e)
    preOrder(node.left)
    preOrder(node.right)
  }

  /**
    * 非递归前序遍历
    */
  def preOrderNR(): Unit = {
    val stack = new util.Stack[Node]
    stack.push(root)
    while ( {
      !stack.isEmpty
    }) {
      val cur = stack.pop
      System.out.println(cur.e)
      if (cur.right != null) stack.push(cur.right)
      if (cur.left != null) stack.push(cur.left)
    }
  }

  /**
    * 中序遍历
    */
  def inOrder(): Unit = {
    inOrder(root)
  }

  def inOrder(node:Node): Unit = {
    if (node == null) return
    inOrder(node.left)
    System.out.println(node.e)
    inOrder(node.right)
  }

  /**
    * 返回中序遍历的list
    *
    * @return
    */
  def inorderTraversal: util.List[E] = {
    val res = new util.ArrayList[E]
    if (root == null) return res
    val stack = new util.Stack[Node]
    var cur = root
    while ( {
      cur != null || !stack.empty
    }) if (cur != null) {
      stack.push(cur)
      cur = cur.left
    }
    else {
      cur = stack.pop
      res.add(cur.e)
      cur = cur.right
    }
    res
  }

  /**
    * 后序遍历
    */
  def postOrder(): Unit = {
    postOrder(root)
  }

  def postOrder(node: BST[E]#Node): Unit = {
    if (node == null) return
    postOrder(node.left)
    postOrder(node.right)
    System.out.println(node.e)
  }

  /**
    * 层序遍历
    */
  def levelOrder(): Unit = {
    val queue = new util.LinkedList[Node]
    queue.add(root)
    while ( {
      !queue.isEmpty
    }) {
      val cur = queue.remove
      System.out.println(cur.e)
      if (cur.left != null) queue.add(cur.left)
      if (cur.right != null) queue.add(cur.right)
    }
  }

  /**
    * 寻找最小元素（递归）
    *
    * @return
    */
  def minimum: E = {
    if (size == 0) throw new IllegalArgumentException("BST is Empty!")
    minimum(root).e
  }

  private def minimum(node: Node): Node = {
    if (node.left == null) return node
    minimum(node.left)
  }

  /**
    * 寻找最小元素（迭代）
    *
    * @return
    */
  def minimumNR: E = {
    if (size == 0) throw new IllegalArgumentException("BST is Empty!")
    var tp = root
    while (tp.left != null)
      tp = tp.left
    tp.e
  }

  /**
    * 寻找最大元素（递归）
    *
    * @return
    */
  def maxmum: E = {
    if (size == 0) throw new IllegalArgumentException("BST is Empty!")
    maxmum(root).e
  }

  private def maxmum(node: Node): Node = {
    if (node.right == null) return node
    maxmum(node.right)
  }

  /**
    * 寻找最大元素（迭代）
    *
    * @return
    */
  def maxmumNR: E = {
    if (size == 0) throw new IllegalArgumentException("BST is Empty!")
    var tp = root
    while (tp.right != null) tp = tp.right
    tp.e
  }

  /**
    * 删除二分搜索树的最小值
    *
    * @return
    */
  def removeMin: E = {
    val ret = minimum
    root = removeMin(root)
    ret
  }

  /**
    * 删除已node为跟的二分搜索树中的最小节点
    * 返回删除节点后的树的跟
    *
    * @param node
    * @return
    */
  private def removeMin(node:Node): Node = {
    if (node.left == null) {
      val rightNode = node.right
      node.right = null
      size -= 1
      return rightNode
    }
    node.left = removeMin(node.left)
    node
  }

  /**
    * 删除二分搜索树的最大值
    *
    * @return
    */
  def removeMax: E = {
    val ret = maxmum
    root = removeMax(root)
    ret
  }

  /**
    * 删除已node为跟的二分搜索树中的最大节点
    * 返回删除节点后的树的跟
    *
    * @param node
    * @return
    */
  private def removeMax(node: Node): Node = {
    if (node.right == null) {
      val rightNode = node.left
      node.left = null
      size -= 1
      return rightNode
    }
    node.right = removeMax(node.right)
    node
  }

  def remove(e: E): Unit = {
    root = remove(root, e)
  }

  /**
    * 删除已node为根节点的树中值为e的节点（递归）
    * 返回删除节点后的树的根
    *
    * @param node
    * @param e
    * @return
    */
  private def remove(node: Node, e: E): Node = {
    if (node == null) return null
    if (e.compareTo(node.e) < 0) {
      node.left = remove(node.left, e)
      node
    }
    else if (e.compareTo(node.e) > 0) {
      node.right = remove(node.right, e)
      node
    }
    else { // e==node.e
      //左子树为空
      if (node.left == null) {
        val rightNode = node.right
        node.right = null
        size -= 1
        return rightNode
      }
      //右子树为空
      if (node.right == null) {
        val leftNode = node.left
        node.left = null
        size -= 1
        return leftNode
      }
      //待删除节点的左右子树都不为空的情况
      //找到比待删除节点大的最小节点，及戴删除节点的右子树的最小节点
      //用这个节点代替要删除的节点
      //注意对size变量的维护，removeMin中-1，后面又+1
      val successor = minimum(node.right)
      successor.right = removeMin(node.right)
      successor.left = node.left
      node.left = null
      node.right = null
      successor
    }
  }

  /**
    * 获得元素e在二分搜索树中的前驱(迭代)
    *
    * @param e
    * @return
    */
  def floor(e: E): Node = {
    if (root == null) return null
    var tp = root
    var rs:Node = null
    while (tp != null) if (e.compareTo(tp.e) > 0) if (tp.right != null) {
      rs = tp
      tp = tp.right
    }
    else return tp
    else if (e.compareTo(tp.e) < 0) if (tp.left != null) tp = tp.left
    else return rs
    else return tp
    rs
  }

  /**
    * 获得元素e在二分搜索树中的前驱(递归)
    *
    * @param e
    * @return
    */
  def floorNR(e: E): Node = {
    if (root == null) return null
    floorNR(root, e)
  }

  /**
    * 已node为根节点下，找到e的前驱
    *
    * @param node
    * @param e
    * @return
    */
  private def floorNR(node: Node, e: E): Node = {
    if (node == null) return null
    if (e.compareTo(node.e) > 0) {
      val tp = floorNR(node.right, e)
      if (tp != null) return tp
      node
    }
    else if (e.compareTo(node.e) < 0) floorNR(node.left, e)
    else node
  }

  /**
    * 获得元素e在二分搜索树中的后继(迭代)
    *
    * @param e
    * @return
    */
  def ceil(e: E): Node = {
    if (root == null) return null
    var tp = root
    var rs:Node = null
    val cmp = e.compareTo(tp.e)
    while ( {
      tp != null
    }) if (cmp > 0) if (tp.right != null) tp = tp.right
    else return rs
    else if (cmp < 0) if (tp.left != null) {
      rs = tp
      tp = tp.left
    }
    else return tp
    else return tp
    rs
  }

  def ceilNR(e: E): Node = {
    if (root == null) return null
    ceilNR(root, e)
  }

  /**
    * 已node为根节点下，找到e的后继
    *
    * @param node
    * @param e
    * @return
    */
  private def ceilNR(node: Node, e: E): Node = {
    if (node == null) return null
    val cmp = e.compareTo(node.e)
    if (cmp > 0) ceilNR(node.right, e)
    else if (cmp < 0) {
      val tp = ceilNR(node.left, e)
      if (tp != null) return tp
      node
    }
    else node
  }

  /**
    * 获得e的排名
    *
    * @param e
    * @return
    */
  def rank(e: E): Int = {
    val list = inorderTraversal
    var r = 0
    import scala.collection.JavaConversions._
    for (e1 <- list) {
      r += 1
      if (e1.compareTo(e) == 0) return r
    }
    0
  }

  /**
    * 获得排名rank的元素的值
    *
    * @param rank
    * @return
    */
  def select(rank: Int): E = {
    if (rank > size) return _:E
    val list = inorderTraversal
    list.get(rank-1)
  }

  override def toString: String = {
    val res = new StringBuilder
    generateBSTString(root, 0, res)
    res.toString
  }

  /**
    * 生成以node节点为根节点，深度为depth的描述二叉树的字符串
    *
    * @param node
    * @param depth
    * @param res
    */
  private def generateBSTString(node: Node, depth: Int, res: StringBuilder): Unit = {
    if (node == null) {
      res.append(generateDepthString(depth) + "null\n")
      return
    }
    res.append(generateDepthString(depth) + node.e + "\n")
    generateBSTString(node.left, depth + 1, res)
    generateBSTString(node.right, depth + 1, res)
  }

  private def generateDepthString(depth: Int) = {
    val res = new StringBuilder
    var i = 0
    while ( {
      i < depth
    }) {
      res.append("--")

      {
        i += 1; i - 1
      }
    }
    res.toString
  }

}

object BST{
  def main(args: Array[String]): Unit = {
    val bst = new BST[Integer]
    val a = Array(13, 15, 22, 33, 37, 41, 58, 50, 63, 42, 53)
    for (i <- a) {
      bst.add(i)
    }
    System.out.println(bst.select(5))
    System.out.println(bst.inorderTraversal)
    System.out.println(bst.rank(34))
    System.out.println(bst.ceilNR(54).e)
    bst.remove(3)
    bst.preOrder()



    bst.preOrder()
    bst.preOrderNR()
    bst.inOrder()
    bst.postOrder()
    bst.contains(8)
    bst.levelOrder()
    System.out.println(bst.toString)
    System.out.println(bst.maxmumNR)
    System.out.println(bst.maxmum)
    bst.preOrder()
    System.out.println("--------------\n" + bst.removeMax + "\n---------------")
    bst.preOrder()
  }
}
