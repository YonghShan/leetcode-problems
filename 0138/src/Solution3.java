import java.util.HashMap;
import java.util.Map;

/**
 * @author YonghShan
 * @date 2/13/21 - 16:06
 */

class Solution3 {
    // Iteration with 1 extra space:
    // 复制出node放入原node后（原list和复制出来的list交织在一起）；再为复制出来的node设置random；最后为原node和复制出来的node重新分配next
    /* Runtime: 0ms   O(n)
       Memory: 38.6MB   O(1)
     */
    public Node copyRandomList(Node head) {
        if (head == null) return head;

        Node old_node = head;
        while (old_node != null) {
            Node new_node = new Node(old_node.val);
            new_node.next = old_node.next;
            old_node.next = new_node;
            old_node = new_node.next;
        }

        old_node = head;
        while (old_node != null) {
            old_node.next.random = (old_node.random != null) ? old_node.random.next : null;
            old_node = old_node.next.next;
        }

        old_node = head;
        Node new_node = head.next;
        Node p = new_node;
        while (p.next != null) {
            old_node.next = p.next;
            p.next = p.next.next;
            old_node = old_node.next;
            p = p.next;
        }
        old_node.next = null; // Attention! 循环结束时，old_node.next = p, 这样才彻底把原list和复制list解开

        return new_node;
    }
}