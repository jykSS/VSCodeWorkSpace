import java.util.Comparator;
import java.util.PriorityQueue;

import javax.management.Query;

/*
 * @lc app=leetcode.cn id=23 lang=java
 *
 * [23] 合并K个升序链表
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        //小根堆
        Queue<ListNode> pqueue = new PriorityQueue<ListNode>((v1,v2)->v1.val-v2.val);
        for (ListNode node : lists) {
            if (node!=null) {
                pqueue.offer(node);
            }
        }
        ListNode result = new ListNode(0);
        ListNode tail = result;
        while (!pqueue.isEmpty()) {
            ListNode minNode = pqueue.poll();
            tail.next = minNode;
            tail=minNode;
            if (tail.next!=null) {
                pqueue.offer(tail.next);
            }
        }
        return result.next;
    }
}
// @lc code=end
