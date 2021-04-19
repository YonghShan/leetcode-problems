/**
 * @author YonghShan
 * @date 2/24/21 - 10:52
 */
class Solution2 {
    // Disjoint-Set / Union-Find：
    // 将所有node初始看作只包含自身的集合，通过edges中的边对两两元素所在的集合进行union，如果不能union，说明两元素处于同一集合
    /* Runtime: 1ms     O(Nα(N))≈O(N), where N is the number of vertices (and also the number of edges) in the graph, and α is the Inverse-Ackermann function.
       Memory: 39.3MB   O(N) The current construction of the graph (embedded in our dsu structure) has at most N nodes.
     */
    int MAX_EDGE_VAL = 1000;

    public int[] findRedundantConnection(int[][] edges) {
        DSU dsu = new DSU(MAX_EDGE_VAL + 1);
        for (int[] edge: edges) {
            // edge[0]表示node n，edge[1]表示node m；dsu.union为false，表示n和m处于同一集合中，[n,m]这条边会是原graph出现cycle
            if (!dsu.union(edge[0], edge[1])) return edge;
        }
        throw new AssertionError();
    }
}

class DSU {
    int[] parent;
    int[] rank;

    public DSU(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++) parent[i] = i; // 将parent设为自己，即自己即为自己所在集合的root
        rank = new int[size]; // 只有根节点的树（只有一个元素的集合），秩为0
    }

    public int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]); // Path Compression
        return parent[x];
    }

    public boolean union(int x, int y) {
        int xr = find(x), yr = find(y);
        if (xr == yr) {
            return false;
            // 当两棵秩不同的树合并后，新的树的秩为原来两棵树的秩的较大者
            // 当两棵秩相同的树合并后，新的树的秩为原来的树的秩+1
        } else if (rank[xr] < rank[yr]) {
            parent[xr] = yr;
        } else if (rank[xr] > rank[yr]) {
            parent[yr] = xr;
        } else {
            parent[yr] = xr;
            rank[xr]++;
        }
        return true;
    }
}