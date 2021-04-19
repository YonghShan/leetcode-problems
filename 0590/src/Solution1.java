import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 3/28/21 - 11:29
 */
public class Solution1 {
    // Recursion
    /* Runtime: 0ms     O(n)
       Memory: 39.6MB   O(n)
     */
    private List<Integer> res = new ArrayList<>();
    public List<Integer> postorder(Node root) {
        if (root == null) return res;

        for (Node child : root.children) {
            postorder(child);   // 不能写成res.addAll(postorder(child)), 会导致res中添加重复的元素
            // res.addAll(postorder(5))   => res: 5
            // res.addAll(postorder(6))   => res: 5 + (5, 6)   // postorder(6)结束后res为5，6
        }
        res.add(root.val);

        return res;
    }
}
