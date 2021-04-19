import java.util.LinkedList;
import java.util.List;

/**
 * @author YonghShan
 * @date 2/15/21 - 01:14
 */

class Solution4 {
    // Iteration3： 更简洁
    public List<Integer> preorderTraversal(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>(); // stack也可以改成Deque
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast(); // stack如果是Deque，则这里用pop()
            output.add(node.val);
            if (node.right != null) {
                stack.add(node.right);
            }
            if (node.left != null) {
                stack.add(node.left);
            }
        }
        return output;
    }
}