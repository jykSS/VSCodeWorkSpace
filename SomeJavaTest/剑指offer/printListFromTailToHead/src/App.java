import java.util.ArrayList;
import java.util.Stack;

/**
 * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
示例1
输入
复制
{67,0,24,58}
返回值
复制
[58,24,0,67]
 */

public class App {
    public static void main(String[] args) throws Exception {
        ListNode listNode=new ListNode(67);
        listNode.next=new ListNode(0);
        listNode.next.next=new ListNode(24);
        listNode.next.next.next=new ListNode(58);
        System.out.println(printListFromTailToHead(listNode));

    }
    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList <Integer> list = new ArrayList<>();
        if (listNode==null) {
            return list;
        }
        Stack<Integer> stack = new Stack<>();
        while (listNode!=null) {
            stack.push(listNode.val);
            listNode=listNode.next;
        }
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }
        return list;   
    }
}
