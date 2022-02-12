import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
    //     int a =2;
    //     int b = 3;
    //    int  c = gcd(a,b);
 //      System.out.println(c);
       int[] a ={1,2,3,3,4};
    //    swap(1, 0, a);
    //    System.out.println(a);
    exchange(a);
    System.out.println(a);
    }

    public static int[] exchange(int[] nums) {
        int i = 0,j=0;
        while(i<=nums.length-1){
            if((nums[i]& 1)!= 0){
                int temp = nums[i];
                nums[i]=nums[j];
                nums[j]=temp;
                j++;
            }
            ++i;
        }
        return nums;
    }



    /**
     * 返回最大公约数
     * @param a
     * @param b
     * @return
     */
    public static int gcd(int a,int b){
        if(b==0){
            return a;
        }
        return gcd(b,a%b);
    }

/**
 * 交换函数
 */
public static void swap(int i,int j ,int[] a) {
    a[i]=a[i]^a[j];
    a[j]=a[i]^a[j];
    a[i]=a[i]^a[j];
}

    class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {
            if (root == null) {
                return null;
            }
            List<List<Integer>> levelOrder = new ArrayList<>();
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                int count = queue.size();
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    TreeNode treeNode = queue.poll();
                    list.add(treeNode.val);
                    if (treeNode.left != null) {
                        queue.add(treeNode.left);
                    }
                    if (treeNode.right != null) {
                        queue.add(treeNode.right);
                    }
                }
                levelOrder.add(list);
            }
            return levelOrder;
        }
    }
}
