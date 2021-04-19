import java.util.LinkedList;
import java.util.Queue;

/**
 * @author YonghShan
 * @date 3/7/21 - 00:19
 */
class MyStack {
    // Two queues: 遇到pop之前，都往q1中存；遇到pop，把q1前n-1个元素放在q2中，剩下的最后一个即为要pop的元素；再将空的q1和q2对换，始终保持q2为空
    /* Runtime: 0ms     push - O(1), pop O(n)
       memory: 36.9MB   O(n)
     */
    Queue<Integer> q1;
    Queue<Integer> q2;

    /** Initialize your data structure here. */
    public MyStack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        q1.add(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        int size = q1.size();
        for (int i = 0; i < size-1; i++) q2.add(q1.remove());
        int res = q1.remove();
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
        return res;
    }

    /** Get the top element. */
    public int top() {
        int size = q1.size();
        for (int i = 0; i < size-1; i++) q2.add(q1.remove());
        int top = q1.peek();
        q2.add(q1.remove());
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
        return top;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q1.isEmpty();
    }
}

