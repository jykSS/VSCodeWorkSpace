/*
 * @lc app=leetcode.cn id=236 lang=java
 *
 * [236] 二叉树的最近公共祖先
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        // 我自己是叛徒之一，但是我不一定受到惩罚，因为要找的是叛徒的老板，但是你代表你的团队找到了一个叛徒
        if (root == p || root == q) {
            return root;
        }
        // 在各层级团队找叛徒
        TreeNode first_traitor = lowestCommonAncestor(root.left, p, q);
        // 在各层级团队找叛徒
        TreeNode second_traitor = lowestCommonAncestor(root.right, p, q);

        // 我们团队找到两叛徒了，game over,倒霉的就是我。接下去我会被一层一层的往上提交，最后到老板那。
        if (first_traitor != null && second_traitor != null) {
            return root;
        }
        // 我们团队只找到一个叛徒，这个叛徒代表整个团队，就用这个叛徒来向上（回溯）提交甩锅
        // p.s. 太好了，跟我这个小manager没啥关系
        if (first_traitor != null) {
            return first_traitor;
        }
        if (second_traitor != null) {
            return second_traitor;
        }

        // 我们是最干净的团队！
        return null;
    }
}
// @lc code=end
