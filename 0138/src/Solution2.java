import java.util.HashMap;
import java.util.Map;

/**
 * @author YonghShan
 * @date 2/13/21 - 14:55
 */

class Solution2 {
    // Iteration with n extra space:
    // 复制出node放入visitedHistory，如果这个node.next/random不为null且不存在于visitHistory里，则new出来；如果存在，则reference
    /* Runtime: 0ms   O(n)
       Memory: 38.7MB   O(n)
     */
    public Node copyRandomList(Node head) {
        if (head == null) return head;

        Map<Node, Node> visitedHistory = new HashMap<Node, Node>();
        Node old_node = head;
        Node new_node = new Node(old_node.val);
        visitedHistory.put(old_node, new_node);

        while (old_node != null) {
            if (old_node.next != null) {
                if (visitedHistory.containsKey(old_node.next)) {
                    new_node.next = visitedHistory.get(old_node.next);
                } else {
                    Node new_next = new Node(old_node.next.val);
                    new_node.next = new_next;
                    visitedHistory.put(old_node.next, new_next);
                }
            }
            // 也可以把这段代码单独写成一个function
            if(old_node.random != null) {
                if (visitedHistory.containsKey(old_node.random)) {
                    new_node.random = visitedHistory.get(old_node.random);
                } else {
                    Node new_random = new Node(old_node.random.val);
                    new_node.random = new_random;
                    visitedHistory.put(old_node.random, new_random);
                }
            }

            old_node = old_node.next;
            new_node = new_node.next;
        }

        return visitedHistory.get(head);
    }
}