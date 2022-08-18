/*
 * @lc app=leetcode.cn id=876 lang=java
 *
 * [876] 链表的中间结点
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode l1 = head, l2 = head;
        while (l2!=null) {
            if (l2.next==null) {
                break;
            }
            l2= l2.next.next;
            l1=l1.next;
        }
        return l1; 
    }
}
// @lc code=end

