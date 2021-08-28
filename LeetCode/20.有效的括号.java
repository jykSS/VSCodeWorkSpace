/*
 * @lc app=leetcode.cn id=20 lang=java
 *
 * [20] 有效的括号
 */

// @lc code=start
class Solution {
    public boolean isValid(String s) {
        StringBuilder s0 = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            switch(s.charAt(i)) {
                case '(':
                case '{':
                case '[':
                            s0 = s0.append(s.charAt(i));
                            break;
                case ')':
                            if (s0.length() > 0 && s0.charAt(s0.length() - 1) == '(') {
                                s0 = s0.deleteCharAt(s0.length() - 1);
                                break;
                            } else {
                                return false;
                            }
                case ']':
                            if (s0.length() > 0 && s0.charAt(s0.length() - 1) == '[') {
                                s0 = s0.deleteCharAt(s0.length() - 1);
                                break;
                            } else {
                                return false;
                            }
                case '}':
                            if (s0.length() > 0 && s0.charAt(s0.length() - 1) == '{') {
                                s0 = s0.deleteCharAt(s0.length() - 1);
                                break;
                            } else {
                                return false;
                            }
            }
        }

        return s0.length()==0;
    }
}
// @lc code=end
