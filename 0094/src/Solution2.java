import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author YonghShan
 * @date 2/14/21 - 16:32
 */

class Solution2 {
    // Iteration1
    /* Runtime: 0ms     O(n + m) (不仅每个node找了一遍，还额外将p赋为null，m为p赋为null的次数)
       Memory: 37.2MB   <O(n)
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode p = root;
        while (p != null) {
            if (p.left != null) {
                stack.addFirst(p);
                p = p.left;
            } else {
                list.add(p.val);
                p = p.right;
            }
            while (p == null && !stack.isEmpty()) {
                p = stack.pop();
                list.add(p.val);
                p = p.right;
            }
        }

        return list;
    }
}