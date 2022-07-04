import java.util.List;

import App.ListNode;

/*
 * @lc app=leetcode.cn id=82 lang=java
 *
 * [82] 删除排序链表中的重复元素 II
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
    public ListNode deleteDuplicates(ListNode head) {
        if (head==null) {
            return head;
        }
        ListNode dump = new ListNode(-1);
        ListNode tmpp = dump;
        ListNode tmpq = head;

        while (tmpq!=null) {
            if (tmpp.val==temp&&head.next!=null) {
                while (condition) {
                    
                    tmpq=tmpq.next;
                }
                tmpq=tmpq.next;
                if (tmpq==null) {
                    
                }
            }

        }
    }
}
// @lc code=end

