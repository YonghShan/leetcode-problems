import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author YonghShan
 * @date 3/16/21 - 23:33
 */

public class Solution1 {
    // Spiral Backtracking
    /* Runtime: 7ms (faster than 55.94%)     O(n-m), where n is the number of cells in the room and m is the number of the obstacles.
                                             We visit each non-obstacle cell once and only once. At each visit, we will check 4 directions around the cell.
                                             Therefore, the total number of operations would be 4⋅(N−M).
       Memory: 39.1MB (less than 63.12%)     O(n-m): We employed a hashtable to keep track of whether a non-obstacle cell is visited or not.
     */
    private Robot robot;
    private Set<List<Integer>> cleaned = new HashSet<>();
    private static final int[][] DIRECTIONS = {{-1, 0}, {0,  1}, {1,  0}, {0, -1}};
    public void cleanRoom(Robot robot) {
        this.robot = robot;
        backtrack(0, 0, 0); // 一定要记录并传入初始方向
    }

    public void backtrack(int row, int clo, int dir) {  // robot进入一个新的格子（即开始backtrack时），所面朝的方向并不一定是up，而是要根据传入的参数dir来决定
        robot.clean();
        cleaned.add(Arrays.asList(row, clo));
        for (int i= 0; i < 4; i++) {
            int d = (dir + i) % 4; // 在这个for循环的结尾，robot会转向右，此时d表示更新后的方向，是由原方向dir加不断增加的i得到的，而转向后所朝向的新的格子的坐标由d所对应的数组决定
            int r = row + DIRECTIONS[d][0];
            int c = clo + DIRECTIONS[d][1];
            if (!isCleaned(r, c) && robot.move()) { // (r, c)所在的格子没有被打扫过，而且robot所面对的cell（即(r, c)）是open的（即不是obstacle）并进入
                backtrack(r, c, d);
                goBack();
            }
            // 当前邻居invalidate，继续检查下一个邻居
            robot.turnRight();
        }
    }

    public boolean isCleaned(int row, int clo) {
        return cleaned.contains(Arrays.asList(row, clo));
    }

    public void goBack() {
        // 掉头
        robot.turnLeft();
        robot.turnLeft();
        // 前进
        robot.move();
        // 恢复之前在该cell时的原方向
        robot.turnLeft();
        robot.turnLeft();
    }
}
