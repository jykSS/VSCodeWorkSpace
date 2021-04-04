import java.util.Stack;

public class App {
    public static void main(String[] args) throws Exception {
        SingleLinkedListDemo s = new SingleLinkedListDemo();
        HeroNode h1 = new HeroNode(1);
        HeroNode h2 = new HeroNode(3);
        HeroNode h3 = new HeroNode(4);
        HeroNode h4 = new HeroNode(2);
        s.addByOrder(h1);
        s.addByOrder(h2);
        s.addByOrder(h3);
        s.addByOrder(h4);
        System.out.println("正序");
        s.list();

        System.out.println("逆序");
        reversePrintNode(s.getHead());

    }

    private static void reversePrintNode(HeroNode head) {
        //若为空链表则无法打印
        if (head.next == null) {
            return;
        }
        //创建一个栈
        Stack < HeroNode > heroNodes = new Stack < > ();
        //记录当前节点
        HeroNode cur = head.next;
        //遍历节点
        while (cur != null) {
            heroNodes.push(cur);
            cur = cur.next;
        }
        while (heroNodes.size() > 0) {
            System.out.println(heroNodes.pop());
        }
    }
}