import java.util.*;

/**
 * @author YonghShan
 * @date 3/8/21 - 14:51
 */
public class Solution1 {
    // DFS: 自己定义了stack来代替recursion所用到的implicit call stack
    /* Runtime: 1ms  O(n+e), where n is the number of rooms, and e is the total number of keys.
       Memory: 39MB  O(n)
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Deque<Integer> stack = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        stack.addFirst(0);
        visited.add(0);

        while (!stack.isEmpty()) {
            int roomNum = stack.pop();
            List<Integer> neighbors = rooms.get(roomNum);
            for (Integer neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    stack.addFirst(neighbor);
                    visited.add(neighbor);
                }
            }
        }

        return visited.size() == rooms.size();
    }
}
