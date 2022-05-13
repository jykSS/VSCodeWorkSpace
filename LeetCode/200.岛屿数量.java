/*
 * @lc app=leetcode.cn id=200 lang=java
 *
 * [200] 岛屿数量
 */

// @lc code=start
class Solution {
    public int numIslands(char[][] grid) {
        int res = 0;
        int x = grid.length;
        if (x == 0) {
            return 0;
        }
        int y = grid[0].length;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (grid[i][j] == 1) {
                    res++;
                    infect(grid, i, j, x, y);
                }
            }
        }
        return res;
    }
   private void infect(char[][] grid,int i , int j , int x , int y){
       if (i<0||i>x||j<0||j>y||grid[i][j]!=1) {
           return;
       }
       grid[i][j]=2;
       infect(grid, i+1, j, x, y);
       infect(grid, i-1, j, x, y);
       infect(grid, i, j+1, x, y);
       infect(grid, i, j-1, x, y);
   }
}
// @lc code=end
