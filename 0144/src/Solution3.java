import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author YonghShan
 * @date 2/14/21 - 01:09
 */

class Solution3 {
    // Iteration2
    /* Runtime: 0ms     O(n) (每个node都严格只找了一遍)
       Memory: 37.7MB   <O(n)
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode p = root;
        while (p != null) {
            list.add(p.val);
            if (p.left != null && p.right != null) {
                stack.addFirst(p); // 这里不能直接addFirst(p.right)，会报错
                p = p.left;
            } else if (p.left != null && p.right == null){
                p = p.left;
            } else {
                p = p.right;
            }
            // 上面else if...else可以合并为：p = (p.left != null) ? p.left : p.right;
            // 这里不存在p.right == null时，p被push进stack的情况，即stack中的node的right绝对不为null
            if (p == null && !stack.isEmpty()) p = stack.pop().right;
        }

        return list;
    }
}
