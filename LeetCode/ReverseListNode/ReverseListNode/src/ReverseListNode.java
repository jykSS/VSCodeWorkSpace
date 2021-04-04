public class ReverseListNode {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 迭代方式
     *
     * @param head 翻转前链表的头结点
     * @return 翻转后链表的头结点
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        // 始终指向链表的头结点
        ListNode pFirst = head;
        // 三个结点中的第一个结点
        ListNode pPre = pFirst;
        // 当前需要反转的结点
        ListNode pCur = head.next;
        // 下一次即将需要反转的结点
        ListNode pFuture = null;
        while (pCur != null) {
            pFuture = pCur.next;
            pPre.next = pFuture;
            pCur.next = pFirst;
            pFirst = pCur;
            pCur = pFuture;
            System.out.println("迭代的反转过程:" + getList(pFirst));
        }
        return pFirst;
    }

    /**
     * 递归方式
     *
     * @param head 翻转前链表的头结点
     * @return 翻转后链表的头结点
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pNext = head.next;
        head.next = null;
        ListNode reverseListNode = reverseList2(pNext);
        pNext.next = head;
        System.out.println("递归的反转过程:" + getList(reverseListNode));
        return reverseListNode;
    }

    /**
     * 将数组转化为链表
     *
     * @return 链表的头结点
     */
    public ListNode setList() {
        int a[] = { 1, 2, 3, 4, 5 };
        ListNode pHead = new ListNode(a[0]);
        ListNode pNext = pHead;
        for (int i = 1; i < a.length; i++) {
            ListNode pTemp = new ListNode(a[i]);
            pNext.next = pTemp;
            pNext = pTemp;

        }
        return pHead;
    }

    /**
     * @param pHead 链表头结点
     * @return 链表的字符串表达形式
     */
    public String getList(ListNode pHead) {
        int a[] = new int[5];
        ListNode pCur = pHead;
        int point = 0;
        while (pCur != null) {
            a[point] = pCur.val;
            pCur = pCur.next;
            point++;
        }
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int temp : a) {
            sb.append(temp + " ");
        }
        sb.append("]");
        return sb.toString();

    }

    public static void main(String args[]) {
        ReverseListNode test = new ReverseListNode();

        System.out.println("原始链表为:");
        ListNode node = test.setList();
        System.out.println(test.getList(node));

        System.out.println("-------------------------");

        ListNode nodeReverse = test.reverseList(node);
        System.out.println("迭代的最终结果:" + test.getList(nodeReverse));

        System.out.println("-------------------------");

        // 可以将之前反转的链表再反转回来
        ListNode nodeReverse2 = test.reverseList2(nodeReverse);
        System.out.println("递归的最终结果:" + test.getList(nodeReverse2));
    }

}
