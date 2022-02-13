/*
 * @lc app=leetcode.cn id=14 lang=java
 *
 * [14] 最长公共前缀
 */

// @lc code=start
class Solution {
    public static String longestCommonPrefix(String[] strs) {
        // 公共前缀比所有字符串都短，随便选一个先
        String s = strs[0];
        for (String string : strs) {
            while (!string.startsWith(s)) {
                if (s.length() == 0)
                    return "";
                // 公共前缀不匹配就让它变短！
                s = s.substring(0, s.length() - 1);
            }
        }
        return s;
    }
}
// @lc code=end
