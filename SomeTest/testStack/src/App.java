import java.util.Stack;

public class App {
    public static void main(String[] args) throws Exception {
        String s = "hellow";
        int num=23123;
        char[] ss= num.toCharArray();
        System.out.println(reverseVowels(s));
    }

    public static String reverseVowels(String s) {
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
            ss[start]=ss[temp];
            start++;
            end--;
        }
        return new String(ss);
    }

    static boolean ify(char a) {
        if (a == 'a' || a == 'o' || a == 'e' || a == 'i' || a == 'u') {
            return false;
        }
        if (a == 'A' || a == 'O' || a == 'E' || a == 'I' || a == 'U') {
            return false;
        }
        return true;
    }
}
