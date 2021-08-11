import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class App {
    public static void main(String[] args) throws Exception {
        int arr[] = {};
        int count = subarrayBitwiseORs(arr);
        System.out.println(count);
    }

    public static int subarrayBitwiseORs(int[] arr) {
        int j = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            int temp = 0;
            for (int k = j; k < arr.length; k++) {
                temp = temp | arr[k];
                set.add(temp);
            }
            j++;
        }
        return set.size();
    }

    public int subarrayBitwiseORs1(int arr[]) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
            for (int j = i - 1; j >= 0; j--) {
                if ((arr[i] | arr[j]) == arr[j]) {
                    break;
                }
                arr[j] |= arr[i];
                set.add(arr[j]);
            }
        }
        return set.size();
    }

}
