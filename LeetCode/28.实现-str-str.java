/*
 * @lc app=leetcode.cn id=28 lang=java
 *
 * [28] 实现 strStr()
 */

// @lc code=start
class Solution {
    public int strStr(String haystack, String needle) {
        // 自己敲一下子 前缀表不需要减一的实现方式
        int needleLength = needle.length();
        if (needleLength == 0)
            return 0;
        // 当needle是空字符串时，返回0

        int[] next = new int[needleLength];
        // 定义好next数组
        for (int right = 1, left = 0; right < needleLength; right++) {
            // 定义好两个指针right与left
            // 在for循环中初始化指针right为1，left=0,开始计算next数组，right始终在left指针的后面
            while (left > 0 && needle.charAt(left) != needle.charAt(right)) {
                // 如果不相等就让left指针回退，到0时就停止回退
                left = next[left - 1];// 进行回退操作；
            }
            if (needle.charAt(left) == needle.charAt(right)) {
                left++;
            }
            next[right] = left;
            // 这是从 1 开始的

        }
        // 循环结束的时候，next数组就已经计算完毕了

        for (int i = 0, j = 0; i < haystack.length(); i++) {

            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = next[j - 1];
            }
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }
            if (j == needleLength)
                return i - needleLength + 1;
        }
        return -1;

    }
}
// @lc code=end
