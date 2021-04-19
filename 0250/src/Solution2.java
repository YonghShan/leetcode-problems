/**
 * @author YonghShan
 * @date 2/17/21 - 15:02
 */

class Solution2 {
    // Recursion:
    /* Runtime：0ms (faster than 100%)
       Memory: 36.9MB (less than 95.36%)
     */
    public int countUnivalSubtrees(TreeNode root) {
        int count = 0;
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        if (root.left == null && isUnivalSubtrees(root.right) && (root.val == root.right.val)) count = 1;
        if (root.right == null && isUnivalSubtrees(root.left) && (root.val == root.left.val)) count = 1;
        if (root.left != null && root.right != null && isUnivalSubtrees(root.left) && isUnivalSubtrees(root.right)
                && (root.val == root.right.val) && (root.val == root.left.val)) count = 1;
        return count + countUnivalSubtrees(root.left) + countUnivalSubtrees(root.right);
    }

    public boolean isUnivalSubtrees(TreeNode node) {
        if (node == null) return true;
        if (node.left == null && node.right == null) return true;
        if (node.left == null) return isUnivalSubtrees(node.right) && (node.val == node.right.val);
        if (node.right == null) return isUnivalSubtrees(node.left) && (node.val == node.left.val);
        return isUnivalSubtrees(node.right) && isUnivalSubtrees(node.left) && (node.val == node.right.val)
                && (node.val == node.left.val);
    }

    // 官方isUnivalSubtree()的写法：形式上更简洁，但是因为条件分得不如上面的细，所以runtime不变，但是内存占用稍多(38.1MB)
//    public boolean isUnivalSubtrees(TreeNode node) {
//        if (node.left == null && node.right == null) return true;
//
//        boolean is_unival = true;
//        if (node.left != null) is_unival = isUnivalSubtrees(node.left) && (node.val == node.left.val);
//        // 这里因为上面一个if判断会修改is_unival，所以这里要多个判定成分，不然[1,1,1,5,1,5,1]会出错（LeetCode的测试集没有考虑到这种可能）
//        if (node.right != null) is_unival = isUnivalSubtrees(node.right) && is_unival && (node.val == node.right.val);
//        if (!is_unival) return false;
//        return true;
//    }
}