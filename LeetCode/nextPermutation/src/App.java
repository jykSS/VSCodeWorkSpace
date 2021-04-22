/**
 * “下一个排列”的定义是：给定数字序列的字典序中下一个更大的排列。如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 
 * 我们可以将该问题形式化地描述为：给定若干个数字，将其组合为一个整数。如何将这些数字重新排列，以得到下一个更大的整数。如 123 下一个更大的数为
 * 132。如果没有更大的整数，则输出最小的整数。
 * 
 * 以 1,2,3,4,5,6 为例，其排列依次为：
 * 
 * 
 * 123456 123465 123546 ... 654321 可以看到有这样的关系：123456 < 123465 < 123546 < ... <
 * 654321
 * 
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 
 * 必须 原地 修改，只允许使用额外常数空间。
 * 
 */

public class App {
    public static void main(String[] args) throws Exception {
        int[] nums = { 1, 2, 3 };
        nextPermutation(nums);
        System.out.println(nums);
    }

    public static void nextPermutation(int[] nums) {
        In
        swap(nums, 0, 1);
    }

    private static void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
}
