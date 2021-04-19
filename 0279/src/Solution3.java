import java.util.HashSet;
import java.util.Set;

/**
 * @author YonghShan
 * @date 3/2/21 - 14:34
 */
class Solution3 {
    // 官方的解释是Greedy Enumeration BFS，但我觉得应该是Iterative Deepening DFS on a complete m-ary tree
    // m是小于numSquares(int n)的输入参数n的perfect squares的个数，e.g. n=35, perfectSquares=[1,4,9,16,25], m=5(i.e. m=(int)sqrt(n))
    // numSquares(n) =      argmin     (is_divided_by(n,count))   使is_divided_by(n,count)为true的最小的count
    //                 count∈[1,2,...n]
    // is_divided_by(n,count) = is_divided_by(n−k,count−1) ∃ k ∈ {square numbers}
    /* Runtime: 10ms    O(n^(2/h)) = O(m^h) where h is the height of the N-ary tree.in worse case, 要把这个完整m(sqrt(n))叉树的所有节点都遍历一遍
       Memory: 38.5MB   O(sqrt(n)) = O(m): We keep a list of square_nums, which is of sqrt(n) size.
                                    In addition, we would need additional space for the recursive call stack.
                                    But as we will learn later, the size of the call track would not exceed 4.
       这里不用担心enumeration会像Solution1一样引起StackOverFlow/TLE的原因是：Solution1中recursion的次数可能会很多，一直等不到return，
       而且每次recursion就要重新计算一次perfectSquares[], 这些因素叠加导致要么Stack不够用，要么超时；
       但这里因为有count的存在，recursion最多count-1次就会返回值，然后释放Stack（而count又不会超过4），而且从始至终只计算一次perfectSquares[]
     */
    private Set<Integer> perfectSquares = new HashSet<>(); // 不用array是不好initialize长度
    public int numSquares(int n) {
        this.perfectSquares.clear();

        for (int i = 1; i * i <= n; i++) perfectSquares.add(i * i);

        int count = 1;
        for (; count <= n; count++) {
            if (is_divided_by(n, count)) return count;
        }

        return count;
    }

    public boolean is_divided_by(int n, int count) {
        if (count == 1) return perfectSquares.contains(n);

        for (Integer i : perfectSquares) {
            if (is_divided_by(n-i, count-1)) return true;
        }
        return false;
    }
}