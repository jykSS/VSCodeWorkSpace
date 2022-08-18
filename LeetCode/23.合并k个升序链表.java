import java.util.PriorityQueue;
import java.util.Queue;


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
        Queue<ListNode> queue=new PriorityQueue<>((a,b)->a.val-b.val);
        ListNode result = new ListNode(-1);
        ListNode temp = result;
        for (int i = 0; i < lists.length; i++) {
            if (lists[i]!=null) {
                queue.add(lists[i]);
            }
        }
        while (!queue.isEmpty()) {
            ListNode listNode=queue.poll();
            temp.next=listNode;
            temp=temp.next;
            if (listNode.next!=null) {
                queue.offer(listNode.next);
            }
        }
        return result.next;
    }
}
// @lc code=end
