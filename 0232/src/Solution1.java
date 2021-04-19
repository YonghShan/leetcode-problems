import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author YonghShan
 * @date 3/6/21 - 23:11
 */
class MyQueue {
    // 遇到pop操作之前，全都push进stack1中；遇到第一次pop操作时，将stack1中的所有元素pop进stack2中
    /* Runtime: 0ms (faster than 100%)     Push - O(1) per operation, Pop - Amortized O(1) per operation.
       Memory: 38.8MB (less than 5.15%)    O(n), where n refers to the total number of int ever pushed into MyQueue
     */
    Deque<Integer> stack1;
    Deque<Integer> stack2;
    /** Initialize your data structure here. */
    public MyQueue() {
        stack1 = new ArrayDeque<>();
        stack2 = new ArrayDeque<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        stack1.addFirst(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (!stack2.isEmpty()) return stack2.poll();
        int size = stack1.size(); // 下面的for循环不能直接i<stack1.size()，因为循环体中涉及stack1的操作
        for (int i = 0; i < size; i++) stack2.addFirst(stack1.poll());
        return stack2.poll();
    }

    /** Get the front element. */
    public int peek() {
        if (!stack2.isEmpty()) return stack2.peek();
        int size = stack1.size();
        for (int i = 0; i < size; i++) stack2.addFirst(stack1.poll());
        return stack2.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}