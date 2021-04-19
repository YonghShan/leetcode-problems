import java.util.*;

/**
 * @author YonghShan
 * @date 2/23/21 - 00:11
 */
class Solution1 {
    // Using DFS to get the Topological Sorted Order
    // 把int[][] prerequisites转换成edges，i.e. [a,b]表示b->a, 因为b是a的prerequisite
    /* Pseudo Code:
        ➔ let S be a stack of courses
        ➔ function dfs(node)
        ➔     for each neighbor in adjacency list of node
        ➔          dfs(neighbor)
        ➔     add node to S
     */
    /* Runtime: 83ms (faster than 5.04%)  O(V+E)
       Memory: 40.1MB (less than 62.98%)  O(V+E): Adjacency List是以node来分类的 -> O(V)
                                                  neighbors是依据edges -> O(E)
                                                  Recursion in worst case -> O(E)
                                                  Stack中包含所有vertices -> O(V)
                                        =>Sum up: O(V + E)
     */
    private Map<Integer, List<Integer>> adjacencyList; // 每一个node和其相对应的adjacency list
    private List<Integer> neighbors; // 一个node的全部neighbors的集合，i.e. adjacency list
    private Deque<Integer> stack; // a stack of courses == the final topological sorted order
    private List<Integer> visited; // 不同于stack（对其neighbors都DFS完了才入stack），刚开始对其DFS之前就入visited
    private boolean isPossible;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        adjacencyList = new HashMap<>();
        stack = new ArrayDeque<>();
        visited = new ArrayList<>();
        isPossible = true;

        // 通过prerequisites来获取每个node的adjacency list
        for (int i = 0; i < numCourses; i++) {
            neighbors = new ArrayList<>();
            for (int j = 0; j < prerequisites.length; j++) {
                if (prerequisites[j][1] == i) neighbors.add(prerequisites[j][0]);
            }
            adjacencyList.put(i, neighbors);
        }

        // 对每个node依次进行DFS: 先是取所有Vertices，进入dfs()再取每个Vertex的edges (一共是取了所有edges in the graph) -> O(V+E)
        for (int i = 0; i < numCourses; i++) {
            if (isPossible == false) break;
            dfs(i);
        }

        if (isPossible == false) return new int[0];

        // isPossible为true才将stack中的元素依次pop
        int[] result = new int[numCourses];
        for (int k = 0; k < numCourses; k++) {
            result[k] = stack.pop();
        }

        return result;
    }

    public void dfs(int i) {
        if (stack.contains(i) || isPossible == false) return;
        if (visited.contains(i)) {
            isPossible = false;
            return;
        } else {
            visited.add(i);
            for (Integer node : adjacencyList.get(i)) {
                dfs(node);
            }
        }

        stack.addFirst(i);
    }
}