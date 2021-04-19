import java.util.*;

/**
 * @author YonghShan
 * @date 2/14/21 - 23:22
 */

class Solution3 {
    // Iteration：left-right-root + list.add()  ==   root-right-left + list.addFirst()
    /* Runtime: 0ms     O(n)
       Memory: 37.2MB   O(n)
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> list = new LinkedList();
        Deque<TreeNode> stack = new ArrayDeque();

        if (root == null) return list;

        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            list.addFirst(root.val);
            if (root.left != null) stack.push(root.left);
            if (root.right != null) stack.push(root.right);
        }

        return list;
    }
}
