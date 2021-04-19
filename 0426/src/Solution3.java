/**
 * @author YonghShan
 * @date 3/19/21 - 22:04
 */
class Solution {
    // Inorder Recursion: 官方
    // the smallest (first) and the largest (last) nodes
    Node first = null;
    Node last = null;

    public void helper(Node node) {
        if (node != null) {
            // left
            helper(node.left);
            // node
            if (last != null) {
                // link the previous node (last)
                // with the current one (node)
                last.right = node;
                node.left = last;
            }
            else {
                // keep the smallest node
                // to close DLL later on
                first = node;
            }
            last = node; // last通过这条语句不断向后变大，而first一直保持为leftmost
            // right
            helper(node.right);
        }
    }

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;

        helper(root);
        // close Doubly Linked List
        last.right = first;
        first.left = last;
        return first;
    }
}
