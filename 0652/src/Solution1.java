import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author YonghShan
 * @date 4/11/21 - 15:01
 */
public class Solution1 {
    // Solution1/Solution1_2比Solution1Advanced慢的原因：
    //     Solution1中得到parent的serial String时就已经得到children的serial String了，但是并没有存入HashMap，
    //     导致之后还要重新取children的serial String，重复操作过多，可以用memoization（见Solution1_3）
    // 序列化各个Subtree，将得到的String作为HashMap的key，记录每种Subtree出现的次数
    /* Runtime: 597ms (faster than 5.07%)   O(n^2): serialization就需要O(n),再对每个Node都进行serialization => O(n^2)
       Memory: 46MB (less than 35.05%)      O(n^2): serialization就需要O(n)   ?
     */
    private HashMap<String, Integer> map = new HashMap<>();
    private List<TreeNode> list = new ArrayList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        String s = serialize(root);
        map.put(s, map.getOrDefault(s, 0)+1);
        if (map.get(s) == 2) list.add(root);
        if (root.left != null) findDuplicateSubtrees(root.left);
        if (root.right != null) findDuplicateSubtrees(root.right);
        return list;
    }

    public String serialize(TreeNode root) {
        if (root == null) return "#,";
        StringBuilder sb = new StringBuilder();
        sb.append(root.val);
        sb.append(",");
        sb.append(serialize(root.left));
        sb.append(serialize(root.right));
        return sb.toString();
    }
}
