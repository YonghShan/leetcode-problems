import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

/**
 * @author YonghShan
 * @date 2/20/21 - 01:01
 */

class Solution1 {
    // Algorithm: 首先判断p和q是否在同一个subtree中，如果不在，则判断p.parent和q是否在一个树中
    /* Runtime： 4ms (faster than 100%)
       Memory: 39.8MB (less than 96.16%)
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return root;

        if (isInTheTree(p, q)) {
            return p;
        } else {
            TreeNode parent = findParent(root, p);
            while (!isInTheTree(parent, q)) parent = findParent(root, parent);
            return parent;
        }
    }

    public TreeNode findParent(TreeNode root, TreeNode node) {
        if (root == null) return null;
        if (root == node || root.left == node || root.right == node) return root;
        if (findParent(root.left, node) != null) return findParent(root.left, node);
        return findParent(root.right, node);
    }

    public boolean isInTheTree(TreeNode p, TreeNode q) {
        if (p == null) return false;
        if (p == q) return true;
        return isInTheTree(p.left, q) || isInTheTree(p.right, q);
    }
}