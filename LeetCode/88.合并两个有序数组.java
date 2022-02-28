/*
 * @lc app=leetcode.cn id=88 lang=java
 *
 * [88] 合并两个有序数组
 */

// @lc code=start
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] temp = new int[n + m];
        int l1 = 0;
        int l2 = 0;
        int tempi = 0;
        while (l1 < m || l2 < n) {
            if (l1 == m) {
                temp[tempi] = nums2[l2++];
            } else if (l2 == n) {
                temp[tempi] = nums1[l1++];
            } else if (nums1[l1] <= nums2[l2]) {
                temp[tempi] = nums1[l1++];
            } else {
                temp[tempi] = nums2[l2++];
            }
            tempi++;
        }
        for (int i = 0; i < temp.length; i++) {
            nums1[i] = temp[i];
        }
    }
}
// @lc code=end
