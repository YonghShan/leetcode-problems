import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * @author YonghShan
 * @date 3/22/21 - 20:51
 */
public class Solution1 {
    // Sweep Line Algorithm: From Tushar Roy: https://www.youtube.com/watch?v=GSBLe8cKu0s 详见笔记
    // 假设有一条虚拟的垂直线从左到右扫过大楼：
    //    1. We move from left to right encountering the starts and the ends of the building.
    //    2. Whenever we encounter a start of a building, we push the height of that building into a priority queue
    //       if the max in the priority queue changes, it means that this building at this start point must be taller than
    //       every other building which is overlapping at that start point, so it needs to be part of final answer.
    //    3. Also whenever we encounter the end of the building, we need to remove the height of that building
    //       from the priority queue and again if the max in the priority queue changes, it means that that value needs
    //       to be part of final answer.
    // 3种edge cases：
    //    1. 两个building的start重合：building with higher height is considered first
    //    2. 两个building的end重合：building with lower height is considered first
    //    3. 前一个building的end和后一个building的start重合：后一个building is considered first
    /**
     * Represents either start or end of building
     */
    static class BuildingPoint implements Comparable<BuildingPoint> { // e.g. Input[0] = [2, 9, 10] => BuildingPoint1 = [2, 10, True] and BuildingPoint2 = [9, 10, False]
        int x;
        boolean isStart; // true则表示该BuildingPoint(x, height)为一个building的start，反之为end
        int height;

        @Override
        public int compareTo(BuildingPoint o) {  // 重写是为了之后sort时，满足3个edge cases
            // first compare by x.
            // If they are same then use this logic:
            //    if two starts are compared then higher height building should be picked first   (Edge case I)
            //    if two ends are compared then lower height building should be picked first      (Edge case II)
            //    if one start and end is compared then start should appear before end            (Edge case III)
            if (this.x != o.x) {
                return this.x - o.x;  // a.compareTo(b): 如果a的x大于b的x，则return (a.x-b.x) > 0: compareTo()返回1是交换，实现升序
            } else {
                return (this.isStart ? -this.height : this.height) - (o.isStart ? -o.height : o.height);
                // 分析：两个building的x相同时：第一个building指左边/前一个building
                //      1. 第一个building为start，第二个也为start: return (-this.height - -o.height) => return (o.height - this.height): 如果前一个高，则return <0, 即维持前一个在前面
                //      2. 第一个building为start，第二个为end: 不可能
                //      3. 第一个building为end，第二个为start: return (this.height - -o.height) > 0 => return (this.height + o.height) > 0: 无论各自的高度，总是交换，将第二个start移至前面
                //      4. 第一个building为end，第二个为end: return (this.height - o.height): 如果前一个高，则return >0, 交换，将矮的放在前
            }
        }
    }

    public List<int[]> getSkyline(int[][] buildings) { // LeetCode要求return的是List<List<Integer>>, 见Solution1NoNotation.java

        // for all start and end of building, put them into List of BuildingPoint
        BuildingPoint[] buildingPoints = new BuildingPoint[buildings.length*2];
        int index = 0;
        // Split the original 1x3 input into two 1x2
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
        Arrays.sort(buildingPoints); // 调用自定义的compareTo();按照x-coordinate实现升序排列

        // using TreeMap because it gives log time performance.
        // PriorityQueue in java does not support remove(object) operation in log time.
        TreeMap<Integer, Integer> queue = new TreeMap<>();  // 因为大楼的height是很可能会重复的，所以将key记录height，value记录同一个height出现的次数
        // PriorityQueue<Integer> queue1 = new PriorityQueue<>(Collections.reverseOrder());
        queue.put(0, 1);  // 初始化，即使并没有height为0的大楼，也为了代码方便设为已出现1次
        // queue1.add(0);
        int prevMaxHeight = 0; // 初始的max_height(配合笔记)设为0
        List<int[]> result = new ArrayList<>();
        for(BuildingPoint buildingPoint : buildingPoints) {
            // if it is start of building then add the height to TreeMap.
            // If height already exists then increment the value
            if (buildingPoint.isStart) {
                // Java8 Map接口新增加的特性：尝试去计算key=buildingPoint.height所对应的value，这里lambda定义的是：如果value不存在则为1，不然value++
                // map.compute(K key, BiFunction<自变量x1, 自变量x2, 因变量y> remappingFunction)
                // 也就是第二个参数remappingFunction就是一个输入两个自变量，返回一个因变量，这里实际上就只有一个自变量k，返回了它所对应的因变量value
                queue.compute(buildingPoint.height, (key, value) -> {
                    if (value != null) {
                        return value + 1;
                    }
                    return 1;
                });
//                // 上面的compute语句等价于：
//                Integer value = queue.get(buildingPoint.height);
//                if (value != null) {
//                    queue.put(buildingPoint.height, value+1);
//                } else {
//                    queue.put(buildingPoint.height, 1);
//                }
                //  queue1.add(cp.height);
            } else { // if it is end of building then decrement or remove the height from map.
                queue.compute(buildingPoint.height, (key, value) -> {
                    if (value == 1) {
                        return null;
                    }
                    return value - 1;
                });
//                // 上面的compute语句等价于：
//                Integer value = queue.get(buildingPoint.height);
//                if (value == 1) {
//                    queue.put(buildingPoint.height, null);
//                } else {
//                    queue.put(buildingPoint.height, value-1);
//                }
                // queue1.remove(cp.height);
            }
            // peek the current height after addition or removal of building x.
            int currentMaxHeight = queue.lastKey(); // TreeMap对加入其中的键值对自动按照key的值排序，默认为升序，所以这里直接取lastKey即为queue中最大的key值，也就是curr_max_height
            // int currentMaxHeight = queue1.peek();  // PriorityQueue也会根据元素的排序顺序决定出队的优先级
            // if height changes from previous height then this building x becomes critical x.
            // So add it to the result.
            if (prevMaxHeight != currentMaxHeight) { // 无论进queue还是出queue，只要完成该操作后，max_height发生变化，则该buildingPoint的x-coordinate和新的max_height分别作为x, y加入result
                result.add(new int[]{buildingPoint.x, currentMaxHeight});
                prevMaxHeight = currentMaxHeight;
            }
        }
        return result;
    }
}
