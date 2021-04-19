/**
 * @author YonghShan
 * @date 2/19/21 - 01:02
 */
public class Solution2 {
    // Recursion "Bottom-up": 重点是最后的while循环（如果没有它，不同subtree之间只有第一层node之间有next联系）！！！
    /* Runtime: 0ms (faster than 100%)   O(n)
       Memory: 39MB (less than 89.31%)   O(1）(assume implicit stack space does not count as extra space for this problem)
     */
    public Node connect(Node root) {
        if (root == null) return root;

        // root.left 或 root.right 不为null 或 root.left 和 root.right 都不为null：
        if (root.left != null) {
            root.left = connect(root.left);
            root.left.next = (root.right != null) ? root.right : null;
        }

        if (root.right != null) {
            root.right = connect(root.right);
            root.right.next = null;  // At first, just set the next field to right child is null
        }

        // Then, update the next field to right child only when there are 3 levels in the tree
        Node p = root.left;
        while (p != null && p.right != null && p.next.left != null) {
            p.right.next = p.next.left;
            p = p.right;
        }

        return root;
    }
}
