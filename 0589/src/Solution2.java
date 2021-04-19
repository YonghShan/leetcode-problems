import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author YonghShan
 * @date 3/27/21 - 23:39
 */
public class Solution2 {
    // Iteration
    /* Runtime: 2ms      O(n)
       Memory: 39.5MB    O(n)
     */
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Deque<Node> stack = new ArrayDeque<>();
        stack.addFirst(root);
        while (!stack.isEmpty()) {
            Node tmp = stack.pop();
            res.add(tmp.val);
            int size = tmp.children.size();
            for (int i = 0; i < size; i++) {
                Node n = tmp.children.remove(tmp.children.size()-1);
                // Node n = tmp.children.remove(size-1-i);  // runtime几乎没区别
                stack.addFirst(n);
            }
        }
        return res;
    }
}
