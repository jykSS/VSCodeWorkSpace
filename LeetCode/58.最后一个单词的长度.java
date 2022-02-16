/*
 * @lc app=leetcode.cn id=58 lang=java
 *
 * [58] 最后一个单词的长度
 */

// @lc code=start
class Solution {
    public int lengthOfLastWord(String s) {
        s = s.trim();
        String[] ss = s.split(" ");
        if (ss.length > 0) {
            return ss[ss.length - 1].length();
        } else {
            return 0;
        }
    }
}
// @lc code=end
