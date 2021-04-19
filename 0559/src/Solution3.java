import java.util.LinkedList;
import java.util.Queue;

/**
 * @author YonghShan
 * @date 3/28/21 - 16:40
 */
public class Solution3 {
    // Iteration: BFS
    /* Runtime: 1ms     O(n)
       Memory: 38.8MB   O(n)
     */
    public int maxDepth(Node root) {
        if (root == null) return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            for (int i = 0; i < size; i++) {
                Node tmp = queue.poll();
                queue.addAll(tmp.children);
            }
        }
        return depth;
    }
}
