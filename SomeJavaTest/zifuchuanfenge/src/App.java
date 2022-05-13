import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String s = in.nextLine();
            List<String> lists = new ArrayList();
            for (int i = 0; i < s.length(); i++) {
                String str = "";
                if (i + 8 < s.length()) {
                    str = s.substring(i, i + 8);
                    i = i + 7;
                } else {
                    str = s.substring(i, s.length());
                    char[] sc = str.toCharArray();
                    StringBuilder sb = new StringBuilder(str);
                    for (int j = sc.length; j < 8; j++) {
                        sb.append("0");
                    }
                    str = sb.toString();
                    i = s.length();
                }
                lists.add(str);
            }
            for (String so : lists) {
                System.out.println(so);
            }
        }
    }
}
