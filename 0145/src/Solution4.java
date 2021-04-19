import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author YonghShan
 * @date 3/28/21 - 11:46
 */
class Solution {
    // Iteration: fulfill the stack following right->node->left strategy.
    /* Runtime: O(n)
       Memory: O(n)
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> output = new ArrayList();
        Deque<TreeNode> stack = new ArrayDeque();

        while (root != null || !stack.isEmpty()) {
            // push nodes: right -> node -> left
            while (root != null) {
                if (root.right != null) {
                    stack.push(root.right);
                }
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();

            // if the right subtree is not yet processed
            if (!stack.isEmpty() && root.right == stack.peek()) {
                stack.pop();
                stack.push(root);
                root = root.right;
                // if we're on the leftmost leaf
            } else {
                output.add(root.val);
                root = null;
            }
        }

        return output;
    }
}
