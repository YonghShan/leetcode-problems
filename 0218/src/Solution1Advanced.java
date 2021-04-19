import java.util.*;

/**
 * @author YonghShan
 * @date 3/22/21 - 23:49
 */
public class Solution1Advanced {
    // Solution1中之所以要自己定义BuildingPoint是为了重定义compareTo()，以满足3个edge cases
    // 但是现在只要将start的高度在记录时设为负数，则无论是像下面使用priority queue记录坐标，还是像Solution1中用数组记录坐标都可以满足edge cases的排序要求
    /* Runtime: 19ms   O(nlogn), where n is the number of buildings
       Memory: 42MB    O(n)
     */
    public List<List<Integer>> getSkyline(int[][] buildings) {
        // 自定义Priority Queue的排序方式：x-coordinate从小到大排序，如果x相等，则按照高度从低到高排序
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        // 本解法中只用了Priority Queue的offer(), isEmpty(), poll()均为O(logn): remove()等同于poll()，也是O(logn)，但remove(Object o)是O(n)
        // Split the original 1x3 input into two 1x2
        for (int[] building : buildings) {
            // start和高度  ATTENTION!!! 高度为负说明是start
            pq.offer(new int[] { building[0], -building[2] });
            // end和高度
            pq.offer(new int[] { building[1], building[2] });
        }

        List<List<Integer>> res = new ArrayList<>();

        TreeMap<Integer, Integer> queue = new TreeMap<>();  // 默认升序排列
        // 因为大楼的height是很可能会重复的，所以将key记录height，value记录同一个height出现的次数
        queue.put(0, 1);
        int prevMaxHeight = 0;

        while (!pq.isEmpty()) {
            int[] buildingPoint = pq.poll();
            if (buildingPoint[1] < 0) {  // isStart == true;
//                queue.put(-buildingPoint[1], queue.getOrDefault(-buildingPoint[1], 0) + 1);
                queue.compute(-buildingPoint[1], (key, value) -> {
                    if (value != null) {
                        return value + 1;
                    }
                    return 1;
                });
            }
            else {  // isStart == false;
//                queue.put(buildingPoint[1], queue.get(buildingPoint[1]) - 1);
//                if (queue.get(buildingPoint[1]) == 0) queue.remove(buildingPoint[1]);
                queue.compute(buildingPoint[1], (key, value) -> {
                    if (value == 1) {
                        return null;
                    }
                    return value - 1;
                });

            }

            int currMaxHeight = queue.lastKey();
            // if height changes from previous height then this building x becomes critical x.
            // So add it to the result.
            if (currMaxHeight != prevMaxHeight) {
                res.add(Arrays.asList(buildingPoint[0], currMaxHeight));
                prevMaxHeight = currMaxHeight;
            }
        }
        return res;
    }
}
