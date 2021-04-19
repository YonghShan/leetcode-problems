import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YonghShan
 * @date 4/11/21 - 16:23
 */
public class Solution1Advanced2 {
    // 在Solution1Advanced的基础上，换用更快的StringBuilder
    /* Runtime: 11ms (faster than 95.62%)   O(n): 我觉得这种implementation是O(n) (将两个iterations(遍历tree和为遍历到的node序列化)合并为一个)，官方是O(n^2)
       Memory: 49.9MB (less than 20.33%)    O(n^2): serialization就需要O(n)   ?   StringBuilder消耗大
       这里不能想Solution1_2那样将StringBuilder作为parameter of collect()，因为我们需要在每次recursion时都进行有关HashMap的操作
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
        StringBuilder sb = new StringBuilder();
        sb.append(node.val);
        sb.append(",");
        sb.append(collect(node.left));
        sb.append(",");
        sb.append(collect(node.right));
        String serial = sb.toString();
        count.put(serial, count.getOrDefault(serial, 0) + 1);
        if (count.get(serial) == 2)
            ans.add(node);
        return serial;
    }
}
