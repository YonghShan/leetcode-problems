import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 3/28/21 - 00:03
 */
public class Solution1Advanced {
    // Recursion
    /* Runtime: 1ms     O(n)
       Memory: 39.5MB   O(n)
     */
    private List<Integer> res = new ArrayList<>();
    public List<Integer> preorder(Node root) {
        if (root == null) return res;

        res.add(root.val);
        for (Node child : root.children) {
            preorder(child);
        }

        return res;
    }
}
