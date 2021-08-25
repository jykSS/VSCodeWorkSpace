/*
 * @lc app=leetcode.cn id=357 lang=java
 *
 * [357] 计算各个位数不同的数字个数
 */

// @lc code=start
class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        switch (n)
        {
            case 0: return 1;
            case 1: return 10;
            case 2: return 91;
            case 3: return 739;
            case 4: return 5275;
            case 5: return 32491;
            case 6: return 168571;
             case 7: return 712891;
            case 8: return 2345851;
            case 9: return 5611771;
            default : return 8877691;
        }
    }
}
// @lc code=end

