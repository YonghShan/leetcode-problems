import java.util.*;

/**
 * @author YonghShan
 * @date 2/20/21 - 16:16
 */
class Solution2 {
    // Iteration with pointer parent:
    // 首先，从root向下遍历，并且将tree中每个node和其parent作为一对放入HashSet parent中；
    // 然后，利用HashSet parent中的内容，得到p的ancestors；
    // 最后，判断q是否出现在p的ancestors中，如果没有，则q置为q的parent，继续判断
    /* Runtime：10 ms (faster than 20.30%)  O(n)
       Memory：39.6 MB (less than 97.97%)   O(n)
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // PART I: Get the HashSet parent while traversing the tree
        // Stack for tree traversal
        Deque<TreeNode> stack = new ArrayDeque<>();
        // HashMap for parent pointers
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        parent.put(root, null);
        stack.push(root);

        // Iterate until we find both the nodes p and q
        while (!parent.containsKey(p) || !parent.containsKey(q)) {
            TreeNode node = stack.pop();
            // While traversing the tree, keep saving the parent pointers.
            if (node.left != null) {
                parent.put(node.left, node);
                stack.push(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                stack.push(node.right);
            }
        }

        // PART II: Get the ancestors for treenode p
        // Ancestors set() for node p.
        Set<TreeNode> ancestors = new HashSet<>();
        // Process all ancestors for node p using parent pointers.
        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }

        // PART III: Find the LCA
        // The first ancestor of q which appears in p's ancestor set() is their lowest common ancestor.
        while (!ancestors.contains(q))
            q = parent.get(q);
        return q;
    }
}