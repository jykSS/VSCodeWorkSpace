import java.util.Arrays;
import java.util.Objects;

public class App {
    public static void main(String[] args) throws Exception {
    //    Integer i1=200;
    //    Integer i2=200;
    //    if (i1==i2) {
    //        System.out.println("............asdasdadas");
    //    }
    //    Integer i3=100;
    //    Integer i4=100;
    //    if (i3==i4) {
    //        System.out.println("asdasdadasadasdasdasdasdasd");
    //    }
    //    String s="%%djasiojdioas";
    //    int a = s.indexOf("%");
    //    System.out.println(a);
    // Integer X =null;
    // System.out.println(Objects.isNull(X));
    int[] nums = {10,9,2,5,3,7,101,3};
    int lengthOfLIS = lengthOfLIS(nums);
    System.out.println(lengthOfLIS);
    }
    public static int lengthOfLIS(int[] nums) {
        int[] res = new int[nums.length];
        int len = 0;
        for (int num: nums) {
            int idx = Arrays.binarySearch(res, 0, len, num);
            idx = idx < 0? -idx - 1: idx;
            res[idx] = num;
            if (idx == len) {
                len++;
            }
        }
        return len;
    }
}
