import java.util.List;

/*
 * @lc app=leetcode.cn id=25 lang=java
 *
 * [25] K 个一组翻转链表
 */

// @lc code=start
/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode() {} ListNode(int val) { this.val = val; } ListNode(int val,
 * ListNode next) { this.val = val; this.next = next; } }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode cur = head;
        int length = 0;
        while (cur != null && length != k) {
            cur = cur.next;
            length++;
        }
        if (length == k) {
            ListNode pre = reverseKGroup(cur, k);
            while (k-- > 0) {
                ListNode next = head.next;
                head.next = pre;
                pre = head;
                head = next;
            }
            return pre;
        } else {
            return head;
        }
    }
}
// @lc code=end
