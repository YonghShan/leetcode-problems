/**
 * @author YonghShan
 * @date 8/10/21 - 23:59
 */
public class Solution1 {
    // DP
    /* Runtime: 15ms (faster than 10.53%)   O(m * n * maxMove)
       Memory: 39.4MB (less than 7.11%)     O(m * n * maxMove)
     */
    int mod = (int)1e9+7;
    int n, N;
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        this.n = n;
        this.N = maxMove;

        // f[i][j]代表从index = i(对应的grid坐标为(x, y) = (index / n, index % n))的起始位置在步数不超过j的条件下，移出界的路径数量
        int[][] f = new int[m * n][N + 1];

        // 初始化边缘格子在maxMove=[1,N]的条件下移出界的路径数量
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                // 位于grid边缘的格子满足以下四个特征:
                if (x == 0) add(x, y, f);
                if (x == m - 1) add(x, y, f);
                if (y == 0) add(x, y, f);
                if (y == n - 1) add(x, y, f);
            }
        }

        // 定义可移动的四个方向
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        // 从小到大枚举「可移动步数」
        for (int step = 2; step <= N; step++) {
            // 枚举所有格子
            for (int idx = 0; idx < m * n; idx++) {
                int x = parseIndex(idx)[0], y = parseIndex(idx)[1];
                for (int[] dir : dirs) {
                    int newX = x + dir[0], newY = y + dir[1];
                    // 如果位置有「相邻格子」，则「相邻格子」参与状态转移
                    if (newX >= 0 && newX <= m - 1 && newY >= 0 && newY <= n - 1) {
                        f[idx][step] += f[getIndex(newX, newY)][step - 1];
                        f[idx][step] %= mod;
                    }
                }
            }
        }

        // 最终结果为从起始点触发，最大移动步数不超 N 的路径数量
        return f[getIndex(startRow, startColumn)][N];
    }

    // 为每个边缘格子添加一条路径(初始值+1)
    void add(int x, int y, int[][] f) {
        int idx = getIndex(x, y);
        for (int step = 1; step <= N; step++) f[idx][step]++;
    }

    // 将(x, y)转换为index
    int getIndex(int x, int y) {
        return x * n + y;
    }

    // 将index解析回(x, y)
    int[] parseIndex(int idx) {
        return new int[]{idx / n, idx % n};
    }
}
