/*
 * @lc app=leetcode.cn id=357 lang=java
 *
 * [357] 计算各个位数不同的数字个数
 */

// @lc code=start
class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        switch (n)
        {
            case 0: return 1;
            case 1: return 10;
            case 2: return 91;
            case 3: return 739;
            case 4: return 5275;
            case 5: return 32491;
            case 6: return 168571;
             case 7: return 712891;
            case 8: return 2345851;
            case 9: return 5611771;
            default : return 8877691;
        }
    }
    
}
public class Solution {
	public static int countNumbersWithUniqueDigits(int n) {
		if (n > 10) {
			return countNumbersWithUniqueDigits(10);
		}
		int count = 1; // x == 0
		long max = (long) Math.pow(10, n);

		boolean[] used = new boolean[10];

		for (int i = 1; i < 10; i++) {
			used[i] = true;
			count += search(i, max, used);
			used[i] = false;
		}

		return count;
	}

	private static int search(long prev, long max, boolean[] used) {
		int count = 0;
		if (prev < max) {
			count += 1;
		} else {
			return count;
		}

		for (int i = 0; i < 10; i++) {
			if (!used[i]) {
				used[i] = true;
				long cur = 10 * prev + i;
				count += search(cur, max, used);
				used[i] = false;
			}
		}

		return count;
	}
}
// @lc code=end

