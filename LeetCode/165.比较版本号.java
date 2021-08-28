/*
 * @lc app=leetcode.cn id=165 lang=java
 *
 * [165] 比较版本号
 */

// @lc code=start
class Solution {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int minLength = Math.min(v1.length, v2.length);
        for (int i = 0; i < minLength; i++) {
            if (Integer.parseInt(v1[i]) == Integer.parseInt(v2[i])) {
                continue;
            } else if (Integer.parseInt(v1[i]) < Integer.parseInt(v2[i])) {
                return -1;
            } else {
                return 1;
            }
        }
        if (v1.length == v2.length) {
            return 0;
        }
        StringBuilder temp = new StringBuilder("");
        if (v1.length > minLength) {
            for (int i = minLength; i < v1.length; i++) {
                temp.append(v1[i]);
            }
            String temp1 = temp.toString().replaceAll("\\.", "");
            if (Integer.parseInt(temp1) > 0) {
                return 1;
            }
        } else {
            for (int i = minLength; i < v2.length; i++) {
                temp.append(v2[i]);
            }
            String temp1 = temp.toString().replaceAll("\\.", "");
            if (Integer.parseInt(temp1) > 0) {
                return -1;
            }
        }
        return 0;
    }
}
// @lc code=end
