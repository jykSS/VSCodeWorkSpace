import java.util.ArrayList;
import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=15 lang=java
 *
 * [15] 三数之和
 */

// @lc code=start
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> threeSum = new ArrayList<>();
        if (nums.length < 3) {
            return threeSum;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) { // 固定当前数
            if (nums[i] > 0) {
                return threeSum; // 后面的数更大，结果更加不可能
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;// 如果当前数和前一个一样，那么跳过此次
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    threeSum.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 直到找到和之前不一样的，为了保证*下一次*的结果正确
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum > 0) {
                    right--; // 结果太大，那么右指针移动
                } else {
                    left++; // 结果太小，那么左指针移动
                }
            }
        }
        return threeSum;
    }
}
// @lc code=end
