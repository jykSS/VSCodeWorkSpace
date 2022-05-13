public class App {
    public static void main(String[] args) throws Exception {
        String s="12/ ";
        String[] split2 = s.split("/");
        System.out.println(split2.length);
        for (String string : split2) {
            System.out.println(string);
        }
    }
}
