public class App {
    public static void main(String[] args) throws Exception {
        // System.out.println("Hello, World!");
        int[] arr = new int[3];
        int result = Solve(arr);
        System.out.println(result);
    }

    private static int Solve(int[] arr) {
        int result =0;
        for (int i = 0; i < arr.length; i++) {            
            for (int k = i; k < arr.length; k++) {
                int res = oneSolve(arr,i,k);
                result=+res;
            }
        }
        return result;
    }

    //arr[] i k 的数
    private static int oneSolve(int[] arr, int i,int k) {
        int oneSum = 0,temp =Integer.MAX_VALUE;
        for (int j = i; j <=k ; j++) {
            if (arr[j]<temp) {
                temp=arr[j];
            }
            oneSum+=arr[j];
        }
        return oneSum*temp;
    }
}
