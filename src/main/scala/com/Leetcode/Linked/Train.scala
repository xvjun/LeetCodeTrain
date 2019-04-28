package com.Leetcode.Linked




object Train {

  def main(args: Array[String]): Unit = {
    var root = new ListNode(1)
    var h1 = new ListNode(2)
    root.next = h1
    var h2 = new ListNode(6)
    h1.next = h2
    var h3 = new ListNode(3)
    h2.next = h3
    var h4 = new ListNode(4)
    h3.next = h4
    var h5 = new ListNode(5)
    h4.next = h5
    var h6 = new ListNode(6)
    h5.next = h6
    removeElements2(root,6)
  }

  class ListNode(var _x: Int = 0) {
    var next: ListNode = null
    var x: Int = _x
  }

  /**
    * 203. 移除链表元素（循环）
    * @param head
    * @param `val`
    * @return
    */
  def removeElements(head: ListNode, `val`: Int): ListNode = {
    var dummyHead:ListNode  = new ListNode(-1)
    dummyHead.next = head

    var prev = dummyHead
    while (prev.next != null) {
      if (prev.next.x == `val`) {
        prev.next = prev.next.next
      }else{
        prev = prev.next
      }
    }
    dummyHead.next
  }

  /**
    * 203. 移除链表元素(递归)
    * @param head
    * @param `val`
    * @return
    */
  def removeElements2(head: ListNode, `val`: Int): ListNode = {
    if (head == null ) {return null}
    var res = removeElements2(head.next,`val`)
    if (head.x == `val`) {res}
    else{
      head.next = res
      head
    }
  }

  class ListNode2(var _x: Int = 0) {
    var next: ListNode2 = null
    var x: Int = _x
  }

  /**
    * 21. 合并两个有序链表
    * @param l1
    * @param l2
    * @return
    */
  def mergeTwoLists(l1: ListNode2, l2: ListNode2): ListNode2 = {
    val dummyHead = new ListNode2()
    var p1 = l1
    var p2 = l2
    var cur = dummyHead
    while(p1 != null && p2 != null){
      if (p1.x <= p2.x) {
        cur.next = p1
        cur = cur.next
        p1 = p1.next
      }else{
        cur.next = p2
        cur = cur.next
        p2 = p2.next
      }
    }
    if (p1 == null) {cur.next = p2}
    else{cur.next = p1}
    dummyHead.next
  }

  /**
    * 83. 删除排序链表中的重复元素
    * @param head
    * @return
    */
  def deleteDuplicates(head: ListNode): ListNode = {
    if(head == null || head.next == null){
      return head
    }
    head.next = deleteDuplicates(head.next)
    if(head.x == head.next.x){head.next}
    else {head}
  }

  /**
    * 876. 链表的中间结点
    * @param head
    * @return
    */
  def middleNode(head: ListNode): ListNode = {
    var dummyHead = head
    var cur = head
    while(dummyHead != null && dummyHead.next != null){
      dummyHead = dummyHead.next.next
      cur = cur.next
    }
    cur
  }

//  /**
//    * 206. 反转链表
//    *
//    * @param head
//    * @return
//    */
//  def reverseList(head: ListNode): ListNode = {
//
//  }

}
