import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author YonghShan
 * @date 3/2/21 - 16:30
 */
class Solution4 {
    // 官方的解释是Greedy BFS，但我觉得应该是BFS on a complete m-ary tree
    // m是小于numSquares(int n)的输入参数n的perfect squares的个数，e.g. n=35, perfectSquares=[1,4,9,16,25], m=5(i.e. m=(int)sqrt(n))
    /* Runtime: 81ms   O(n^(2/h)) = O(m^h) where h is the height of the N-ary tree. in worse case, 要把这个完整m(sqrt(n))叉树的所有节点都遍历一遍
       Memory: 41.8MB   O(n^(2/h)) = O(m^h): which is also the maximal number of nodes that can appear at the level h
     */
    public int numSquares(int n) {
        List<Integer> perfectSquares = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) perfectSquares.add(i * i);

        Set<Integer> current_level = new HashSet<>();
        current_level.add(n);
        int level = 0;

        while (!current_level.isEmpty()) {
            level++;
            Set<Integer> next_level = new HashSet<>();

            for (Integer remainder : current_level) {
                for (Integer perfcetSquare : perfectSquares) {
                    if (remainder.equals(perfcetSquare)) return level;
                    next_level.add(remainder - perfcetSquare);
                }
            }
            current_level = next_level;
        }

        return level;
    }
}
