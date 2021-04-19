import java.util.*;

/**
 * @author YonghShan
 * @date 3/12/21 - 15:32
 */
class Solution4 {
    // Iteration: BFS
    /* Runtime: 1ms       O(n)
       Memory: 38.8MB     O(log(n))
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        int depth = 0;
        queue.add(root);
        while (!queue.isEmpty()) {
            depth++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode p = queue.poll();
                if (p.left != null) queue.add(p.left);
                if (p.right != null) queue.add(p.right);
            }
        }
        return depth;
    }
}
