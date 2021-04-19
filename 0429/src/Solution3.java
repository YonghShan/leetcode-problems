import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 3/28/21 - 15:37
 */
public class Solution3 {
    // Recursion with Variable level
    /* Runtime: 0ms     O(n)
       Memory: 39.7MB   O(logn)
     */
    private List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> levelOrder(Node root) {
        if (root != null) helper(root, 0);
        return res;
    }

    public void helper(Node root, int level) {
        if (res.size() == level) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(root.val);
        for (Node child : root.children) {
            if (child != null) helper(child, level+1);
        }
    }
}
