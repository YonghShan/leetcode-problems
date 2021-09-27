import java.util.*;

/**
 * @author YonghShan
 * @date 2/15/21 - 01:14
 */

class Solution4 {
    // Iteration3： 更简洁
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.addFirst(root);
        while (!stack.isEmpty()) {
            TreeNode tmp = stack.poll();
            res.add(tmp.val);
            if (tmp.right != null) stack.addFirst(tmp.right);
            if (tmp.left != null) stack.addFirst(tmp.left);
        }
        return res;
    }
}