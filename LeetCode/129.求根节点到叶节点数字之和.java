/*
 * @lc app=leetcode.cn id=129 lang=java
 *
 * [129] 求根节点到叶节点数字之和
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
    int sum = 0;
    public int sumNumbers(TreeNode root) {
        sumTools(root,0);
        return sum;
    }
    void sumTools(TreeNode root,int val){
        if (root==null) {
            return;
        }
        val=val*10+root.val;
        if (root.left==null&&root.right==null) {
            sum+=val;
        }
        sumTools(root.left,val);
        sumTools(root.right,val);
    }

}
// @lc code=end

