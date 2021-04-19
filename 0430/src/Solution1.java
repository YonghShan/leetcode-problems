
/**
 * @author YonghShan
 * @date 2/11/21 - 16:55
 */

class Solution1 {
    // Recursion
    /* Runtime: 0ms    O(n)
       Memory: 37MB    O(n)
     */
    public Node flatten(Node head) {
        if (head == null) return head;

        Node p1 = head;
        Node p2 = head.next;

        while (p1 != null) {
            if (p1.child != null) {
                Node p = flatten(p1.child);
                p1.next = p;
                p.prev = p1;
                p1.child = null;
                while (p.next != null) p = p.next; // p由child list的head变为tail
                p.next = p2; // 将child list的tail和原list的p2连接
                if (p2 != null) p2.prev = p; // 如果原list只有一个node（i.e. p1指向head，p2指向null）
            }
            if (p2 == null) { // 不能用p1.next来判断，此时p1.next指向原child list里的node
                break;
            } else {
                p1 = p2;
                p2 = p2.next;
            }
        }

        return head;
    }
}