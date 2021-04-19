import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 3/16/21 - 20:19
 */
class Solution {
    // Backtracking: 官方(改了一下主对角线的编号方法) n^2 < n^3 < 2^n < 3^n < n! < n^n
    /* Runtime: O(n!)   n^2 < n^3 < 2^n < 3^n < n! < n^n
       Memory: O(n)
     */
    int rows[];
    // "hill" diagonals   major/principal/primary/main diagonals
    int hills[];
    // "dale" diagonals   minor/counter/secondary/anti- diagonals
    int dales[];
    int n;
    // output
    List<List<String>> output = new ArrayList();
    // queens positions
    int queens[];

    public boolean isNotUnderAttack(int row, int col) {
        int res = rows[col] + hills[(n-1) - (row - col)] + dales[row + col];
        return (res == 0) ? true : false;
    }

    public void placeQueen(int row, int col) {
        queens[row] = col;
        rows[col] = 1;
        hills[(n-1) - (row - col)] = 1;  // "hill" diagonals：将Queen所在第(n-1)-(row-col)条主对角线设为1 （主对角线从左下开始编号）
        dales[row + col] = 1;   //"dale" diagonals
    }

    public void removeQueen(int row, int col) {
        queens[row] = 0;
        rows[col] = 0;
        hills[(n-1) - (row - col)] = 0;
        dales[row + col] = 0;
    }

    public void addSolution() {
        List<String> solution = new ArrayList<String>();
        for (int i = 0; i < n; ++i) {
            int col = queens[i];
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < col; ++j) sb.append(".");
            sb.append("Q");
            for(int j = 0; j < n - col - 1; ++j) sb.append(".");
            solution.add(sb.toString());
        }
        output.add(solution);
    }

    public void backtrack(int row) {
        for (int col = 0; col < n; col++) {
            if (isNotUnderAttack(row, col)) {
                placeQueen(row, col);
                // if n queens are already placed
                if (row + 1 == n) addSolution();
                    // if not proceed to place the rest
                else backtrack(row + 1);
                // backtrack
                removeQueen(row, col);
            }
        }
    }

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        rows = new int[n];
        hills = new int[2 * n - 1]; // 对于n=4，即4x4的棋盘，共有7条主对角线: 从左下开始编号
        dales = new int[2 * n - 1]; // 对于n=4，即4x4的棋盘，共有7条反对角线
        queens = new int[n];

        backtrack(0);
        return output;
    }
}
