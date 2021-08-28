import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // List<String> ss = List.of("3","33","3333","3333");
        // System.out.println(ss.toString());
        String s = "abcdaefgh";
        System.out.println(lengthOfLongestSubstring(s));
    }
    public static int lengthOfLongestSubstring(String s) {
        int[] last = new int[128];
        int n = s.length();
        int res = 0;
        int start = 0; // 窗口开始位置
        for(int i = 0; i < n; i++) {
            int index = s.charAt(i);
            start = Math.max(start, last[index]);
            res   = Math.max(res, i - start + 1);
            last[index] = i+1;
        }

        return res;
    }
}
