import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        for (int i = 1; i < 101; i++) {
            String locationid = String.format("%03d",i);
            System.out.println("update t_whs_location l set l.locationid='GJJGLS00"+locationid+"'  where l.warehouseid='GJJGLS' and l.locationid='GJJGLS0"+locationid+"';");
            System.out.println("update t_whs_goodsinventory g set g.locationid='GJJGLS00"+locationid+"'  where g.warehouseid='GJJGLS' and g.locationid='GJJGLS0"+locationid+"';");
        }
        // letterCombinations(s);
    }
    public static Map<Integer,String> initMap (){
        Map<Integer,String> initMap = new HashMap<>();
        initMap.put(2, "abc");
        initMap.put(3, "def");
        initMap.put(4, "ghi");
        initMap.put(5, "jkl");
        initMap.put(6, "mno");
        initMap.put(7, "pqrs");
        initMap.put(8, "tuv");
        initMap.put(9, "wxyz");
        return initMap;
    }
    public static List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        Map<Integer, String> initMap = initMap();
        char[] charArray = digits.toCharArray();
        List<Integer> cIntegers=new ArrayList<>();
        for (int i = 0; i < charArray.length; i++) {
            cIntegers.add(charArray[i]-'0');
        }
        if (cIntegers.isEmpty()) {
            return list;
        }
        for (int i = 0; i < cIntegers.size(); i++) {
           list=spand(list, cIntegers.get(i), initMap); 
        }
        return list;
    }
    private static List<String> spand(List<String> result, Integer cInteger, Map<Integer, String> initMap) {
        String strings = initMap.get(cInteger);
        char[] charArray = strings.toCharArray();
        if (result.isEmpty()) {
            for (int i = 0; i < charArray.length; i++) {
                StringBuilder sb = new StringBuilder("");
                sb.append(charArray[i]);
                result.add(sb.toString());
            }
        } else {
            List<String> list = new ArrayList<>();
            for (int j = 0; j < result.size(); j++) {
                for (int i = 0; i < charArray.length; i++) {
                    StringBuilder sb = new StringBuilder(result.get(j));
                    sb.append(charArray[i]);
                    list.add(sb.toString());
                }
            }
            result= list;
        }

        return result;
    }
}
