class bubbleSort {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 5, 5, 7 };
        bubbleSort(arr);
        System.out.println(arr);
    }

    public static int[] bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j + 1] < arr[j]) {
                    flag = false;
                    arr[j] = arr[j] ^ arr[j + 1];
                    arr[j + 1] = arr[j] ^ arr[j + 1];
                    arr[j] = arr[j] ^ arr[j + 1];
                }
            }
            // 一趟下来是否发生位置交换
            if (flag)
                break;
        }
        return arr;
    }

}