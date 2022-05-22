import java.util.LinkedList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=143 lang=java
 *
 * [143] 重排链表
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
    public void reorderList(ListNode head) {
        LinkedList<ListNode> linkList = new LinkedList<>();
        while (head != null) {
            linkList.addLast(head);
            head = head.next;
        }
        Integer index = 0;
        ListNode dump = new ListNode(0);
        head = dump;
        while (linkList.size() > 0) {
            if ((index & 1) == 0) {
                //偶数
                dump.next = linkList.removeFirst();
                dump = dump.next;
            } else {
                //奇数
                dump.next = linkList.removeLast();
                dump = dump.next;
            }
            index++;
        }
        dump.next=null;
        head = head.next;
    }
}
// @lc code=end

