/*
 * @lc app=leetcode.cn id=264 lang=java
 *
 * [264] 丑数 II
 */

// @lc code=start
class Solution {
    public int nthUglyNumber(int n) {
         int index = 1 , result =0;
         for (int i = 1; i <= Integer.MAX_VALUE; i++) {
            if (isUgly(i)) {
                if (index==n) {
                   result=i;
                   break;
                }else{
                    index++;
                }
            }
         }
         return result;
    }

    private boolean isUgly(int n) {
        if (n <= 0) return false;
        // 如果 n 是丑数，分解因子应该只有 2, 3, 5
        while (n % 2 == 0) n /= 2;
        while (n % 3 == 0) n /= 3;
        while (n % 5 == 0) n /= 5;
        return n == 1;
    }
}
// @lc code=end

