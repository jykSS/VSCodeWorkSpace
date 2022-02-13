/*
 * @lc app=leetcode.cn id=942 lang=java
 *
 * [942] 增减字符串匹配
 */

// @lc code=start
class Solution {
    public int[] diStringMatch(String s) {
        int N = s.length();
        int lo = 0, hi = N;
        int[] ans = new int[N + 1];
        for (int i = 0; i < N; ++i) {
            if (s.charAt(i) == 'I') {
                ans[i] = lo++;
            } else {
                ans[i] = hi--;
            }
        }
        ans[N] = lo;
        return ans;
    }
}
// @lc code=end
