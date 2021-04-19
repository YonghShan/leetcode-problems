/**
 * @author YonghShan
 * @date 2/3/21 - 23:27
 */
public class Solution2 {
    // Two Pointers: Floyd's Tortoise(pointer slow) and Hare(pointer fast)
    // Phase 1: find the intersection (where fast == slow);
    // Phase 2: find the entrance with the intersection found.
    //          With the pointer ptr1 pointing to the head of list and the pointer ptr2 pointing to the intersection,
    //          advance them by one step per iteration simultaneously until they meet each other at the entrance.
    /* Runtime: 0ms   O(n)
       Memory: 39MB   O(1)
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode ptr1 = head;
        ListNode ptr2 = head;
        boolean isCyclic = false;

        // Phase 1: Find the intersection
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next; // fast.next不为null，则fast.next一定有next（fast.next.next可能为其他ListNode，也可能为null）
            if (slow == fast) {
                isCyclic = true;
                ptr2 = slow;
                break;
            }
        }

        // Phase 2: Find the entrance
        while (ptr1 != null && ptr1.next != null && ptr1 != ptr2) { // 有可能entrance和intersection都为head（e.g. [1, 2] at index = 0），就不用进循环
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        return !isCyclic ? null : ptr1;
    }
}
