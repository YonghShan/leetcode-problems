/**
 * @author YonghShan
 * @date 3/16/21 - 18:40
 */
class Solution2 {
    // Backtracking: 官方(改了一下主对角线的编号方法) n^2 < n^3 < 2^n < 3^n < n! < n^n
    /* Runtime: 0ms    O(n!): There is N possibilities to put the first queen, not more than N (N - 2) to put the second one,
                              not more than N(N - 2)(N - 4) for the third one etc. In total that results in O(N!) time complexity.
       Memory: 35.6MB  O(n) to keep an information about diagonals and rows.
     */
    public boolean is_not_under_attack(int row, int col, int n,
                                       int [] rows,
                                       int [] hills,
                                       int [] dales) {
        int res = rows[col] + hills[(n-1) - (row - col)] + dales[row + col];
        return (res == 0) ? true : false;
    }

    public int backtrack(int row, int count, int n,
                         int [] rows,
                         int [] hills,
                         int [] dales) {
        for (int col = 0; col < n; col++) {
            if (is_not_under_attack(row, col, n, rows, hills, dales)) {
                // place_queen
                rows[col] = 1;
                hills[(n-1) - (row - col)] = 1;  // "hill" diagonals：将Queen所在第(n-1)-(row-col)条主对角线设为1 （主对角线从左下开始编号）
                dales[row + col] = 1;   //"dale" diagonals

                // if n queens are already placed
                if (row + 1 == n) count++;
                    // if not proceed to place the rest
                else count = backtrack(row + 1, count, n,
                        rows, hills, dales);

                // remove queen
                rows[col] = 0;
                hills[(n-1) - (row - col)] = 0;
                dales[row + col] = 0;
            }
        }
        return count;
    }

    public int totalNQueens(int n) {
        int rows[] = new int[n];
        // "hill" diagonals
        int hills[] = new int[2 * n - 1]; // 对于n=4，即4x4的棋盘，共有7条主对角线: 从左下开始编号
        // "dale" diagonals
        int dales[] = new int[2 * n - 1]; // 对于n=4，即4x4的棋盘，共有7条反对角线

        return backtrack(0, 0, n, rows, hills, dales);
    }
}
