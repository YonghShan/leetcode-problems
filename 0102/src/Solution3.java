import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 2/15/21 - 21:14
 */

class Solution3 {
    // Recursion with the Attribute level: 当输出list levels的size等于当前node的level时，在levels中加入一个新的level
    // "Top-down": 从最上层一层层向下填充
    /* Runtime: 0ms    O(n)
       Memory: 39.4MB  O(n)
     */
    List<List<Integer>> levels = new ArrayList<List<Integer>>();

    public void helper(TreeNode node, int level) {
        // start the current level
        if (levels.size() == level)
            levels.add(new ArrayList<Integer>());

        // fulfil the current level
        levels.get(level).add(node.val);

        // process child nodes for the next level
        if (node.left != null)
            helper(node.left, level + 1);
        if (node.right != null)
            helper(node.right, level + 1);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return levels;
        helper(root, 0);
        return levels;
    }
}
