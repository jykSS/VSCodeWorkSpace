import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode.cn id=39 lang=java
 *
 * [39] 组合总和
 */

// @lc code=start
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, target, result, 0, new ArrayList<>());
        return result;
    }

    private void dfs(int[] candidates, int target, List<List<Integer>> result, int j, List<Integer> arrayList) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            result.add(new ArrayList<>(arrayList));
            return;
        }
        for (int i = j; i < candidates.length; i++) {
            if (target < 0) {
                break;
            }
            arrayList.add(candidates[i]);
            dfs(candidates, target - candidates[i], result, i, arrayList);
            arrayList.remove(arrayList.size() - 1);
        }
    }
}
// @lc code=end
