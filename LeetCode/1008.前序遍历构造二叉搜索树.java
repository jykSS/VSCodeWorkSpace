/*
 * @lc app=leetcode.cn id=1008 lang=java
 *
 * [1008] 前序遍历构造二叉搜索树
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
    public TreeNode bstFromPreorder(int[] preorder) {
        return build(preorder,0,preorder.length-1);
    }
    TreeNode build(int[] preorder,int start,int end){
        if (start>end) {
            return null;
        }
        int temp;
        for (temp = start; temp <= end; temp++) {
            if (preorder[temp]>preorder[start]) {
                break;
            }
        }
        TreeNode treeNode = new TreeNode(preorder[start]);
        //left
        TreeNode treeleft = build(preorder,start+1,temp-1);
        //right
        TreeNode treeright=build(preorder,temp,end);
        treeNode.left =treeleft;
        treeNode.right =treeright;
        return treeNode;
    }
}
// @lc code=end

