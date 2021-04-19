import java.util.*;

/**
 * @author YonghShan
 * @date 3/4/21 - 00:03
 */

class Solution1 {
    // DFS: 如果neighbor还没被visited，则继续向下recursion；如果已经被visited，那也一定被cloned，通过neighbor.val去cloned中取来设为自己的neighbor之一
    /* Runtime: 24ms (faster than 98.98%)  O(n+m), where m is the number of edges in graph
                                          （和官方DFS内核一样，官方给的是O(n+m)，我觉得准确些应该是O(n+2m)
                                            UPDATE: O(n+m)是因为map.get()是O(1)的）
       Memory: 39.1MB (less than 78.37%)   O(n), where n is the number of vertices in graph
     */
    private Map<Node, Node> cloned = new HashMap<>();
    public Node cloneGraph(Node node) {
        if (node == null) return null;

        List<Node> root_nerighbors = node.neighbors;
        Node cloned_root = new Node(node.val);
        if (root_nerighbors.size() == 0) return cloned_root;
        cloned.put(node, cloned_root);

        for (Node neighbor : root_nerighbors) {
            if (!cloned.containsKey(neighbor)) {
                cloned_root.neighbors.add(cloneGraph(neighbor));
            } else {
                cloned_root.neighbors.add(cloned.get(neighbor));
            }
        }

        return cloned_root;
    }
}
