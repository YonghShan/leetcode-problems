/**
 * @author YonghShan
 * @date 3/16/21 - 23:35
 */
public interface Robot {
    // Returns true if the cell in front is open and robot moves into the cell.
    // Returns false if the cell in front is blocked and robot stays in the current cell.
    public boolean move(); // 注：move()不光是判断能否前进，而且当可以前进时即进入

    // Robot will stay in the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
    public void turnLeft();
    public void turnRight();

    // Clean the current cell.
    public void clean();
}
