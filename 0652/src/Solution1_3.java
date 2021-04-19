import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author YonghShan
 * @date 4/11/21 - 16:15
 */
public class Solution1_3 {
    // Solution1 with Memoization
    /* Runtime: 14ms (faster than 78.15%)   O(n^2): serialization就需要O(n),再对每个Node都进行serialization => O(n^2)
       Memory: 50.2MB (less than 19.09%)    O(n^2): serialization就需要O(n)   ?
     */
    private HashMap<String, Integer> map = new HashMap<>();
    private List<TreeNode> list = new ArrayList<>();
    private HashMap<TreeNode, String> dict = new HashMap<>();  // Memoization

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        String s = serialize(root);
        map.put(s, map.getOrDefault(s, 0)+1);
        if (map.get(s) == 2) list.add(root);
        if (root.left != null) findDuplicateSubtrees(root.left);
        if (root.right != null) findDuplicateSubtrees(root.right);
        return list;
    }

    public String serialize(TreeNode root) {
        if (dict.containsKey(root)) return dict.get(root);
        if (root == null) return "#,";
        StringBuilder sb = new StringBuilder();
        sb.append(root.val);
        sb.append(",");
        sb.append(serialize(root.left));
        sb.append(serialize(root.right));
        dict.put(root, sb.toString());
        return sb.toString();
    }
}
