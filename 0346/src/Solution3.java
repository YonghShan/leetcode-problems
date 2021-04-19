/**
 * @author YonghShan
 * @date 2/21/21 - 21:53
 */
class MovingAverage {
    // With a Circular Queue: 不用担心越（Sliding Window的）界，只用更新首尾pointer即可
    /* Runtime: 42ms    O(1)
       Memory: 43.6MB   O(n)
     */
    int size, head = 0, windowSum = 0, count = 0;  // count完全可以由queue.size()代替，其time complexity为O(1)
    int[] queue;

    public MovingAverage(int size) {
        this.size = size;
        queue = new int[size];
    }

    public double next(int val) { // 因为val都是在head插入的，这里的tail实际上是旧的head；而且在第一轮（第一次插满sliding window）时，是从index=1开始插入的
        ++count;
        // calculate the new sum by shifting the window
        int tail = (head + 1) % size;
        windowSum = windowSum - queue[tail] + val;
        // move on to the next head
        head = (head + 1) % size;
        queue[head] = val;
        return windowSum * 1.0 / Math.min(size, count);
    }
}
