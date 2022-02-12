import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=1 lang=java
 *
 * [1] 两数之和
 */

// @lc code=start
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int left = map.get(nums[i]);
                return new int[]{left,i};
            }else{
                map.put(target-nums[i], i);
            }
        }
        return new int[2];
    }
}
// @lc code=end

