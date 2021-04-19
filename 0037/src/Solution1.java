/**
 * @author YonghShan
 * @date 3/17/21 - 17:44
 */
public class Solution1 {
    // Backtracking
    /* Runtime: 5ms (faster than 83.75%)
                Time complexity is constant here since the board size is fixed and there is no N-parameter to measure.
                In total that results in not more than 9! possibilities for a just one row, that means not more than
                operations in total. Let's compare: brute force with 9^81 and backtracking with (9!)^9, the number of operations is reduced in 10^27 times!
       Memory: 36.2MB (less than 88.06%)
                The board size is fixed, and the space is used to store board, rows, columns and subgrid structures, each contains 81 elements.
     */
    private char[][] board;
    private int [][] rows;
    private int [][] cols;
    private int[][] subgrid;
    boolean sudokuSolved = false;
    public void solveSudoku(char[][] board) {
        this.board = board;
        this.rows = new int[board.length][board.length+1];
        this.cols = new int[board.length][board.length+1];
        this.subgrid = new int[board.length][board.length+1]; // 9x9的board有9个subgrid，从0到8编号，每个subgrid里面要填1-9，如果第0个subgrid里面已经存在3，则将subgrid[0][3]置为1
        // 先初始化rows, cols, subgrid
        for (int i = 0; i < 9; i++) { // 要用1-9填满一张数独，那么这个数独一定是9x9的
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') placeNumber(i, j, board[i][j]);
            }
        }
        backtrack(0, 0);
    }

    public void backtrack(int row, int col) {
        boolean findEmpty = false;
        while (row <= 8 && col <= 8) {  // 从当前坐标一直遍历到末尾，用两层for循环嵌套不行
            if (board[row][col] == '.') {
                findEmpty = true;
                break;
            }
            col++;
            if (col == 9) {
                col = 0;
                row++;
            }
        }

        if (findEmpty == true) {  // 找到需要填的空格
            for (int i = 1; i < 10; i++) {
                if (isValidate(row, col, i)) { // 判断在board[row][col]处放数字i是否可行
                    char ca = (char) (i+48);
                    placeNumber(row, col, ca);
                    if (row == 8 && col == 8) { // 这里也可以不加这个条件，当(8,8)时，backtrack(row+1, 0)=backtrack(9, 0) 返回sudokuSolved = true;
                        sudokuSolved = true;    // 这里加上只是少一次recursion，对总的运行时间影响甚小，为了代码简洁可以删去
                    } else if (col == 8) {
                        backtrack(row+1, 0);
                    } else {
                        backtrack(row, col+1);
                    }
                    if (sudokuSolved == false) removeNumber(row, col, ca);
                }
            }
        } else { // 没找到，说明从输入的坐标开始一直到结尾都已经填好值了，sudoku被解决
            sudokuSolved = true;
        }
    }

    public boolean isValidate(int row, int col, int guess) {
        int idx = (row / 3) * 3 + col / 3;
        return rows[row][guess] + cols[col][guess] + subgrid[idx][guess] == 0;
    }

    public void placeNumber(int row, int col, char tar) {
        int num = tar - '0';
        int idx = (row / 3) * 3 + col / 3;
        rows[row][num]++; // rows, cols, subgrid的值只有可能是1或0
        cols[col][num]++;
        subgrid[idx][num]++;
        board[row][col] = tar;
    }

    public void removeNumber(int row, int col, char tar) {
        int num = tar - '0';
        int idx = (row / 3) * 3 + col / 3;
        rows[row][num]--;
        cols[col][num]--;
        subgrid[idx][num]--;
        board[row][col] = '.';
    }
}

