import java.util.HashSet;

/**
 * @author YonghShan
 * @date 3/18/21 - 15:16
 */
public class Solution3 {
    // 不用int[9][9], 而是HashSet[9]: 和Solution1比起来可以提前结束
    /* Runtime: O(1) since all we do here is just one iteration over the board with 81 cells.
       Memory: O(9x3) = O(1)
     */
    public boolean isValidSudoku(char[][] board) {
        // init data
        HashSet<Integer>[] rows = new HashSet[9];
        HashSet<Integer> [] columns = new HashSet[9];
        HashSet<Integer> [] boxes = new HashSet[9];
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<Integer>();
            columns[i] = new HashSet<Integer>();
            boxes[i] = new HashSet<Integer>();
        }

        // validate a board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char num = board[i][j];
                if (num != '.') {
                    int n = (int) num;
                    int box_index = (i / 3 ) * 3 + j / 3;
                    // HashSet.add(): Adds the specified element to this set if it is not already present.
                    // More formally, adds the specified element e to this set if this set contains no element e2
                    // such that (e==null ? e2==null : e.equals(e2)). If this set already contains the element,
                    // the call leaves the set unchanged and returns false.
                    if((!rows[i].add(n)) || (!columns[j].add(n)) || (!boxes[box_index].add(n))){ // 如果rows/cols/boxes add不了n，这说明n已经存在于其中了，即n重复
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
