/**
 * 无重复字符的最长串 给定一个字符串，请你找出其 中 不含有重复字符的 最长子串 的长度。
 * 
 * 示例 1:
 * 
 * 输入: "abcabcbb" 输出: 3 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。 示例 2:
 * 
 * 输入: "bbbbb" 输出: 1 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。 示例 3:
 * 
 * 输入: "pwwkew" 输出: 3 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。 请注意，你的答案必须是 子串
 * 的长度，"pwke" 是一个子序列，不是子串。
 */

public class App {
    public static void main(String[] args) {
        String a = "abcd";
        int i = lengthOfLongestSubstring2(a);
        System.out.println(i);
    }

    public int lengthOfLongestSubstring0(String s) {
        int i = 0, j = 0, max = 0;
        Set<Character> set = new HashSet<>();
        while (j < s.length()) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                max = Math.max(max, set.size());
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return max;
    }

    public static int lengthOfLongestSubstring(String s) {
        char[] charString = s.toCharArray();
        int ant = 0;
        for (int i = 0; i < charString.length; i++) {
            boolean ifcon = true;
            Map<Character, Integer> charMap = new HashMap<>();
            for (int j = i; j < charString.length; j++) {
                if (charMap.get(charString[j]) != null) {
                    if ((j - i) > ant) {
                        ant = j - i;
                    }
                    ifcon = false;
                    break;
                } else {
                    charMap.put(charString[j], j);
                }
            }
            if (ifcon) {
                if ((charString.length - i) > ant) {
                    ant = charString.length - i;
                }
            }
        }
        return ant;
    }

    // abcd
    public static int lengthOfLongestSubstring2(String s) {
        int[] last = new int[128];
        int res = 0;
        int start = 0; // 窗口开始位置
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i);
            System.out.println("当前位置" + index);
            start = Math.max(start, last[index]);
            System.out.println("起始位置" + start);
            res = Math.max(res, i - start + 1);
            last[index] = i + 1;
            System.out.println(last[index]);
        }

        return res;

    }
}