import java.util.Stack;

/**
 * @author YonghShan
 * @date 3/7/21 - 00:04
 */
public class Solution2 {
    // Push - O(n) per operation, Pop - O(1) per operation.
    private Stack<Integer> s1 = new Stack<>();
    private Stack<Integer> s2 = new Stack<>();
    private int front;

    public void push(int x) {
        if (s1.empty())
            front = x;
        while (!s1.isEmpty())
            s2.push(s1.pop());
        s2.push(x);
        while (!s2.isEmpty())
            s1.push(s2.pop());
    }

    // Removes the element from the front of queue.
    public void pop() {
        s1.pop();
        if (!s1.empty())
            front = s1.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return s1.isEmpty();
    }

    // Get the front element.
    public int peek() {
        return front;
    }
}
