/**
 * @author YonghShan
 * @date 3/2/21 - 21:32
 */
class MinStack3 {
    class Node {
        int val;
        int min;
        Node next;

        public Node(int v, int m, Node n) {
            this.val = v;
            this.min = m;
            this.next = n;
        }
    }

    Node head;
    int min;

    public MinStack3() {
        min = Integer.MAX_VALUE;
    }

    public void push(int x) {
        min = Math.min(min, x);
        head = new Node(x, min, head);
    }

    public void pop() {
        head = head.next;
        min = head != null ? head.min : Integer.MAX_VALUE;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }
}