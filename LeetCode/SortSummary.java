import java.util.Arrays;

public class SortSummary {

    private static final String BUBBLE_SORT = "冒泡排序";
    private static final String SELECTION_SORT = "选择排序";
    private static final String INSERTION_SORT = "插入排序";
    private static final String SHELL_SORT = "希尔排序";
    private static final String QUICK_SORT = "快速排序";
    private static final String HEAP_SORT = "堆积排序";
    private static final String RADIX_SORT = "基数排序";

    public static void main(String[] args) {

        int[] numsInit = {35, 10, 42, 3, 79, 12, 62, 18, 51, 23};
        int[] nums = numsInit.clone();
        // 冒泡排序 Bubble Sort
        bubbleSort(nums);
        // 选择 Selection Sort
        nums = numsInit.clone();
        selectionSort(nums);
        // 插入排序 Insertion Sort
        nums = numsInit.clone();
        insertionSort(nums);
        // 希尔排序 Shell Sort
        nums = numsInit.clone();
        shellSort(nums);
        // 快速排序 Quick Sort
        nums = numsInit.clone();
        quickSort(nums, 0, nums.length - 1);
        // 堆积排序 Heap Sort
        nums = numsInit.clone();
        heapSort(nums);
        // 基数排序 Radix Sort
        nums = numsInit.clone();
        radixSort(nums);

    }

    private static void radixSort(int[] nums) {

    }

    private static void heapSort(int[] nums) {

    }

    /**
     * 快速排序
     * 传统思路上，选择最左边的数作为一个基数，暂存到变量tmp中
     * 然后从最右边（右边界）开始遍历，如果n[right] < tmp ，则将n[right]填入最左边（左边界）的坑，然后左边界+=1
     * 然后从左边界开始遍历，如果n[left] > tmp ， 则将n[left] 填入上一个坑中
     * 依次右左右左，最后当右边界=左边界时，将tmp放入边界中
     * 第一轮完成，开始递归进行
     *
     * @param nums
     */
    private static void quickSort(int[] nums, int left, int right) {
        if (left < right) {
            printMethod(QUICK_SORT, nums);
            int i = left;
            int j = right;
            int tmp = nums[left];
            while (i < j) {
                while (i < j && nums[j] > tmp) {
                    j--;
                }
                if (i < j) {
                    nums[i++] = nums[j];
                }
                while (i < j && nums[i] < tmp) {
                    i++;
                }
                if (i < j) {
                    nums[j--] = nums[i];
                }
            }
            nums[i] = tmp;
            printResult(left, right, nums);
            quickSort(nums, left, i - 1);
            quickSort(nums, i + 1, right);

        }


    }

    private static void shellSort(int[] nums) {

    }

    /**
     * 插入排序（从小到大）
     * 从第二个数开始，依次与左边前面每一个数做对比，
     * 如果比前一个数小，则将前一个数后移一位，最终将该数插入空位中
     *
     * @param nums
     */
    private static void insertionSort(int[] nums) {
        printMethod(INSERTION_SORT, nums);
        for (int i = 1; i < nums.length; i++) {
            int j = i - 1;
            int tmp = nums[i];
            while (j >= 0 && tmp < nums[j]) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = tmp;
            printResult(i, nums);
        }

    }

    /**
     * 选择排序（从小到大）
     * 遍历数组，找到最小的数，将其与第0位的数调换
     * index小的数会被排定，待排数组长度-1（左边界-1）
     *
     * @param nums
     */
    private static void selectionSort(int[] nums) {
        printMethod(SELECTION_SORT, nums);
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                }
            }
            printResult(i, nums);
        }
    }

    /**
     * 冒泡排序（从小到大）
     * 从第一个数开始，依次与第二三四...个数进行比较，如果比后者大，则交换顺序
     * 最终index最大的数会被排定，则待排数组长度-1（右边界-1）
     * 下一循环同样从第一个数开始
     *
     * @param nums
     */
    private static void bubbleSort(int[] nums) {
        printMethod(BUBBLE_SORT, nums);
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tmp;
                }
            }
            printResult(i, nums);
        }
    }

    private static void printResult(int n, int[] nums) {
        System.out.println("第" + n + "次排序结果： " + Arrays.toString(nums));
    }

    private static void printMethod(String method, int[] nums) {
        System.out.println(method + Arrays.toString(nums));
    }

    private static void printResult(int left, int right, int[] nums) {
        System.out.println("左边界" + left + "和右边界" + right + "的排序结果为" + Arrays.toString(nums));
    }
}