import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/**
 * @author YonghShan
 * @date 3/31/21 - 00:42
 */

class Pair<U, V> {
    public U first;
    public V second;

    public Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }
}

public class Solution1Advanced {
    // Same as Solution 1 implementing with BFS but with the self-defined class Pair
    /* Runtime: 8ms （faster than 5.88%)     O(n)
       Memory: 40.5MB (less than 81.90%)    O(L) where L is the maximum number of nodes that reside at the same level.
                                            Since L is proportional to N in the worst case, we could further generalize the time complexity to O(N).
       We use a queue data structure to do BFS traversal, i.e. visiting the nodes level by level.
       At any given moment, the queue contains nodes that are at most spread into two levels.
       As a result, assuming the maximum number of nodes at one level is L, the size of the queue would be less than 2L at any time.
       Therefore, the space complexity of both encode() and decode() functions is O(L).
     */
    // Encodes an n-ary tree to a binary tree.
    public TreeNode encode(Node root) {
        if (root == null) {
            return null;
        }
        TreeNode newRoot = new TreeNode(root.val);
        Pair<TreeNode, Node> head = new Pair<TreeNode, Node>(newRoot, root);

        // Add the first element to kickoff the loop
        Queue<Pair<TreeNode, Node>> queue = new ArrayDeque<Pair<TreeNode, Node>>();
        queue.add(head);

        while (queue.size() > 0) {
            Pair<TreeNode, Node> pair = queue.remove();
            TreeNode bNode = pair.first;
            Node nNode = pair.second;

            // Encoding the children nodes into a list of TreeNode.
            TreeNode prevBNode = null, headBNode = null;
            for (Node nChild : nNode.children) {
                TreeNode newBNode = new TreeNode(nChild.val);
                if (prevBNode == null) {
                    headBNode = newBNode;
                } else {
                    prevBNode.right = newBNode;
                }
                prevBNode = newBNode;

                Pair<TreeNode, Node> nextEntry = new Pair<TreeNode, Node>(newBNode, nChild);
                queue.add(nextEntry);
            }

            // Attach the list of children to the left node.
            bNode.left = headBNode;
        }

        return newRoot;
    }

    // Decodes your binary tree to an n-ary tree.
    public Node decode(TreeNode root) {
        if (root == null) {
            return null;
        }
        Node newRoot = new Node(root.val, new ArrayList<Node>());

        // adding the first element to kickoff the loop
        Queue<Pair<Node, TreeNode>> queue = new ArrayDeque<Pair<Node, TreeNode>>();
        Pair<Node, TreeNode> head = new Pair<Node, TreeNode>(newRoot, root);
        queue.add(head);

        while (queue.size() > 0) {
            Pair<Node, TreeNode> entry = queue.remove();
            Node nNode = entry.first;
            TreeNode bNode = entry.second;

            // Decoding the children list
            TreeNode firstChild = bNode.left;
            TreeNode sibling = firstChild;
            while (sibling != null) {
                Node nChild = new Node(sibling.val, new ArrayList<Node>());
                nNode.children.add(nChild);

                // prepare the decoding the children of the child, by standing in the queue.
                Pair<Node, TreeNode> nextEntry = new Pair<Node, TreeNode>(nChild, sibling);
                queue.add(nextEntry);

                sibling = sibling.right;
            }
        }

        return newRoot;
    }
}
