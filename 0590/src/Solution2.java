import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author YonghShan
 * @date 3/28/21 - 11:33
 */
public class Solution2 {
    // Iteration: 参考binary tree postorder traversal
    /* Runtime: 2ms     O(n)
       Memory: 39.8MB   O(n)
     */
    public List<Integer> postorder(Node root) {
        LinkedList<Integer> res = new LinkedList<>();  // 这里最开始就要写LinkedList<Integer> res，不能只写List<Integer> res
        if (root == null) return res;

        Deque<Node> stack = new ArrayDeque<>();
        stack.addFirst(root);
        while (!stack.isEmpty()) {
            Node tmp = stack.pop();
            res.addFirst(tmp.val);
            for (Node child : tmp.children) {
                if (child != null) stack.addFirst(child); // ArrayDeque实现的deque不能放null，LinkedList实现的deque可以
            }
        }
        return res;
    }
}
