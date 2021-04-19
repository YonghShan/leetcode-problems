/**
 * @author YonghShan
 * @date 3/19/21 - 18:53
 */
public class Solution2 {
    // Inorder Traversal on Binary Search Tree: DFS Recursion 可以直接看Solution2Advanced
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
        Node leftRoot = new Node(0);
        Node rightRoot = new Node(0);

        if (root.left != null) {
            leftRoot = treeToDoublyList(root.left);
            leftRoot.left.right = root;
            root.left = leftRoot.left;
            if (root.right != null) {                      // Case 2: root.left != null && root.right != null
                rightRoot = treeToDoublyList(root.right);
                rightRoot.left.right = leftRoot;
                leftRoot.left = rightRoot.left;
                root.right = rightRoot;
                rightRoot.left = root;
            } else {                                       // Case 3: root.left != null && root.right == null
                leftRoot.left = root;
                root.right = leftRoot;
            }
        } else {                                           // Case 4: root.left == null && root.right != null
            rightRoot = treeToDoublyList(root.right);
            rightRoot.left.right = root;
            root.left = rightRoot.left;
            root.right = rightRoot;
            rightRoot.left = root;
            return root;
        }

        return leftRoot;
    }
}