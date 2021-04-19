import java.util.*;

/**
 * @author YonghShan
 * @date 3/27/21 - 23:57
 */
public class Solution3 {
    // Iteration: 官方
    /* Runtime: 4ms     O(n)
       Memory: 43.4MB   O(n)
     */
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Deque<Node> stack = new ArrayDeque<>();
        stack.addFirst(root);
        while (!stack.isEmpty()) {
            Node tmp = stack.pop();
            res.add(tmp.val);
            Collections.reverse(tmp.children);
            for (Node child : tmp.children) {
                stack.addFirst(child);
            }
        }
        return res;
    }
}
