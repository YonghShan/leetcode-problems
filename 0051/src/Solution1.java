import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 3/16/21 - 18:47
 */
public class Solution1 {
    // Backtracking based on [0052]-Solution1
    /* Runtime: 4ms (faster than 61.72%)
       Memory: 38.9MB (less than 94.44%)
     */
    private int[][] keyboard;
    private List<List<String>> res;
    public List<List<String>> solveNQueens(int n) {
        res = new ArrayList<>();
        keyboard = new int[n][n];
        return backTrack(0, n);  // 从第0行开始
    }

    public List<List<String>> backTrack(int row, int n) {
        for (int clo = 0; clo < n; clo++) {     // 遍历每一行
            if (isNotUnderAttack(row, clo)) {
                putQueen(row, clo);
                //for (int i=0;i<n;i++) System.out.println ( Arrays.toString (keyboard[i]));  // 输出keyboard上的值
                if (row + 1 == n) {
                    res = buildSolution(n);
                } else {
                    res = backTrack(row+1, n);
                }
                removeQueen(row, clo);
            }
        }
        return res;
    }

    public List<List<String>> buildSolution(int n) { // 根据此时keyBoard的值构建List<List<String>>
        List<String> rowRes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (keyboard[i][j] == (i*n+j+1)) { // keyBoard中每个格子所记录的值表示的是该格子属于哪位Queen的攻击范围内，只有Queen所在的格子的值和该格子的index相同
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            rowRes.add(sb.toString());
        }
        res.add(rowRes);
        return res;
    }

    public boolean isNotUnderAttack(int row, int clo) {
        return keyboard[row][clo] == 0 ? true : false;
    }

    public void putQueen(int row, int clo) {
        int value = row * keyboard.length + clo + 1;
        for (int i = 0; i < keyboard.length; i++) {
            if (keyboard[row][i] == 0) keyboard[row][i] = value; // 该行加入攻击范围
            if (keyboard[i][clo] == 0) keyboard[i][clo] = value; // 该列加入攻击范围
        }
        // 将正反对角线加入攻击范围
        int left = clo - 1;
        int right = clo + 1;
        int j = 1;
        while (left >= 0 || right < keyboard.length) {
            if (right < keyboard.length) { // 更新右半部分
                if (row - j >= 0 && keyboard[row-j][right] == 0) keyboard[row-j][right] = value;
                if (row + j < keyboard.length && keyboard[row+j][right] == 0) keyboard[row+j][right] = value;
            }
            if (left >= 0) { // 更新左半部分
                if (row - j >= 0 && keyboard[row-j][left] == 0) keyboard[row-j][left] = value;
                if (row + j < keyboard.length && keyboard[row+j][left] == 0) keyboard[row+j][left] = value;
            }
            left--;
            right++;
            j++;
        }
    }

    public void removeQueen(int row, int clo) {
        int value = row * keyboard.length + clo + 1;
        for (int i = 0; i < keyboard.length; i++) {
            for (int j = 0; j < keyboard.length; j++) {
                if (keyboard[i][j] == value) keyboard[i][j] = 0;
            }
        }
    }
}
