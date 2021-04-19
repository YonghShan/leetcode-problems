import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YonghShan
 * @date 4/11/21 - 16:50
 */
public class Solution2 {
    // 对于每个node，(node.val, left, right) (作为node的unique identifier)可以唯一地确定它，所以如果有两个node的uid相同，则存在duplicate subtree
    // 我觉得和Solution1的思路一样，一个是用serialized String作为uid，一个是用triple (node.val, left, right)作为uid
    // 唯一的区别就是serialized String是依赖于Tree中nodes的数量，而triple (node.val, left, right)是固定长度的
    /* Runtime: 12ms (faster than 91.38%)    O(n): 和Solution1Advanced一样，我觉得这种implementation是O(n) (将两个iterations(遍历tree和为遍历到的node序列化)合并为一个)，官方是O(n)
       Memory: 40.9MB (less than 88.12%)     O(n): serialization就需要O(n)   ?
     */
    int t;
    Map<String, Integer> trees;
    Map<Integer, Integer> count;
    List<TreeNode> ans;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        t = 1;
        trees = new HashMap(); // 并不真正地存入data，仅仅是提供计算uid的method
        count = new HashMap();
        ans = new ArrayList();
        lookup(root);
        return ans;
    }

    public int lookup(TreeNode node) {
        if (node == null) return 0;
        String serial = node.val + "," + lookup(node.left) + "," + lookup(node.right);
        int uid = trees.computeIfAbsent(serial, x-> t++); // 如果trees这个HashMap中没有serial作为key所对应的value（肯定没有，trees并不真存data），则依据function x-> t计算出value
        count.put(uid, count.getOrDefault(uid, 0) + 1); // 将计算得到的uid作为count这个HashMap的key
        if (count.get(uid) == 2)
            ans.add(node);
        return uid;
    }
}
