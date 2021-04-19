import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 2/21/21 - 16:52
 */
public class MyCircularQueue1 {
    // 1. p_end指向空位，不指向最后一个元素；
    // 2. Rear()直接返回data[p_end-1]
    // 3. 定义count来表示当前queue中元素数量，以此来判断queue是否empty或full
    /* Runtime: 4ms (faster than 97.68%)     O(1)
       Memory: 39.8MB (less than 30.03%)     O(n)
     */
    // store elements
    private int[] data;
    private int p_start;
    private int p_end; // p_end表示的是queue中第一个空位的index，不可能等于data.length
    private int count; // count表示的是数量，可以等于data.length

    public MyCircularQueue1(int k) {
        data = new int[k];
        p_start = 0;
        p_end = 0;
        count = 0;
    }

    public boolean enQueue(int value) {
        if (isFull() == false) {
            data[p_end] = value;
            p_end++;
            if (p_end == data.length) {
                p_end = 0;
            }
            count++;
            return true;
        }
        return false;
    }

    public boolean deQueue() {
        if (isEmpty() == true) return false;
        p_start++;
        if (p_start == data.length) {
            p_start = 0;
        }
        count--;
        return true;
    }

    public int Front() {
        if (isEmpty()) return -1;
        return data[p_start];
    }

    public int Rear() {
        if (isEmpty()) return -1;
        // queue不为空的情况下，p_end=0表示queue中最后一位元素在队尾
        if (p_end == 0) return data[data.length-1];
        return data[p_end-1];
    }

    public boolean isEmpty() {
        if (count == 0) return true;
        return false;
    }

    public boolean isFull() {
        if (count == data.length) return true;
        return false;
    }
}