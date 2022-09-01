import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode.cn id=971 lang=java
 *
 * [971] 翻转二叉树以匹配先序遍历
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
    int i =0;
    List<Integer> result = new ArrayList<>();
    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        if (dfsVoyage(root,voyage)) {
            return result;
        }
        return Arrays.asList(-1);
    }
    boolean dfsVoyage(TreeNode root, int[] voyage){
        if (root==null) {
            return true;
        }
        if (root.val!=voyage[i++]) {
            return false;
        }
        if (root.left!=null&&root.left.val!=voyage[i]) {
            result.add(root.val);
            return dfsVoyage(root.right,voyage)&&dfsVoyage(root.left,voyage);
        }
        return dfsVoyage(root.left,voyage)&&dfsVoyage(root.right,voyage);
    }
}
// @lc code=end

