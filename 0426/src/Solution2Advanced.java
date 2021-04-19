/**
 * @author YonghShan
 * @date 3/19/21 - 22:14
 */
public class Solution2Advanced {
    // Solution2 Abbreviation
    /* Runtime: 0ms （faster than 100%)     O(n) since each node is processed exactly once.
       Memory: 37.9MB (less than 99.00%)    O(n) to keep a recursion stack of the size of the tree height,
                                            which is O(logN) for the best case of completely balanced tree
                                            and O(N) for the worst case of completely unbalanced tree.
     */
    public Node treeToDoublyList(Node root) {
        if (root == null) return root;
        if(root.left == null && root.right == null) {     // Case 1: root.left == null && root.right == null
            root.left = root;
            root.right = root;
            return root;
        }
        Node leftRoot = treeToDoublyList(root.left);
        Node rightRoot = treeToDoublyList(root.right);

        if (leftRoot == null) {                           // Case 4: root.left == null && root.right != null
            rightRoot.left.right = root;
            root.left = rightRoot.left;
            root.right = rightRoot;
            rightRoot.left = root;
            return root;
        } else {
            leftRoot.left.right = root;
            root.left = leftRoot.left;
            if (rightRoot == null) {                      // Case 3: root.left != null && root.right == null
                leftRoot.left = root;
                root.right = leftRoot;
            } else {                                      // Case 2: root.left != null && root.right != null
                rightRoot.left.right = leftRoot;
                leftRoot.left = rightRoot.left;
                root.right = rightRoot;
                rightRoot.left = root;
            }
        }

        return leftRoot;
    }
}
