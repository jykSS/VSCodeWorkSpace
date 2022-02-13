/*
 * @lc app=leetcode.cn id=9 lang=java
 *
 * [9] 回文数
 */

// @lc code=start
class Solution {
    public boolean isPalindrome(int x) {
        // 如上所述，当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        if (x < 0 || (x != 0 && x % 10 == 0)) {
            return false;
        }
        int res = 0;
        // 只取一半进行反转
        while (x > res) {
            res = res * 10 + x % 10;
            x = x / 10;
        }
        // 12321 中位数不影响判断
        return (x == res || x == res / 10);
    }
}
// @lc code=end
