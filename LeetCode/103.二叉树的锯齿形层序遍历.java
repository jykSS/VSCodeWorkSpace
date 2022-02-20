import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingDeque;

/*
 * @lc app=leetcode.cn id=103 lang=java
 *
 * [103] 二叉树的锯齿形层序遍历
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> q = new LinkedList<>();
        boolean flag = false;// 当flag为true时从左往右遍历，否则从右往左遍历
        q.offer(root);
        while (!q.isEmpty()) {
            int count = q.size();
            List<Integer> list = new LinkedList<>();
            while (count > 0) {
                count--;
                if (flag) { // 反向遍历
                    TreeNode temp = q.pollLast();
                    list.add(temp.val);
                    if (temp.right != null) {
                        q.offerFirst(temp.right);
                    }
                    if (temp.left != null) {
                        q.offerFirst(temp.left);
                    }
                } else {// 正向遍历
                    TreeNode temp = q.pollFirst();
                    list.add(temp.val);
                    if (temp.left != null) {
                        q.offerLast(temp.left);
                    }
                    if (temp.right != null) {
                        q.offerLast(temp.right);
                    }
                }
            }
            res.add(list);
            flag = !flag;
        }
        return res;
    }
}
// @lc code=end
