/**
 * @author YonghShan
 * @date 2/4/21 - 00:07
 */
public class Solution2Abbr {
    /* Runtime: 0ms (faster than 100%)      O(n)
       Memory: 38.5MB (less than 98.34%)    O(1)
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {  // Phase 2
                while (fast != head) {   // 与其让tortoise（slow）回到起点，不如直接用起点（head）
                    fast = fast.next;
                    head = head.next;
                }
                return fast;
            }
        }

        return null;
    }
}
