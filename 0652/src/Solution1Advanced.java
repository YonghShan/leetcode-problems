import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YonghShan
 * @date 4/11/21 - 15:49
 */
public class Solution1Advanced {
    // 和Solution1/Solution1_2都是一样的思路，但是代码更concise
    // Recursion "Bottom-up"
    /* Runtime: 20ms (faster than 54.97%)   O(n): 我觉得这种implementation是O(n) (将两个iterations(遍历tree和为遍历到的node序列化)合并为一个)，官方是O(n^2)
       Memory: 44MB (less than 58.22%)      O(n^2): serialization就需要O(n)   ?
     */
    Map<String, Integer> count;
    List<TreeNode> ans;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        count = new HashMap();
        ans = new ArrayList();
        collect(root);
        return ans;
    }

    public String collect(TreeNode node) {
        if (node == null) return "#";
        String serial = node.val + "," + collect(node.left) + "," + collect(node.right);
        count.put(serial, count.getOrDefault(serial, 0) + 1);
        if (count.get(serial) == 2)
            ans.add(node);
        return serial;
    }
}
