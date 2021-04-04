public class App {
    public static void main(String[] args) throws Exception {
        int [] arr={1,2,3,4,5};
        int n=arr.length;
        for (int i = n-1; i >0; i--) {
            swap(arr[i],arr[rand()%(i+1)]);
        }
        System.out.println("Hello, World!");
    }
}
