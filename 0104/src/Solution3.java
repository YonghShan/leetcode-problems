import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

/**
 * @author YonghShan
 * @date 2/15/21 - 23:25
 */

class Solution3 {
    // Iteration: DFS
    /* Runtime: 2ms       O(n)
       Memory: 38.9MB     O(log(n)) 因为每一层最多放一个node进入stack和map中
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;

        HashMap<TreeNode, Integer> map = new HashMap<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        int depth = 0;
        int max = 0;
        while (root != null) {
            depth++;
            max = Math.max(depth, max);
            if (root.right != null) {
                map.put(root.right, depth+1); // 记录root.right的depth, 注意要+1
                stack.addFirst(root.right);
            }
            root = root.left;

            if (root == null && !stack.isEmpty()) {
                root = stack.pop();
                depth = map.get(root)-1; // 一进while，depth要++，这里要先减去，避免重复+1
            }
        }
        return max;
    }
}