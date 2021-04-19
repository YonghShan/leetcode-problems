import java.util.*;

/**
 * @author YonghShan
 * @date 3/23/21 - 00:25
 */
public class Solution1Advanced2 {
    // Solution1中之所以要自己定义BuildingPoint是为了重定义compareTo()，以满足3个edge cases
    // 但是现在只要将start的高度在记录时设为负数，则无论是像下面使用priority queue记录坐标，还是像Solution1中用数组记录坐标都可以满足edge cases的排序要求
    /* Runtime: 14ms (faster than 83.96%)   O(nlogn), where n is the number of buildings
       Memory: 42MB  (less than 86.64%)     O(n)
     */
    public List<List<Integer>> getSkyline(int[][] buildings) {
        int[][] buildingPoints = new int[buildings.length * 2][2];
        // Split the original 1x3 input into two 1x2
        int i = 0;
        for (int[] building : buildings) {
            // start和高度入队  ATTENTION!!! 高度为负说明是start
            buildingPoints[i][0] = building[0];
            buildingPoints[i][1] = -building[2];
            // end和高度入队
            buildingPoints[i + 1][0] = building[1];
            buildingPoints[i + 1][1] = building[2];
            i += 2;
        }
//        for (int k=0;k<buildingPoints.length;k++) System.out.println (Arrays.toString (buildingPoints[k]));
//        System.out.println("-------");
        // Arrays.sort(buildingPoints, Comparator.comparingInt(o -> o[0])); // 依据第一列元素从小到大排序，当第一列元素相同时，依据第二列元素从大到小排列，不满足
        Arrays.sort(buildingPoints, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]; // 如果第一列元素不同，则依据第一列元素从小到大，否则（第一列元素相同），依据第二列元素从小到大
            }
        });
//        for (int k=0;k<buildingPoints.length;k++) System.out.println (Arrays.toString (buildingPoints[k]));

        List<List<Integer>> res = new ArrayList<>();

        TreeMap<Integer, Integer> queue = new TreeMap<>();  // 默认升序排列
        // 因为大楼的height是很可能会重复的，所以将key记录height，value记录同一个height出现的次数
        queue.put(0, 1);
        int prevMaxHeight = 0;

        for (int j = 0; j < buildingPoints.length; j++) {
            int[] buildingPoint = buildingPoints[j];
            if (buildingPoint[1] < 0) {  // isStart == true;
//                queue.put(-buildingPoint[1], queue.getOrDefault(-buildingPoint[1], 0) + 1);
                queue.compute(-buildingPoint[1], (key, value) -> {
                    if (value != null) {
                        return value + 1;
                    }
                    return 1;
                });
            } else {  // isStart == false;
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
