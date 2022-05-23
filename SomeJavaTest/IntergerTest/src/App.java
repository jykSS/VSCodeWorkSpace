import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

public class App {
    public static void main(String[] args) throws Exception {
        // Integer i1=200;
        // Integer i2=200;
        // if (i1==i2) {
        // System.out.println("............asdasdadas");
        // }
        // Integer i3=100;
        // Integer i4=100;
        // if (i3==i4) {
        // System.out.println("asdasdadasadasdasdasdasdasd");
        // }
        // String s="%%djasiojdioas";
        // int a = s.indexOf("%");
        // System.out.println(a);
        Integer X =null;
        Integer Y =3;
         Y+=X==null?0:X;
        System.out.println(Y);
        // int[] nums = {10,9,2,5,3,7,101,3};
        // int lengthOfLIS = lengthOfLIS(nums);
        // System.out.println(lengthOfLIS);
        // ListNode listNode = new ListNode(1);
        // listNode.next=new ListNode(2);
        // listNode.next.next=new ListNode(3);
        // listNode.next.next.next=new ListNode(4);
        // reorderList(listNode);
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void reorderList(ListNode head) {
        LinkedList<ListNode> linkList = new LinkedList<>();
        ListNode dump = head;
        while (dump != null) {
            linkList.addLast(dump);
            dump = dump.next;
        }
        Integer index = 0;
        dump = new ListNode(0);
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

    public static int lengthOfLIS(int[] nums) {
        int[] res = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int idx = Arrays.binarySearch(res, 0, len, num);
            idx = idx < 0 ? -idx - 1 : idx;
            res[idx] = num;
            if (idx == len) {
                len++;
            }
        }
        return len;
    }
}
