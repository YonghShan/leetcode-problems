import com.sun.source.tree.Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author YonghShan
 * @date 2/14/21 - 00:14
 */

class Solution2 {
    // Iteration1
    /* Runtime: 0ms     O(n + m) (不仅每个node找了一遍，还额外将p赋为null，m为p赋为null的次数)
       Memory: 37.3MB   <O(n)
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode p = root;
        while (p != null) {
            list.add(p.val);
            if (p.left != null) {
                stack.addFirst(p); // 这里不能直接addFirst(p.right)，会报错
                p = p.left;
            } else {
                p = p.right;
            }
            // 一定要是while：万一被pop出来的node.right也会null，if就会直接结束，导致stack中剩余的node无法处理
            while (p == null && !stack.isEmpty()) p = stack.pop().right;
        }

        return list;
    }
}