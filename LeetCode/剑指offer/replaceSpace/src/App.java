/**
 * 题目描述 请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are
 * Happy.则经过替换之后的字符串为We%20Are%20Happy。 示例1 输入 复制 "We Are Happy" 返回值 复制
 * "We%20Are%20Happy"
 */

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println(replaceSpace("WeAreHappy "));
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 
     * @param s string字符串
     * @return string字符串
     */
    public static String replaceSpace(String s) {
        if (s == null || "".equals(s)) {
            return s;
        }
        char[] ss = s.toCharArray();
        StringBuilder ssr = new StringBuilder();
        for (char c : ss) {
            if (c == ' ') {
                ssr.append("%20");
            } else {
                ssr.append(c);
            }
        }
        return ssr.toString();
    }
}
