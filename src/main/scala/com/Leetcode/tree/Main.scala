package com.Leetcode.tree

import scala.collection.mutable.{ArrayBuffer, ListBuffer}

class Main {

  def main(args: Array[String]): Unit = {

  }

  class TreeNode(var _value: Int) {
    var value: Int = _value
    var left: TreeNode = null
    var right: TreeNode = null
  }

  /**
    * 100. 相同的树
    * @param p
    * @param q
    * @return
    */
  def isSameTree(p: TreeNode, q: TreeNode): Boolean = {
    if(p == null && q == null){return true}
    if(p != null && q != null && p.value == q.value){
      isSameTree(p.left,q.left) && isSameTree(p.right,q.right)
    }else{
      false
    }
  }

  /**
    * 101. 对称二叉树
    * @param root
    * @return
    */
  def isSymmetric(root: TreeNode): Boolean = {
    if(root == null){true
    }else{
      function1(root.left,root.right)
    }
  }
  private def function1(q: TreeNode, p: TreeNode): Boolean ={
    if (q == null && p == null) return true
    if (q == null || p == null) return false
    if (p.value != q.value) return false
    return function1(p.right, q.left) && function1(p.left, q.right)
  }

}
