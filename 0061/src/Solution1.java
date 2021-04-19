
/**
 * @author YonghShan
 * @date 2/13/21 - 16:59
 */

class Solution1 {
    // 将向右旋转1位写成func，再根据k的值，决定调用func的次数
    /* Runtime： TLE   O(kn) (k最多取到450,000,000不TLE, 但k最大可达2*10^9)
       Memory: O(1)
     */
    public ListNode rotateRight(ListNode head, int k) {
        ListNode new_head = head;

        for (int i = 0; i < k; i++) new_head = rotateRightByOne(new_head);

        return new_head;
    }

    public ListNode  rotateRightByOne(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode p = head;

        while (p.next.next != null) p = p.next;
        // 循环结束时，p是list中倒数第二个node，因为rotate是要在倒数第一和倒数第二个node之间断开
        ListNode new_head = p.next;
        new_head.next = head;
        p.next = null;

        return  new_head;
    }
}