import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=704 lang=java
 *
 * [704] 二分查找
 */

// @lc code=start
class Solution {
    public int search(int[] nums, int target) {
        int binarySearch = Arrays.binarySearch(nums, target);
        if (binarySearch<0) {
            return -1;
        }
        return binarySearch;
    }
}
// @lc code=end

