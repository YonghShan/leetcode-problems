/**
 * @author YonghShan
 * @date 2/19/21 - 00:05
 */

class Solution1 {
    // 太冗余了，不用看了，看Solution2
    /* Runtime: 4ms
       Memory: 39.1MB
     */
    public Node connect(Node root) {
        if (root == null) return root;
        if (root.left == null && root.right == null) root.next = null;

        // root.left 或 root.right 不为null 或 root.left 和 root.right 都不为null：
        if (root.left != null) {
            if (root.left.left != null) {
                root.left = connect(root.left);
            } else {
                root.left.next = (root.right != null) ? root.right : null;
            }
            if (root.left.right != null) {
                root.left = connect(root.left);
                root.left.right.next = (root.right.left != null) ? root.right.left : null;
            }
            root.left.next = (root.right != null) ? root.right : null;
        }

        if (root.right != null) {
            if (root.right.left != null || root.right.right != null) {
                root.right = connect(root.right);
            } else {
                root.right.next = null;
            }
        }

        Node p = root.left;
        while (p != null && p.right != null && p.next.left != null) {
            p.right.next = p.next.left;
            p = p.right;
        }

        return root;
    }
}