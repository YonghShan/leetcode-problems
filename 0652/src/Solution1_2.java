import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author YonghShan
 * @date 4/11/21 - 15:35
 */
public class Solution1_2 {
    // 和Solution1相同，只是换了个serialize()的实现: 序列化后的String相同
    /* Runtime: 873ms (faster than 5.02%)   O(n^2): serialization就需要O(n),再对每个Node都进行serialization => O(n^2)
       Memory: 56.4MB (less than 7.53%)     O(n^2): serialization就需要O(n)   ?   StringBuilder消耗大
     */
    private HashMap<String, Integer> map = new HashMap<>();
    private List<TreeNode> list = new ArrayList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        String s = serialize(root, new StringBuilder());
        map.put(s, map.getOrDefault(s, 0)+1);
        if (map.get(s) == 2) list.add(root);
        if (root.left != null) findDuplicateSubtrees(root.left);
        if (root.right != null) findDuplicateSubtrees(root.right);
        return list;
    }

    public String serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#");
            sb.append(",");
        } else {
            sb.append(root.val);
            sb.append(",");
            serialize(root.left, sb);
            serialize(root.right, sb);
        }
        return sb.toString();
    }
}
