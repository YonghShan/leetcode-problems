import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 3/13/21 - 19:47
 */
public class Solution1 {
    // Recursion without Memoization
    /* Runtime: 1ms
       Memory: 39.4MB
     */
    public List<TreeNode> generateTrees(int n) {
        return helper(1, n);
    }

    public List<TreeNode> helper(int start, int end) {
        List<TreeNode> res = new ArrayList<>();
        if (start > end) res.add(null); // 不直接return null是为了之后leftSubtrees/rightSubtrees不为null（而是只含有null的List<TreeNode>），避免空指针报错

        for (int i = start; i <= end; i++) {
            List<TreeNode> leftSubtrees = helper(start, i-1);
            List<TreeNode> rightSubtrees = helper(i+1, end);

            for (TreeNode leftSubtree : leftSubtrees) {
                for (TreeNode rightSubtree : rightSubtrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftSubtree;
                    root.right = rightSubtree;
                    res.add(root);
                }
            }
        }
        return res;
    }
}
