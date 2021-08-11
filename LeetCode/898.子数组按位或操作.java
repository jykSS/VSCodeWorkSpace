/*
 * @lc app=leetcode.cn id=898 lang=java
 *
 * [898] 子数组按位或操作
 */
import java.util.*;
// @lc code=start
class Solution {
    public int subarrayBitwiseORs(int[] arr) {
        int max = 0;
        for (int i : arr) {
            max |= i;
        }
        Set<Integer> set= new HashSet<>();
        set.add(max);
        for (int i = 0; i < arr.length; i++) {
            int temp=0;
            for (int k = i; k < arr.length&&temp<max;k++) {
               temp=temp|arr[k];
               set.add(temp);
            }
        }
        return set.size();
    }
}


// @lc code=end

