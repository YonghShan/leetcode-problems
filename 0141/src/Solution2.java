/**
 * @author YonghShan
 * @date 2/3/21 - 16:17
 */

public class Solution2 {
    // Two Pointers: once the pointer fast equals to the pointer slow, it denotes a cycle exists. If the fast reaches the end, no cycle.
    /* Runtime: 0ms   O(n)
       Memory: 40MB   O(1)
     */
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        if (head == null || head.next == null) return false;

        while (fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == null || fast.next == null) return false;
            if (fast == slow) return true;
        }

//        // 上面的if和while可以合并为：
//        while (fast != null && fast.next != null) {
//            slow = slow.next;
//            fast = fast.next.next; // fast.next不为null，则fast.next一定有next（fast.next.next可能为其他ListNode，也可能为null）
//            if (slow == fast) {
//                return true;
//            }
//        }

        return false;
    }
}
