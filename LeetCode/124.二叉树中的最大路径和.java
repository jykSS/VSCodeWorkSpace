/*
 * @lc app=leetcode.cn id=124 lang=java
 *
 * [124] 二叉树中的最大路径和
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int res = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        oneSideMax(root);
        return res;
    }
    int oneSideMax(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftSideMax=Math.max(0, oneSideMax(root.left)) ;
        int rightSideMax=Math.max(0, oneSideMax(root.right)) ;
        int pathSideMax = rightSideMax+leftSideMax+root.val;
        res = Math.max(res, pathSideMax);
        return Math.max(leftSideMax, rightSideMax)+root.val;
    }
}
// @lc code=end

