import java.util.*;

import javax.management.Query;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
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
