/*
 * @lc app=leetcode.cn id=606 lang=java
 *
 * [606] 根据二叉树创建字符串
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    StringBuilder sb = new StringBuilder("");

    public String tree2str(TreeNode root) {
        traverse(root);
        return sb.toString();
    }

    void traverse(TreeNode root) {
        if (root != null) {
            sb.append(root.val);
            if (root.left != null || root.right != null) {
                sb.append("(");
                traverse(root.left);
                sb.append(")");
                if (root.right != null) {
                    sb.append("(");
                    traverse(root.right);
                    sb.append(")");
                }
            }
        }
    }
}
// @lc code=end
