import java.util.LinkedList;
import java.util.Queue;

/**
 * @author YonghShan
 * @date 3/7/21 - 00:37
 */

class MyStack2 {
    // Two queues: push的值先放在q2，再把q1的内容都append在q2后，对换q1和q2，保持q2为空
    /* Runtime: push - O(n)O, pop O(1)
       memory:  O(n)
     */
    Queue<Integer> q1;
    Queue<Integer> q2;
    int top;

    /** Initialize your data structure here. */
    public MyStack2() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        q2.add(x);
        top = x;
        while (!q1.isEmpty()) {
            q2.add(q1.remove());
        }
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        int res = q1.remove();
        if (!q1.isEmpty()) {
            top = q1.peek();
        }
        return res;
    }

    /** Get the top element. */
    public int top() {
        return top;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q1.isEmpty();
    }
}
