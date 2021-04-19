import java.util.LinkedList;

/**
 * @author YonghShan
 * @date 3/7/21 - 00:47
 */
public class Solution3 {
    // One queue: 每次push进一个元素，就把queue中的所有元素reverse
    /* Runtime: 0ms     push - O(n), pop O(1)
       Memory: 36.9MB   O(n)
     */
    private LinkedList<Integer> q1;

    /** Initialize your data structure here. */
    public Solution3() {
        q1 = new LinkedList<>();
    }

    // Push element x onto stack.
    public void push(int x) {
        q1.add(x);
        int sz = q1.size();
        while (sz > 1) {
            q1.add(q1.remove());
            sz--;
        }
    }

    // Removes the element on top of the stack.
    public int pop() {
        return q1.remove();
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return q1.isEmpty();
    }

    // Get the top element.
    public int top() {
        return q1.peek();
    }
}
