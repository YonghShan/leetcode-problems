/**
 * @author YonghShan
 * @date 2/13/21 - 21:15
 */

class Solution4 {
    // 缩小了k之后Solution1
    /* Runtime: 2ms      O(max(len,(k%len)) * (len-1)) = O(len * (len-1)) = O(len^2 - len) = O(len^2)
       Memory: 37.9MB    O(1)
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;

        ListNode new_head = head;

        ListNode p1 = head;
        int len = 1;
        while (p1.next != null) {
            p1 = p1.next;
            len++;
        }

        for (int i = 0; i < (k%len); i++) new_head = rotateRightByOne(new_head);

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