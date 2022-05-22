import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=142 lang=java
 *
 * [142] 环形链表 II
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head==null) {
            return null;
        }
        Set<ListNode> sets =new HashSet<>();
        ListNode dump = new ListNode();
        dump.next = head;
        dump = dump.next;
        while (dump == null ||dump.next!=null) {
            if (sets.contains(dump)) {
                 return dump;
            }else{
                sets.add(dump);
                dump=dump.next;
            }
        }
        return null;
    }
}
// @lc code=end

