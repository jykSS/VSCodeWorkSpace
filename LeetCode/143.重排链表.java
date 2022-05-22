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
        ListNode dump = head;
        while (dump!=null) {
            linkList.addLast(dump);
            dump=dump.next;
        }
        Integer index = 0;
        dump=new ListNode(0);
        head = dump ; 
        while (linkList.size()>0) {
            if ((index & 1)==1) {
                //奇数
                dump.next=linkList.removeFirst();
                dump= dump.next;
            }else{
                //偶数
                dump.next=linkList.removeLast();
                dump= dump.next;
            }
            index++;
        }
        head = head.next;
    }
}
// @lc code=end

