import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        int a =2;
        int b = 3;
       int  c = gcd(a,b);
       System.out.println(c);
    }
    /**
     * 返回最大公约数
     * @param a
     * @param b
     * @return
     */
    public static int gcd(int a,int b){
        if(b==0){
            return a;
        }
        return gcd(b,a%b);
    }
    class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {
            if (root == null) {
                return null;
            }
            List<List<Integer>> levelOrder = new ArrayList<>();
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                int count = queue.size();
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    TreeNode treeNode = queue.poll();
                    list.add(treeNode.val);
                    if (treeNode.left != null) {
                        queue.add(treeNode.left);
                    }
                    if (treeNode.right != null) {
                        queue.add(treeNode.right);
                    }
                }
                levelOrder.add(list);
            }
            return levelOrder;
        }
    }
}
