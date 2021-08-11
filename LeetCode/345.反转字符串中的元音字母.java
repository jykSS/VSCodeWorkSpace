/*
 * @lc app=leetcode.cn id=345 lang=java
 *
 * [345] 反转字符串中的元音字母
 */

// @lc code=start
class Solution {
    public String reverseVowels(String s) {
        if (s == null || s.length() == 1) {
            return s;
        }
        char[] ss = s.toCharArray();
        int start=0;
        int end=ss.length-1;
        while(start<end){
            while(start<end&&ify(ss[start])){
                start++;
            }
            while(start<end&&ify(ss[end])){
                end--;
            }
            char temp = ss[end];
            ss[end]= ss[start];
            ss[start]=temp;
            start++;
            end--;
        }
        return new String(ss);
    }

    boolean ify(char a) {
        if (a == 'a' || a == 'o' || a == 'e' || a == 'i' || a == 'u') {
            return false;
        }
        if (a == 'A' || a == 'O' || a == 'E' || a == 'I' || a == 'U') {
            return false;
        }
        return true;
    }
}
// @lc code=end
