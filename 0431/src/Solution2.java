import java.util.*;

/**
 * @author YonghShan
 * @date 3/31/21 - 00:12
 */
public class Solution2 {
    // Same algorithm as Solution 1 but implemented with Recursion
    /* Runtime: 1ms （faster than 100.00%)   O(n)
       Memory: 40.1MB (less than 98.19%)     O(d), where d is the depth of the N-ary tree.
                                             Since d is proportional to n in the worst case,
                                             we could further generalize the time complexity to O(n).
       Unlike the BFS algorithm, we don't use the queue data structure in the DFS algorithm.
       However, implicitly the algorithm would consume more space in the function call stack due to the recursive function calls.
       And this consumption of call stack space is the main space complexity for our DFS algorithm.
       As we can see, the size of the call stack at any moment is exactly the number of level where the currently visited node resides,
       e.g. for the root node (level 0), the recursive call stack is empty.
     */
    // Encodes an n-ary tree to a binary tree.
    public TreeNode encode(Node root) {
        if (root == null) return null;

        TreeNode newTreeNodeRoot = new TreeNode(root.val);
        // Encode the first child of n-ary node to the left node of binary tree.
        if (root.children.size() > 0) {
            newTreeNodeRoot.left = encode(root.children.get(0));
        }
        // Encoding the rest of the sibling nodes.
        TreeNode p = newTreeNodeRoot.left;
        for (int i = 1; i < root.children.size(); i++) {
            p.right = encode(root.children.get(i));
            p = p.right;
        }

        return newTreeNodeRoot;
    }

    // Decodes your binary tree to an n-ary tree.
    public Node decode(TreeNode root) {
        if (root == null) return null;

        Node newNodeRoot = new Node(root.val, new ArrayList<>());

        // Decoding all the children nodes
        TreeNode sibling = root.left;
        while (sibling != null) {
            newNodeRoot.children.add(decode(sibling));
            sibling = sibling.right;
        }

        return newNodeRoot;
    }
}
