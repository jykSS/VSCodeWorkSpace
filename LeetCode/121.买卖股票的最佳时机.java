/*
 * @lc app=leetcode.cn id=121 lang=java
 *
 * [121] 买卖股票的最佳时机
 */

// @lc code=start
class Solution {
    public int maxProfit(int[] prices) {
        int minValue = Integer.MAX_VALUE;
        int profit = 0;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minValue) {
                minValue = prices[i];
            }
            profit = prices[i] - minValue;
            if (profit > maxprofit) {
                maxprofit = profit;
            }
        }
        return maxprofit;
    }
}
// @lc code=end
