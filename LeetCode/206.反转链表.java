/*
 * @lc app=leetcode.cn id=206 lang=java
 *
 * [206] 反转链表
 */

// @lc code=start
/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode() {} ListNode(int val) { this.val = val; } ListNode(int val,
 * ListNode next) { this.val = val; this.next = next; } }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        // ListNode pre = null;
        // ListNode cur = head;
        // while (cur != null) {
        // ListNode temp = cur.next;
        // cur.next = pre;
        // pre = cur;
        // cur = temp;
        // }
        // return pre;
        if (null == head) {
            return head;
        }
        ListNode cur = head;
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = cur;
        // 1-2-3-4-5
        // 2-1-3-4-5
        // 把next进行提前
        while (cur.next != null) {
            ListNode temp = cur.next;
            cur.next = temp.next;
            temp.next = dummyNode.next;
            dummyNode.next = temp;
        }
        return dummyNode.next;
    }
}
// @lc code=end
