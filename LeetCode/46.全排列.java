import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=46 lang=java
 *
 * [46] 全排列
 */

// @lc code=start
class Solution {
    private List<List<Integer>> result = new ArrayList<List<Integer>>();
    private List<Integer> list = new ArrayList<Integer>();

    public List<List<Integer>> permute(int[] nums) {
        // 做标记,剪枝操作
        int[] ifSelect = new int[nums.length];
        dfs(nums, ifSelect);
        return result;
    }

    public void dfs(int[] nums, int[] ifSelect) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (ifSelect[i] == 0) {
                list.add(nums[i]);
                ifSelect[i] = 1;
                dfs(nums, ifSelect);
                // 恢复上一个dfs状态,方便继续深度遍历
                // 1->dfs 2->dfs 3-dfs
                // 1->2->dfs 1->3->dfs 2->1->dfs 2->3->dfs 3->1->dfs 3->2->dfs
                list.remove(list.size() - 1);
                ifSelect[i] = 0;
            }

        }
    }
}
// @lc code=end
