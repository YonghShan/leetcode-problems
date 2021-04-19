import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * @author YonghShan
 * @date 3/22/21 - 22:43
 */
public class Solution1NoNotation {
    // Solution1 无注释版
    // getSkyline()的返回值为LeetCode要求的List<List<Integer>>
    /* Runtime: 15ms (faster than 81.29%)   O(nlogn), where n is twice the number of buildings
                                            在每一个buildingPoint都要决定是做 (入queue || 出queue) && 查询currMaxHeight操作，而对于queue所使用的TreeMap，前两种操作都是O(logn)，第三种O(1),所以一共是O(nlogn)
       Memory: 41.9MB (less than 86.64%)    O(n)
     */
    static class BuildingPoint implements Comparable<BuildingPoint> {
        int x;
        boolean isStart;
        int height;

        @Override
        public int compareTo(BuildingPoint o) {
            if (this.x != o.x) {
                return this.x - o.x;
            } else {
                return (this.isStart ? -this.height : this.height) - (o.isStart ? -o.height : o.height);
            }
        }
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        BuildingPoint[] buildingPoints = new BuildingPoint[buildings.length*2];
        int index = 0;

        for(int building[] : buildings) {
            buildingPoints[index] = new BuildingPoint();
            buildingPoints[index].x = building[0];
            buildingPoints[index].isStart = true;
            buildingPoints[index].height = building[2];

            buildingPoints[index + 1] = new BuildingPoint();
            buildingPoints[index + 1].x = building[1];
            buildingPoints[index + 1].isStart = false;
            buildingPoints[index + 1].height = building[2];
            index += 2;
        }
        Arrays.sort(buildingPoints);

        TreeMap<Integer, Integer> queue = new TreeMap<>();
        // PriorityQueue<Integer> queue1 = new PriorityQueue<>(Collections.reverseOrder());
        queue.put(0, 1);
        // queue1.add(0);
        int prevMaxHeight = 0;
        List<List<Integer>> result = new ArrayList<>();
        for(BuildingPoint buildingPoint : buildingPoints) {
            if (buildingPoint.isStart) {
                queue.compute(buildingPoint.height, (key, value) -> {
                    if (value != null) {
                        return value + 1;
                    }
                    return 1;
                });
                //  queue1.add(cp.height);
            } else {
                queue.compute(buildingPoint.height, (key, value) -> {
                    if (value == 1) {
                        return null;
                    }
                    return value - 1;
                });
                // queue1.remove(cp.height);
            }

            int currentMaxHeight = queue.lastKey();
            // int currentMaxHeight = queue1.peek();
            if (prevMaxHeight != currentMaxHeight) {
                result.add(Arrays.asList(buildingPoint.x, currentMaxHeight));
                prevMaxHeight = currentMaxHeight;
            }
        }
        return result;
    }
}