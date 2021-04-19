import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 3/27/21 - 23:36
 */
public class Solution1 {
    // Recursion
    /* Runtime: 4ms     O(n)
       Memory: 46.3MB   O(n)
     */
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        res.add(root.val);
        for (Node child : root.children) {
            res.addAll(preorder(child));
        }

        return res;
    }
}
