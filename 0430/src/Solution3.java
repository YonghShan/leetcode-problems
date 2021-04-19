import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author YonghShan
 * @date 2/12/21 - 16:53
 */

class Solution3 {
    // Iteration: 如果p既有child又有next，则将p.next放入stack保存，而将p.next重新改为p.child，并沿着child list继续向后遍历
    // 当child list遍历完后，再把child的tail.next定义为stack.pop() (i.e. 即之前保存的原p.next)
    /* Runtime: 0ms      O(n)
       Memory: 37MB      O(n)
     */
    public Node flatten(Node head) {
        if (head == null) return head;

        Node p = head;
        Deque<Node> stack = new ArrayDeque<>();
        Node curr = head;

        while (p != null) {
            if (p.child != null) {
                // i.e. p既有child又有next，则将p.next放入stack保存；如果只有child而next为null，则不需要保存next
                if (p.next != null) stack.push(p.next);
                // 改变p和p.child的联系
                p.next = p.child;
                p.child.prev = p;
                p.child = null;
            }

            if (p.next == null && !stack.isEmpty()) { // 当child list已经遍历结束，且上一层list仍有node存在stack中
                curr = stack.pop();
                // 将child.tail和上一层list的node建立联系
                p.next = curr;
                curr.prev = p;
                // 回到上一层list中继续遍历
                p = curr;
            } else {
                p = p.next;
            }
        }

        return head;
    }
}