/**
 * @author YonghShan
 * @date 2/21/21 - 18:53
 */
public class MyCircularQueue2 {
    // 如果不想像1中，对p_start和p_end进行是否越界的判定，可以采用以下公式来更新：
    //    最后一位元素：tailIndex = (headIndex + count - 1) % capacity
    //    第一个空位，即1中的p_end = (headIndex + count) % capacity
    //    1中的p_start = headIndex = (headIndex + 1) % capacity
    /* Runtime: 4ms (faster than 97.68%)     O(1)
       Memory: 39.8MB (less than 30.03%)     O(n)
     */
    // store elements
    private int[] data;
    private int headIndex;
    private int count;
    private int capacity;

    public MyCircularQueue2(int k) {
        this.data = new int[k];
        this.headIndex = 0;
        this.count = 0;
        this.capacity = k;
    }

    public boolean enQueue(int value) {
        if (this.isFull() == false) {
            this.data[(this.headIndex + this.count) % this.capacity] = value;
            this.count++;
            return true;
        }
        return false;
    }

    public boolean deQueue() {
        if (this.isEmpty() == true) return false;
        this.headIndex = (this.headIndex + 1) % this.capacity;
        this.count--;
        return true;
    }

    public int Front() {
        if (this.isEmpty()) return -1;
        return this.data[this.headIndex];
    }

    public int Rear() {
        if (this.isEmpty()) return -1;
        int tailIndex = (this.headIndex + this.count - 1) % this.capacity;
        return this.data[tailIndex];
    }

    public boolean isEmpty() {
        if (this.count == 0) return true;
        return false;
    }

    public boolean isFull() {
        if (this.count == this.capacity) return true;
        return false;
    }
}