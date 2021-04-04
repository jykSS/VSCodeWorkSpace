/***
 * 2. 两数相加 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 
 * 示例：
 * 
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4) 输出：7 -> 0 -> 8 原因：342 + 465 = 807
 * 
 * @param <ListNode>
 */

public class App {
    public static void main(String[] args) throws Exception {
        // 创建两个链表 // 第一个链表: 1-> 8 -> 7 -> 6 (在做加法运算代表的是6781) 
        ListNode l1 = new ListNode(6);
        // 这是第一个链表的第一个节点(不能用这个节点去往下加数据) 
        // 必须有一个指针去往第一个节点上去加数据 
        ListNode p = l1;
        // 这个指针节点会从链表的第一个节点一直往下走(直至最后一个节点) 
        p.next = new ListNode(7);
        p = p.next;
        p.next = new ListNode(8);
        p = p.next;
        p.next = new ListNode(1);
        // 第二个链表 
        ListNode l2 = new ListNode(9);
        ListNode q = l2;
        q.next = new ListNode(9);
        q = q.next;
        q.next = new ListNode(9);
        q = q.next;
        q.next = new ListNode(9);
        ListNode re = addTwoNumbers(l1, l2);
        while (re != null) {
            System.out.println(re.val);
            re = re.next;
        }
    }

    /**
     * Definition for singly-linked list. public class ListNode { int val; ListNode
     * next; ListNode(int x) { val = x; } }
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p=l1,q=l2,curr=dummyHead;
        int carry=0;//进位
        while(p!=null || q!=null){
            int x=(p!=null)?p.val:0;
            int y=(q!=null)?q.val:0;
            int sum=x+y+carry;
            carry=sum/10;
            curr.next=new ListNode(sum%10);
            curr=curr.next;
            if(p!=null) p=p.next;
            if(q!=null) q=q.next;
        }
        if(carry>0){
            curr.next=new ListNode(carry);
        }
        return dummyHead.next;
    }
}