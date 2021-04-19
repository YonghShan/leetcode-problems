import java.util.*;

/**
 * @author YonghShan
 * @date 2/23/21 - 15:16
 */
class Solution3 {
    // Using Node In-degree "Kahn's Algorithm"
    // 把int[][] prerequisites转换成edges，i.e. [a,b]表示b->a, 因为b是a的prerequisite
    /* Pseudo Code:
        1. Iterate all the nodes, 记录每个node的两个信息：
           i. the adjacency list of each node;
          ii. 每个node的in-degree (都放到一个map<node, in-degree>)
        2. 将in-degree为0的node放入Q中；
        3. The following steps are to be done until the Q become empty:
           i. pop the node from Q;
          ii. 找到这个node的所有neighbors，并将它们的in-degree减1；如果减为0，则将该neighbor放入Q中
         iii. 把这个node放入topological sorted order中；
          iv. 重复以上3步.
     */
    /* Runtime: 43ms (faster than 5.04%)     O(V+E)
       Memory: 39.77MB (less than 90.31%)    O(V+E): in worst case, 所有node的in-degree都为0，indegree[]和queue为O(V)
                                                     Adjacency List是以node来分类的 -> O(V)
                                                     neighbors是依据edges -> O(E)
                                                     topologicalOrder[]为O(V)
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
        int[] indegree = new int[numCourses];
        int[] topologicalOrder = new int [numCourses];
        Queue<Integer> queue = new LinkedList<>();

        // 1. Iterate all the nodes to get the adjacency list and in-degree:
        for (int i = 0; i < numCourses; i++) {
            List<Integer> neighbors = new ArrayList<>();
            for (int j = 0; j < prerequisites.length; j++) {
                if (prerequisites[j][1] == i) neighbors.add(prerequisites[j][0]);
                if (prerequisites[j][0] == i) indegree[i]++;
            }
            adjacencyList.put(i, neighbors);
            // 2. Put the node whose in-degree equals to 0 into Q:
            if (indegree[i] == 0) queue.add(i);
        }

        // 3.
        int idx = 0;
        while (!queue.isEmpty()) {
            int point = queue.poll();
            for (Integer neigh : adjacencyList.get(point)) {
                indegree[neigh]--;
                if (indegree[neigh] == 0) queue.add(neigh);
            }
            topologicalOrder[idx++] = point;
        }

        if (idx == numCourses) return topologicalOrder;
        return new int[0];
    }
}
