import java.util.LinkedList;
import java.util.Queue;

/**
 * @author YonghShan
 * @date 2/21/21 - 21:25
 */

class MovingAverage {
    // With Queue
    /* Runtime: 79ms   O(n) 每次next(val) 求和的时候，都要把queue的n个元素都过一遍
       Memory: 43.6MB  O(n)
     */

    /** Initialize your data structure here. */
    private int size;
    private double sum; // sum一定要为double，不然利用sum求出来的average不保留小数部分
    private Queue<Integer> q = new LinkedList<>();

    public MovingAverage(int size) {
        this.size = size;
    }

    public double next(int val) {
        sum = 0;
        q.offer(val);
        if (q.size() > size) q.poll();
        for (Integer i : q) {
            sum += i;
        }
        return sum / q.size();
    }
}