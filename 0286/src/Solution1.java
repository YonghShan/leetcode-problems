import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author YonghShan
 * @date 2/21/21 - 23:06
 */

class Solution1 {
    // 从每个empty room出发，通过上下左右邻居，去寻找GATE
    // TLE: wallsAndGates()和helper()中都有嵌套的循环，其中helper()中的嵌套循环又嵌套在wallsAndGates()中
    // 而且，为了避免判断邻居是否已经visited，还需要为Set visited重写equals()，从而保证contains()判断的正确
    /* Runtime: O(m^2n^2) (m×n size grid)
       Memory: O(mn)
     */

    public void wallsAndGates(int[][] rooms) {
        int height = rooms.length;
        int width = rooms[0].length;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                rooms[i][j] = helper(rooms, i, j);
            }
        }
    }

    public int helper(int[][] rooms, int row, int clo) {
        int height = rooms.length;
        int width = rooms[0].length;
        if (rooms[row][clo] == -1) return -1;
        if (rooms[row][clo] == 0) return 0;

        // room == INF:
        int distance = 0;
        Queue<int[]> queue = new LinkedList<>();
        // 没有必要设置visited，虽然确实会重复加之前的room，但是如果想判断visited是否包含，需要重写contains()所调用的equals()
        // 下方注释掉的contains()判断并不能起作用
        //Set<int[]> visited = new HashSet<>();
        queue.add(new int[]{row, clo});
        //visited.add(new int[]{row, clo});
        while (!queue.isEmpty()) {
            distance++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] room = queue.poll();
                int room_row = room[0];
                int room_clo = room[1];
                // 判断下邻居：
                if (room_row+1 < height) {
                    if (rooms[room_row+1][room_clo] == 0) {
                        return distance;
                    } else if (rooms[room_row+1][room_clo] != -1 /*&& !visited.contains(new int[]{room_row+1, room_clo})*/) {
                        queue.add(new int[]{room_row+1, room_clo});
                        //visited.add(new int[]{room_row+1, room_clo});
                    }
                }
                // 判断上邻居：
                if (room_row-1 >= 0) {
                    if (rooms[room_row-1][room_clo] == 0) {
                        return distance;
                    } else if (rooms[room_row-1][room_clo] != -1 /*&& !visited.contains(new int[]{room_row-1, room_clo})*/) {
                        queue.add(new int[]{room_row-1, room_clo});
                        //visited.add(new int[]{room_row-1, room_clo});
                    }
                }
                // 判断左邻居：
                if (room_clo-1 >= 0) {
                    if (rooms[room_row][room_clo-1] == 0) {
                        return distance;
                    } else if (rooms[room_row][room_clo-1] != -1 /*&& !visited.contains(new int[]{room_row, room_clo-1})*/) {
                        queue.add(new int[]{room_row, room_clo-1});
                        //visited.add(new int[]{room_row, room_clo-1});
                    }
                }
                // 判断右邻居：
                if (room_clo+1 < width) {
                    if (rooms[room_row][room_clo+1] == 0) {
                        return distance;
                    } else if (rooms[room_row][room_clo+1] != -1 /*&& !visited.contains(new int[]{room_row, room_clo+1})*/) {
                        queue.add(new int[]{room_row, room_clo+1});
                        //visited.add(new int[]{room_row, room_clo+1});
                    }
                }
            }
        }

        return Integer.MAX_VALUE;
    }
}