import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=300 lang=java
 *
 * [300] 最长递增子序列
 */

// @lc code=start
class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] res = new int[nums.length];
        int len = 0;
        for (int num: nums) {
            int idx = Arrays.binarySearch(res, 0, len, num);
            //是否是存在里面最小的,是的话直接替换原来的index位置的
            idx = idx < 0? -idx - 1: idx;
            res[idx] = num;
            //不一定是最佳 但长度是一样的,会替换
            if (idx == len) {
                len++;
            }
        }
        return len;
    }
}
// @lc code=end

