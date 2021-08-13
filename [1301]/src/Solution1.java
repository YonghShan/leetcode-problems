import java.util.List;

/**
 * @author YonghShan
 * @date 8/12/21 - 23:58
 */
public class Solution1 {
    int n;
    int INF = Integer.MIN_VALUE;
    int mod = (int)1e9 + 7;
    public int[] pathsWithMaxScore(List<String> board) {
        n = board.size();

        // Convert List to Array
        char[][] c = new char[n][n];
        for (int i = 0; i < n; i++) c[i] = board.get(i).toCharArray();

        int[] f = new int[n * n];
        int[] g = new int[n * n];
        // 开始从起点位置 (n - 1, n - 1) 开始初始化和状态转移
        for (int x = n - 1; x >= 0; x--) {
            for (int y = n - 1; y >= 0; y--) {
                // 1.初始化部分：
                int idx = getIndex(x, y);

                // 1.1 如果为起点：
                if (c[x][y] == 'S') { //
                    g[idx] = 1; // 从起点(n-1,n-1)到起点的路径肯定存在一条
                    continue;
                }

                // 1.2 如果为障碍点：
                if (c[x][y] == 'X') {
                    f[idx] = INF;
                    continue;
                }

                // 2. 状态转移：
                int val = c[x][y] == 'E' ? 0 : c[x][y] - '0';
                int u = INF, t = 0;

                // “合法”定义为「不出界」
                // 但是如果将“合法”定义为「不出界且不为障碍物」（即if中多个判断：c[][] != 'X'），则初始化中不需要1.2
                // 2.1 下方合法：
                if (x + 1 < n) {
                    int cur = f[getIndex(x + 1, y)] + val;
                    int cnt = g[getIndex(x + 1, y)];
                    int[] res = update(cur, cnt, u, t);
                    u = res[0];
                    t = res[1];
                }

                // 2.2 右边格子合法：
                if (y + 1 < n) {
                    int cur = f[getIndex(x, y + 1)] + val;
                    int cnt = g[getIndex(x, y + 1)];
                    int[] res = update(cur, cnt, u, t);
                    u = res[0];
                    t = res[1];
                }

                // 2.3 右下格子合法：
                if (x + 1 < n && y + 1 < n) {
                    int cur = f[getIndex(x + 1, y + 1)] + val;
                    int cnt = g[getIndex(x + 1, y + 1)];
                    int[] res = update(cur, cnt, u, t);
                    u = res[0];
                    t = res[1];
                }

                f[idx] = u < 0 ? INF : u;
                g[idx] = t;
            }
        }


        // System.out.println(Arrays.toString(f));


        // 构造答案：
        int[] ans = new int[2];
        if (f[getIndex(0, 0)] == INF) {
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

    int[] update(int cur, int cnt, int u, int t) {
        int[] ans = new int[]{u, t};

        if (cur > u) {
            ans[0] = cur;
            ans[1] = cnt;
        } else if (cur == u && cur != INF) {
            ans[1] += cnt;
        }

        ans[1] %= mod;
        return ans;
    }
}
