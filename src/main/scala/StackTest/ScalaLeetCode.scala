package StackTest


import scala.collection.mutable
import scala.collection.mutable._
object ScalaLeetCode {

  def main(args: Array[String]): Unit = {


//    println(inorderTraversal())
  }

  class TreeNode(var _value: Int) {
       var value: Int = _value
       var left: TreeNode = null
       var right: TreeNode = null
     }

  /**
    * 94. 二叉树的中序遍历
    * @param root
    * @return
    */
  def inorderTraversal(root: TreeNode): List[Int] = {
    var list = ArrayBuffer[Int]()

    var s = mutable.Stack[TreeNode]()
    var cur = root
    if (root != null) {
      s.push(root);
    }

    while(!s.isEmpty){
      if (cur.left != null) {
        s.push(cur.left);
      }
      if (cur.right != null) {
        s.push(cur.right);
      }
      list.append(cur.value);
      s.pop();
//      if(cur != null){
//        s.push(cur)
//        cur=cur.left
//      }else{
//        cur = s.pop()
//        list.append(cur.value)
//        cur = cur.right
//      }
    }
    list.toList
  }

  /**
    * 844. 比较含退格的字符串
    * @param S
    * @param T
    * @return
    */
  def backspaceCompare(S: String, T: String): Boolean = {
    var s = Stack[Char]()
    var t = Stack[Char]()
    for(i <- S){
      if(i=='#' && !s.isEmpty){s.pop()}
      else if(i!='#'){s.push(i)}
    }
    for(j <- T){
      if(j=='#' && !t.isEmpty){t.pop()}
      else if(j!='#'){t.push(j);}
    }
    if(s.equals(t)){true}
    else{false}
  }

  /**
    * 682. 棒球比赛
    * @param ops
    * @return
    */
  def calPoints(ops: Array[String]): Int = {
    var s = Stack[Int]()
    for(i <- ops){
      if(i.equals("C")){
        s.pop()
      }
      else if(i.equals("D")){
        s.push(s.top*2)
      }
      else if(i.equals("+")){
        val tp = s.pop()
        val tp2 = s.top+tp
        s.push(tp)
        s.push(tp2);
      }
      else {s.push(i.toInt)}
    }
    var sum = 0
    for(i <- s){
      sum=sum+i
    }
    sum
  }

  /**
    * 20. 有效的括号
    * @param s
    * @return
    */
  def isValid(s: String): Boolean = {
    var st = Stack[Char]()
    for(i <- s){
      if(st.length==0){
        st.push(i)
      }else if((st.top == '(' && i == ')') || (st.top == '[' && i == ']') || (st.top == '{' && i == '}')){
        st.pop()
      }else {
        st.push(i)
      }
    }
    st.length==0
  }


}
