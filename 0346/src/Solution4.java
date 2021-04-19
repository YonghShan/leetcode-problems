import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 2/21/21 - 22:05
 */
class MovingAverage {
    // With Array / List
    /* Runtime: 69ms     O(n)
       Memory: 43.8MB    O(m), m可能>n, 因为list并没有清理不要的数据
     */
    int size;
    List queue = new ArrayList<Integer>();

    public MovingAverage(int size) {
        this.size = size;
    }

    public double next(int val) {
        queue.add(val);
        // calculate the sum of the moving window
        int windowSum = 0;
        for(int i = Math.max(0, queue.size() - size); i < queue.size(); ++i) // for循环计算sum，但是循环条件不一样
            windowSum += (int)queue.get(i);

        return windowSum * 1.0 / Math.min(queue.size(), size);
    }
}