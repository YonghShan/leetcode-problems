import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author YonghShan
 * @date 9/27/21 - 23:10
 */
public class Solution1 {
    public int rangeSumBST(TreeNode root, int low, int high) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.addFirst(root);
        int sum = 0;

        while (!stack.isEmpty()) {
            TreeNode tmp = stack.poll();
            if (tmp.val <= high && tmp.val >= low) sum += tmp.val;
            if (tmp.left != null) stack.addFirst(tmp.left);
            if (tmp.right != null) stack.addFirst(tmp.right);
        }

        return sum;
    }
}
