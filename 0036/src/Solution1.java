
/**
 * @author YonghShan
 * @date 3/18/21 - 14:18
 */
public class Solution1 {
    //
    /* Runtime: 2ms (faster than 86.15%)   O(1) since all we do here is just one iteration over the board with 81 cells.
       Memory: 38.8MB (less than 87.00%)   O(9x9x3) = O(1)
     */
    private int[][] rows;
    private int[][] cols;
    private int[][] subgrid;
    public boolean isValidSudoku(char[][] board) {
        int n = board.length;
        this.rows = new int[n][n+1];
        this.cols = new int[n][n+1];
        this.subgrid = new int[n][n+1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != '.') placeNumber(i, j, board[i][j]);
            }
        }
//        // 输出rows/cols/subgrid的值
//        int m = 0;
//        while (m < 9) {
//            System.out.println(Arrays.toString(rows[m++]));
//        }

        // 初始化之后，如果validate，那么每个数在每行每列每subgrid都应该只出现一次，即rows/cols/subgrid中所有的值都为0或1
        for (int k = 0; k < n; k++) {
            for (int l = 1; l < n+1; l++) { // 数组中只有1-9，但是为了方便rows/cols/subgrid定义时都是从0开始到9，也就是一共10位数，这里要遍历所有的值，l要小于n+1，而不是n
                if (rows[k][l] > 1 || cols[k][l] > 1 || subgrid[k][l] > 1) return false;
            }
        }

        return true;
    }

    public void placeNumber(int row, int col, char c) {
        int num = c - '0';
        int idx = (row / 3) * 3 + col / 3;
        rows[row][num]++;
        cols[col][num]++;
        subgrid[idx][num]++;
    }
}
