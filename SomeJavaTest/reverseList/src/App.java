import java.util.Stack;

public class App {
    public static void main(String[] args) throws Exception {
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        System.out.println(reverseList(head));
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        Stack<ListNode> stack = new Stack();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        head = stack.pop();
        head.next = null;
        ListNode result = head;
        while (!stack.isEmpty()) {
            ListNode temp = stack.pop();
            temp.next = null;
            head.next = temp;
            head = head.next;
        }
        return result;
    }
}
