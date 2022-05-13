import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        int[] arry = { 4, 6 };
        int num1[] = { 0 }, num2[] = { 0 };
        FindNumsAppearOnce(arry, num1, num2);
        System.out.println("Hello, World!");
    }

    public static void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        Map<Integer, Boolean> arryMap = new HashMap();
        for (int i = 0; i < array.length; i++) {
            if (arryMap.containsKey(array[i])) {
                arryMap.remove(array[i]);
            } else {
                arryMap.put(array[i], true);
            }
        }
        List<Integer> List = new ArrayList<>();
        for (int key : arryMap.keySet()) {
            List.add(key);
        }
        num1[0] = List.get(0);
        num2[0] = List.get(1);
    }
}
