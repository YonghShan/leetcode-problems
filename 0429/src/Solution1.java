import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author YonghShan
 * @date 3/28/21 - 15:18
 */
public class Solution1 {
    // Iteration with Queue: 关注点是size
    /* Runtime: 2ms    O(n)
       Memory: 39.5MB  O(n)
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> levelRes = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node tmp = queue.poll();
                levelRes.add(tmp.val);
                for (Node child : tmp.children) {    // queue.addAll(tmp.children);
                    queue.add(child);
                }
            }
            res.add(levelRes);
        }
        return res;
    }
}
