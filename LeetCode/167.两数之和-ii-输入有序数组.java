import java.util.LinkedHashMap;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=167 lang=java
 *
 * [167] 两数之和 II - 输入有序数组
 */

// @lc code=start
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        Map<Integer,Integer> mapvalue = new LinkedHashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            mapvalue.put(numbers[i], i);
        }
        for (int i = 0; i < numbers.length; i++) {
            int temp = numbers[i];
            int key = target-temp;
            if (mapvalue.get(key)!=null) {
                int[] result ={i+1,mapvalue.get(key)+1};
                return result;
            }   
        }
        return null;
    }
}
// @lc code=end

