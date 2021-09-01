import java.util.List;

/**
 * @author YonghShan
 * @date 8/12/21 - 23:58
 */
public class Solution1 {
    // 两个 DP数组 的综合题
    /* Runtime: 15ms (faster than 29.03%)   O(n^2)
       Memory: 39.6MB (less than 27.42%)    O(n^2)
     */
    int n;
    int INF = Integer.MIN_VALUE;
    int mod = (int)1e9 + 7;
    public int[] pathsWithMaxScore(List<String> board) {
        n = board.size();

        // 将 board 转为二维数组
        char[][] c = new char[n][n];
        for (int i = 0; i < n; i++) c[i] = board.get(i).toCharArray();

        // f(i) 代表从右下角起点 (n-1, n-1) 到位置 index 的最大得分
        int[] f = new int[n * n];
        // g(i) 代表从右下角起点 (n-1, n-1) 到位置 index 并取到最大得分的方案数量
        int[] g = new int[n * n];

        // 开始从起点位置 (n - 1, n - 1) 开始初始化和状态转移
        for (int x = n - 1; x >= 0; x--) {
            for (int y = n - 1; y >= 0; y--) {
                // 1.初始化部分：
                int idx = getIndex(x, y);

                // 1.1 如果为起点：
                // g[idx] = 1 : 从起点(n-1, n-1)到起点的路径肯定存在一条，这样我们就有了一个「有效值」可以滚动下去
                // f[idx] = 0 : 代表在起点得分为 0
                if (c[x][y] == 'S') {
                    g[idx] = 1;
                    continue;
                }

                // 1.2 如果为障碍点：
                // g[idx] = 0   : 「障碍点」不可访问，路径为 0
                // f[idx] = INF : 「障碍点」不可访问，得分为无效值
                if (c[x][y] == 'X') {
                    f[idx] = INF;
                    continue;
                }

                // 2. 状态转移：
                // 如果是第一个格子 (0, 0)（即终点），这时候位置得分为 0
                int val = c[x][y] == 'E' ? 0 : c[x][y] - '0';

                // u 代表当前位置的「最大得分」；t 代表取得最大得分的「方案数」
                int u = INF, t = 0;

                // “合法”定义为「不出界」
                // 即使将“合法”定义为「不出界且不为障碍物」（即if中多个判断：c[][] != 'X'），初始化中仍需要1.2
                // 2.1 「如果「下方格子」合法，尝试从「下方格子」进行转移：
                if (x + 1 < n) {
                    int cur = f[getIndex(x + 1, y)] + val;
                    int cnt = g[getIndex(x + 1, y)];
                    int[] res = update(cur, cnt, u, t);
                    u = res[0];
                    t = res[1];
                }

                // 2.2 如果「右边格子」合法，尝试从「右边格子」进行转移：
                if (y + 1 < n) {
                    int cur = f[getIndex(x, y + 1)] + val;
                    int cnt = g[getIndex(x, y + 1)];
                    int[] res = update(cur, cnt, u, t);
                    u = res[0];
                    t = res[1];
                }

                // 2.3 如果「右下角格子」合法，尝试从「右下角格子」进行转移：
                if (x + 1 < n && y + 1 < n) {
                    int cur = f[getIndex(x + 1, y + 1)] + val;
                    int cnt = g[getIndex(x + 1, y + 1)];
                    int[] res = update(cur, cnt, u, t);
                    u = res[0];
                    t = res[1];
                }

                // 更新 dp 值
                f[idx] = u < 0 ? INF : u; // u 会小于0是因为当前格子只能由「障碍点」转移而来
                g[idx] = t;
            }
        }

        // System.out.println(Arrays.toString(f));

        // 构造答案：
        int[] ans = new int[2];
        if (f[getIndex(0, 0)] == INF) { // 如果终点不可达（动规值为 INF）时，写入 (0, 0)
            ans[0] = 0;
            ans[1] = 0;
        } else {
            ans[0] = f[getIndex(0, 0)];
            ans[1] = g[getIndex(0, 0)];
        }
        return ans;
    }

    int getIndex(int x, int y) {
        return x * n + y;
    }

    int[] parseIndex(int index) {
        return new int[]{index / n, index % n};
    }

    // 更新 dp 值: 单独写出来主要是因为g数组「状态转移方程」的特殊性
    int[] update(int cur, int cnt, int u, int t) {
        // 起始答案为 [u, t] : u 为「最大得分」，t 为最大得分的「方案数」
        int[] ans = new int[]{u, t};

        // 如果当前值大于 u，更新「最大得分」和「方案数」
        if (cur > u) {
            ans[0] = cur;
            ans[1] = cnt;
            // 如果当前值等于 u，增加「方案数」
        } else if (cur == u && cur != INF) { // 重要！！！
            ans[1] += cnt;
        }

        ans[1] %= mod;
        return ans;
    }
}
