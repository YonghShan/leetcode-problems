import java.util.LinkedList;
import java.util.Queue;

/**
 * @author YonghShan
 * @date 2/19/21 - 21:48
 */
class Solution2 {
    // Level Order Traversal: the same as Problem 116
    /* Basic algorithm for level-order traversal:
       while (!Q.empty()) {
           size = Q.size()
           for i in range 0..size {
               node = Q.pop()
               Q.push(node.left)
               Q.push(node.right)
           }
       }
     */
    /* Runtime: O(N) since we process each node exactly once.
       Memory: O(N). The space complexity for breadth first traversal is the maximum space occupied
                     and the space occupied by the queue is dependent upon the maximum number of nodes in particular level.
     */
    public Node connect(Node root) {

        if (root == null) {
            return root;
        }

        // Initialize a queue data structure which contains just the root of the tree
        Queue<Node> Q = new LinkedList<Node>();
        Q.add(root);

        // Outer while loop which iterates over each level
        while (Q.size() > 0) {

            // Note the size of the queue
            int size = Q.size();

            // Iterate over all the nodes on the current level
            for(int i = 0; i < size; i++) {

                // Pop a node from the front of the queue
                Node node = Q.poll();

                // This check is important. We don't want to establish any wrong connections. The queue will
                // contain nodes from 2 levels at most at any point in time. This check ensures we only
                // don't establish next pointers beyond the end of a level
                if (i < size - 1) {
                    node.next = Q.peek();
                }

                // Add the children, if any, to the back of the queue
                if (node.left != null) {
                    Q.add(node.left);
                }
                if (node.right != null) {
                    Q.add(node.right);
                }
            }
        }

        // Since the tree has now been modified, return the root node
        return root;
    }
}