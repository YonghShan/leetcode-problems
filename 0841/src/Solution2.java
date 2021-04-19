import java.util.*;

/**
 * @author YonghShan
 * @date 3/8/21 - 15:00
 */
public class Solution2 {
    // BFS:
    /* Runtime: 1ms
       Memory: 38.6MB
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        q.add(0);
        visited.add(0);

        while (!q.isEmpty()) {
            int roomNum = q.poll();
            List<Integer> neighbors = rooms.get(roomNum);
            for (Integer neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    q.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }

        return visited.size() == rooms.size();
    }
}
