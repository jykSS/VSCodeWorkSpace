/*
 * @lc app=leetcode.cn id=5 lang=java
 *
 * [5] 最长回文子串
 */

// @lc code=start
class Solution {
    private int lo ,maxlen;
    public String longestPalindrome(String s) {
        int length =s.toCharArray().length;
        if (length<2) {
            return s;
        }
        for (int i = 0; i < length-1; i++) {
            findPalindrome(s,i,i);
            findPalindrome(s,i,i+1);
        }
        return s.substring(lo, lo+maxlen);
    }
    void findPalindrome(String s,int j,int k){
        while (j>=0&&k<s.length()&&s.charAt(j)==s.charAt(k)) {
            j--;
            k++;
        } 
        if (maxlen<k-j-1) {
            maxlen=k-j-1;
            lo=j+1;
        }
    }
}
// @lc code=end

