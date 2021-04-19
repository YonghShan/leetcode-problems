import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author YonghShan
 * @date 2/21/21 - 21:41
 */
class MovingAverage {
    // With Queue （只是用deque来做queue，并没有用到deque两侧都可添加移除元素的特性），同时改变了求和的方法
    /* Runtime: 45ms    O(1) 求和时不用过一遍queue中存的所有元素
       Memory: 43.9MB   O(n)
     */
    int size, windowSum = 0;
    Deque queue = new ArrayDeque<Integer>();

    public MovingAverage(int size) {
        this.size = size;
    }

    public double next(int val) {
        // 并没有每次计算都将sum清零，而是判断是否越界，如果越界，则减去队首元素再加上新插入元素的值
        // calculate the new sum by shifting the window
        queue.add(val);
        int head = queue.size() > size ? (int)queue.poll() : 0; // 判断是非越界

        windowSum = windowSum - head + val;

        return windowSum * 1.0 / Math.min(size, queue.size());
    }
}