import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=56 lang=java
 *
 * [56] 合并区间
 */

// @lc code=start
class Solution {
    public int[][] merge(int[][] intervals) {
        List<int[]> intss = new ArrayList<>();
        Arrays.sort(intervals,(a,b)->{
            return a[0]-b[0];
        });
        int[] ints = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0]<=ints[1]) {
                ints[1]=Math.max(ints[1], intervals[i][1]) ;
            }else{
                //记录上一个的
                intss.add(ints);
                ints=intervals[i];
            }
        }
        //记录最后的
        intss.add(ints);
        return intss.toArray(new int[0][0]);
    }
}
// @lc code=end

