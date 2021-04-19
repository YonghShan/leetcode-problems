/**
 * @author YonghShan
 * @date 2/19/21 - 16:29
 */
public class Solution1 {
    // Recursion "Bottom-up": 不能过所有的testcase!!!
    /* Runtime:
       Memory:
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
        // 下面这个对于next的更新写得不全面，应该还需要一个pointer p3来对应Solution3中的pointer prev
        Node p1 = (root.left != null) ? root.left : root.right;
        while (p1 != null && (p1.right != null || p1.left != null)) {
            Node p2 = p1;
            while (p2.next != null) {
                p2 = p2.next;
                if (p2.left != null || p2.right != null) break;
            }
            if (p1 == p2) break; // 如果p1==p2，会导致p1的child的next设为自己，导致之后的死循环
            if (p1.right == null) { // p1仅有left一个孩子，p1只能去更新left的next，并且传至left
                p1.left.next = (p2.left != null) ? p2.left : p2.right;
                p1 = p1.left;
            } else { // p1可能只有一个right，此时p1更新right的next并传至right；也有可能p1既有left也有right，此时依旧更新right，但传至left
                p1.right.next = (p2.left != null) ? p2.left : p2.right;
                p1 = (p1.left != null) ? p1.left : p1.right;
            }
            while (p2.next != null && (p2.left != null || p2.right != null) && (p2.next.left != null || p2.next.right != null)) {
                if (p2.right == null) {
                    p2.left.next = (p2.next.left != null) ? p2.next.left : p2.next.right;
                } else {
                    p2.right.next = (p2.next.left != null) ? p2.next.left : p2.next.right;
                }
                p2 = p2.next;
            }
        }

        return root;
    }
}
