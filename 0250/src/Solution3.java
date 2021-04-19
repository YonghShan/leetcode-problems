/**
 * @author YonghShan
 * @date 2/17/21 - 21:14
 */
public class Solution3 {
    // Solution 2中两个functions的内容非常接近，试着合并（isUnivalSubtree()只能选择官方的写法）
    /* Runtime：0ms (faster than 100%)       O(n)
       Memory: 38.2MB (less than 54.39%)     O(h), where h is the height of tree
     */
    int count = 0;
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) return 0;
        isUnivalSubtrees(root);
        return count;
    }

    public boolean isUnivalSubtrees(TreeNode node) {
        if (node.left == null && node.right == null) {
            count++;
            return true;
        }

        boolean is_unival = true;
        if (node.left != null) is_unival = isUnivalSubtrees(node.left) && (node.val == node.left.val);
        if (node.right != null) is_unival = isUnivalSubtrees(node.right) && is_unival && (node.val == node.right.val);
        if (!is_unival) return false;
        count++;
        return true;
    }
}
