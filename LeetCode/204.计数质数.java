/*
 * @lc app=leetcode.cn id=204 lang=java
 *
 * [204] 计数质数
 */

// @lc code=start
class Solution {
    public int countPrimes(int n) {
        if (n<3) {
            return 0;
        }
        int count = 1;
        boolean[] f = new boolean[n];
        //偶数一定不是质数
        for (int i = 3; i < n; i+=2) {
            //是合数就不需要统计
            if (f[i]) {
                continue;
            }
            //如果i是质数,那么i的倍数就是合数
            for (int j = i; j <n ; j+=i) {
                f[j]=true;
            }
            count++;
        }
        return count;
    }
}
// @lc code=end

