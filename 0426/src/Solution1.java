import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author YonghShan
 * @date 3/19/21 - 15:43
 */
public class Solution1 {
    // Inorder Traversal on Binary Search Tree: DFS Iteration with stack
    /* Runtime: 0ms     O(n) since each node is processed exactly once.
       Memory: 38.2MB   O(n)
     */
    public Node treeToDoublyList(Node root) {
        if (root == null) return root;
        Node dummy = new Node(0);
        Node prev = dummy;
        Node curr = root;
        Deque<Node> deque = new ArrayDeque<>();

        while (curr != null || !deque.isEmpty()) {
            while (curr != null) {
                deque.addFirst(curr);
                curr = curr.left;
            }

            // curr从leftmost开始依次向后，而prev一直保持为curr的前一个node，两者直接相互建立联系，实现doubly linked list
            curr = deque.pop();
            // prev和curr一前一后建立联系
            curr.left = prev;
            prev.right = curr;
            // prev和curr同时向后移一位
            prev = curr; // or prev = prev.right;
            curr = curr.right;
        }
        // while结束时，prev为rightmost，将其和dummy.right(i.e. leftmost)建立联系，形成cycle
        prev.right = dummy.right;
        dummy.right.left = prev;

        return dummy.right;
    }
}
