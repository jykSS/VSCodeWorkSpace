/*
 * @lc app=leetcode.cn id=97 lang=java
 *
 * [97] 交错字符串
 */

// @lc code=start
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        char[] c1 = s1.toCharArray(), c2 = s2.toCharArray(), c3 = s3.toCharArray();
        return dfs(c1, c2, c3, 0, 0, 0, new boolean[s1.length()+1][s2.length()+1]);
    }

    public boolean dfs(char[] c1, char[] c2, char[] c3, int i, int j, int k, boolean[][] invalid) {
        if (invalid[i][j]) {
            return false;
        }
        if (k == c3.length) {
            return true;
        }
        boolean valid = i < c1.length && c1[i] == c3[k] && dfs(c1, c2, c3, i + 1, j, k + 1, invalid)
                || j < c2.length && c2[j] == c3[k] && dfs(c1, c2, c3, i, j + 1, k + 1, invalid);
        if (!valid){
            invalid[i][j] = true;
        }
        return valid;
    }
}
// @lc code=end
