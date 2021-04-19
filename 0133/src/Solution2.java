import java.util.*;


/**
 * @author YonghShan
 * @date 3/4/21 - 15:12
 */

class Solution2 {
    // BFS:
    /* Runtime: 24ms (faster than 90.62%)  O(n+m), where m is the number of edges in graph
       Memory: 39.1MB (less than 78.37%)   O(n), where n is the number of vertices in graph（仅仅是map所占用的空间，queue最后为空）
     */
    public Node cloneGraph(Node node) {
        if (node == null) return null;

        Node cloned_root = new Node(node.val);
        Map<Node, Node> cloned = new HashMap<>();
        cloned.put(node, cloned_root);
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            for (Node neighbor : temp.neighbors) {
                if (cloned.containsKey(neighbor)) {
                    cloned.get(temp).neighbors.add(cloned.get(neighbor));
                } else {
                    Node n = new Node(neighbor.val);
                    cloned.put(neighbor, n);
                    cloned.get(temp).neighbors.add(n);
                    queue.add(neighbor);
                }
            }
        }

        return cloned_root;
    }
}
