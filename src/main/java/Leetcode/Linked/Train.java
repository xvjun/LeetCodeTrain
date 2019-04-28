package Leetcode.Linked;

import java.util.TreeMap;

public class Train {
    public static void main(String[] args) {

    }

    /**
     * 203. 移除链表元素(循环)
     * @param head
     * @param val
     * @return
     */
    public Leetcode.Linked.ListNode removeElements(Leetcode.Linked.ListNode head, int val) {
        Leetcode.Linked.ListNode dummyHead = new Leetcode.Linked.ListNode(-1);
        dummyHead.next = head;
        Leetcode.Linked.ListNode prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            }else{
                prev = prev.next;
            }
        }
        return dummyHead.next;
    }

    /**
     * 203. 移除链表元素(递归)
     * @param head
     * @param val
     * @return
     */
    public Leetcode.Linked.ListNode removeElements2(Leetcode.Linked.ListNode head, int val) {
        if (head == null) {return null;}
        head.next = removeElements2(head.next,val);
        return head.val == val ? head.next : head;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 21. 合并两个有序链表
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;

        while(l1 != null && l2 != null){
            if(l1.val<= l2.val){
                cur.next = l1;
                cur = cur.next;
                l1 = l1.next;
            }else{
                cur.next = l2;
                cur = cur.next;
                l2 = l2.next;
            }
        }
        if(l1 == null){
            cur.next = l2;
        }else{
            cur.next = l1;
        }
        return dummyHead.next;
    }

    /**
     * 83. 删除排序链表中的重复元素
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null){return null;}
        head.next = deleteDuplicates(head.next);
        if(head.val == head.next.val){head = head.next;}
        return head;
    }

    /**
     * 876. 链表的中间结点
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        ListNode dummyHead = head;
        while(dummyHead != null && dummyHead.next != null){
            dummyHead = dummyHead.next.next;
            head = head.next;
        }
        return head;
    }

    /**
     * 237. 删除链表中的节点
     * @param node
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * 206. 反转链表
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode newHead = null;
        while (head != null){
            ListNode temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
        }
        return newHead;
    }


}
