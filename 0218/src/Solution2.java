import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author YonghShan
 * @date 3/23/21 - 14:53
 */
public class Solution2 {
    // Divide & Conquer: exactly the same as Merge Sort Algorithm
    /* Runtime: 6ms (faster than 97.51%)   O(nlogn)   Master Theorem Case II: T(n) = 2T(n/2) + 2n
       Memory: 42.9MB (less than 37.33%)   O(n)
     */
    public List<List<Integer>> getSkyline(int[][] buildings) {
        int len = buildings.length;
        if (len == 0) return new ArrayList<>();
        return divideConquer(buildings, 0, len - 1);
    }

    private List<List<Integer>> divideConquer(int[][] buildings, int l, int r) {
        // 创建返回值
        List<List<Integer>> res = new ArrayList<>();

        // 找到树底下的结束条件 -> 一个建筑物
        if (l == r) {
            res.add(Arrays.asList(buildings[l][0], buildings[l][2])); // 左上端坐标
            res.add(Arrays.asList(buildings[l][1], 0)); // 右下端坐标   ATTENTION！！！和Sweep Line Algorithm不同，这里记录右下端而不是右上端
            return res;
        }

        int mid = l + (r - l) / 2; // 取中间值
        // int mid = (start + end) >>> 1;

        // 左边递归
        List<List<Integer>> leftSkyline = divideConquer(buildings, l, mid);

        // 右边递归
        List<List<Integer>> rightSkyline = divideConquer(buildings, mid + 1, r);

        // 左右合并

        // 创建leftSkyline 和 rightSkyline 的索引位置
        int i = 0, j = 0;
        // 创建leftSkyline 和 rightSkyline 目前的高度
        int h1 = 0, h2 = 0;
        while (i < leftSkyline.size() || j < rightSkyline.size()) {
            // 这里这么对leftX和rightX赋值的原因：
            // 当有一方Skyline遍历完了，则另一方还剩下的Skyline直接加入结果，所以当一方遍历完的时候，我们给他赋值为一个很大的数，
            // 这样的话我们可以在一个while循环中完成我们的算法，不用再单独考虑当一个遍历完的处理。i.e. if (i >= leftSkyline .size()){}
            // 将leftX和rightX定义为long，可以保证我们给leftX或者rightX赋的Long.MAX_VALUE这个值，后续不会出现leftX==rightX。因为原始数据都是int范围的。
            long leftX = i < leftSkyline .size() ? leftSkyline .get(i).get(0) : Long.MAX_VALUE;
            long rightX = j < rightSkyline .size() ? rightSkyline .get(j).get(0) : Long.MAX_VALUE;
            long x = 0;

            if (leftX < rightX) {
                h1 = leftSkyline .get(i).get(1);
                x = leftX;
                i++;
            } else if (leftX > rightX) {
                h2 = rightSkyline .get(j).get(1);
                x = rightX;
                j++;
            } else { // leftX 和 rightX 的横坐标相等
                h1 = leftSkyline .get(i).get(1);
                h2 = rightSkyline .get(j).get(1);
                x = leftX;
                i++;
                j++;
            }
            //更新 height
            int height = Math.max(h1, h2);
            //重复的解不要加入：Skyline的绘制所需要记录的点是使得原本水平的移动改为竖直（向上/下）移动的点，也就是每个关键点都与前一个关键点的高度不同
            if (res.isEmpty() || height != res.get(res.size() - 1).get(1)) {
                res.add(Arrays.asList((int) x, height));
            }
        }
        return res;
    }
}
