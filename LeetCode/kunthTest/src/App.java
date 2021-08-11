import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) throws Exception {
        // /^[\s\u4e00-\u9fa5a-z0-9_-]{0,}$/
        String s = "RESPIRATOR MASK (N121 - WEINI), FFP2, CARTON-1000 ORDER PREPARATION REF: N-2383-UKROPR3 SHIPMENT REF: S2104_0225 HS CODE: 630790";
        String REGEX_USERNAME = null;
    
            System.out.println(s.replaceAll(REGEX_USERNAME, "替换"));
        
    }
}
