import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class App {
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(2);
        root.right.left = new TreeNode(4);
        preorderTraversal(root);
        System.out.println(Arrays.toString(list.toArray()));
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                list.add(root.val);
                stack.push(root);
                root = root.left;
            }
            if (!stack.isEmpty()) {
                root = stack.pop();
                root = root.right;
            }
        }
        return list;
    }
}