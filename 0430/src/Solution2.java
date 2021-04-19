/**
 * @author YonghShan
 * @date 2/12/21 - 16:20
 */

class Solution2 {
    // Recursion: very concise code
    Node tail = null;
    public Node flatten(Node head) {
        if(head == null) return null;

        head.prev = tail;
        tail = head;

        Node nextNode = head.next;

        head.next = flatten(head.child);
        head.child = null;

        tail.next = flatten(nextNode);

        return head;
    }
}
