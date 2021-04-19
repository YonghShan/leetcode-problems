import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author YonghShan
 * @date 3/19/21 - 23:22
 */
public class Solution1 {
    // Backtracking: [0077] Combinations Solution2即官方答案的思路:
    //    1. 遇到要求的结果里有很多子集合的，将子集合作为backtrack()的parameters;
    //    2. 采用LinkedList是因为ArrayList不支持removeLast();
    //    3. 在得到一种排列可能curr时，在将curr加入res时一定不可以直接res.add(curr), 之后curr的变化会导致res中的值也发生相同改变
    /* Runtime: 3ms (faster than 22.63%)    n = nums.length
                分析：最后需要的curr有n个位，每位能取的数字从n，n-1，n-2，...，1，即curr有n!种可能，每次取到合格的curr再加入res时，要创建一个新的curr花费O(n)  ==> n!个curr共花费n*n!
                官方分析：当first=1时，有n = P(n, 1)种选择，即会执行n次操作;
                        当first=2时，有n(n-1)= P(n, 2)种选择，即会执行n(n-1)次操作;
                          ...
                        当first=n-1时，有n(n-1)...2 = P(n, n-1)种选择，即会执行n(n-1)...2次操作;
                        当first=n时，有n(n-1)...2*1 = P(n, n) = n!种选择，即会执行n!次操作.
                        累加起来：\sum_{first = 1}^n P(n, first) <= n * n!
       Memory: 39.1MB (less than 83.05%)    O(P(n, n)) = O(n!) since n-permutations-of-n has to keep P(n, n)/n! solutions.
     */
    private List<List<Integer>> res = new LinkedList<>();
    private int[] nums;
    public List<List<Integer>> permute(int[] nums) {
        this.nums = nums;
        // 下面的if判断可以不要，只是为了在nums.length为1时，提前结束
        if (nums.length == 1) {
            res.add(new LinkedList<>(Collections.singleton(nums[0]))); // Returns an immutable set containing only the specified object.
            return res;
        }
        backtrack(1, new LinkedList<>());
        return res;
    }

    public void backtrack(int first, LinkedList<Integer> curr) {
        if (curr.size() == nums.length) {
            res.add(new LinkedList<>(curr));
        }
        for (int i = 0; i < nums.length; i++) {
            if (!curr.contains(nums[i])) {
                curr.add(nums[i]);
                backtrack(first+1, curr);
                curr.removeLast();
            }
        }
    }
}
