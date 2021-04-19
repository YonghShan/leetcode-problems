import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author YonghShan
 * @date 2/24/21 - 00:13
 */
class Solution1 {
    // DFS: 对于[1,2]这条edge，如果1通过它的除2之外的neighbor也能到达2，则[1,2]这条edge是多余的
    /* Runtime: O(N^2) where N is the number of vertices (and also the number of edges) in the graph.
                In the worst case, for every edge we include, we have to search every previously-occurring edge of the graph.
       Memory: O(N) The current construction of the graph has at most N nodes.
     */
    Set<Integer> seen = new HashSet();
    int MAX_EDGE_VAL = 1000;

    public int[] findRedundantConnection(int[][] edges) {
        // 这里要通过MAX_EDGE_VAL来定义graph的长度，是因为edges的长度不一定等于node的数量
        ArrayList<Integer>[] graph = new ArrayList[MAX_EDGE_VAL + 1]; // graph是一个每个元素都为ArrayList的向量
        for (int i = 0; i <= MAX_EDGE_VAL; i++) {
            graph[i] = new ArrayList();
        }

        // 通过edges为每个node都找到至少一个neighbor：只有少部分node多余一个neighbor
        // 这种处理方式保证了最终返回的edge是多余的edge中最靠后的
        for (int[] edge: edges) {
            seen.clear();
            if (!graph[edge[0]].isEmpty() && !graph[edge[1]].isEmpty() && // 一定要node a和node b双方的neighbor list都不为空，才进行dfs
                    dfs(graph, edge[0], edge[1])) {
                return edge;
            }
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        throw new AssertionError();
    }
    public boolean dfs(ArrayList<Integer>[] graph, int source, int target) {
        if (!seen.contains(source)) {
            seen.add(source);
            if (source == target) return true;
            for (int nei: graph[source]) { // graph[source]是node source的neighbor list
                if (dfs(graph, nei, target)) return true; // 注意target并没有换，体现的是source通过除了target之外的neighbor也能到达target，则返回true, 即[src,target]多余
            }
        }
        return false;
    }
}
