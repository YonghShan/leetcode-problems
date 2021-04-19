
/**
 * @author YonghShan
 * @date 2/6/21 - 17:41
 */

class Solution1 {
    // After one iteration, get two sublist: one is odd list and another is even list.
    /* Runtime: 0ms      O(n)
       memory:  38.5MB   O(1)
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null) return null;  // 对应原list长度为0
        if (head.next == null || head.next.next == null) return head; // 对应原list长度为1或2

        ListNode odd = head;
        ListNode even = head.next;
        ListNode p = even;

        while (p.next != null) {
            odd.next = odd.next.next;
            odd = odd.next;
            p.next = p.next.next;
            p = p.next;
            if (p == null) break;
        }
        // 循环结束时，无论原list的长度为奇或偶，odd一定指向odd sublist的tail。
        // 而当原list长度为奇时，p指向null；当为偶时，p指向原list的tail（也就是null前面的一位）

        odd.next = even; // Append the even sublist at the end of the odd sublist.

        return head;
    }
}