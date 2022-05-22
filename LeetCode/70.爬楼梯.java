/*
 * @lc app=leetcode.cn id=70 lang=java
 *
 * [70] 爬楼梯
 */

// @lc code=start
class Solution {
    int [] remark ;
    public int climbStairs(int n) {
        remark = new int[n+1];
        return dp(n);
    }
    int dp(int n){
        if (n<=2) {
            return n;
        }
        if (remark[n]>0) {
            return remark[n];
        }
        remark[n] = dp(n-1)+dp(n-2);
        return remark[n];
    }
}
// @lc code=end

