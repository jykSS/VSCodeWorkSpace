import java.util.Queue;

/*
 * @lc app=leetcode.cn id=4 lang=java
 *
 * [4] 寻找两个正序数组的中位数
 */

// @lc code=start
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length==0) {
            return nums2;
        }
        Queue<Integer> qlittle = new PriorityQueue<Integer>();
        Queue<Integer> qlarge = new PriorityQueue<Integer>((v1,v2)->v2-v1);


        return 0;

    }
}
// @lc code=end

