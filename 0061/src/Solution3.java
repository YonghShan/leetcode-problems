
/**
 * @author YonghShan
 * @date 2/13/21 - 20:57
 */

class Solution3 {
    // Recursion: 缩小了k之后Solution2
    /* Runtime: 1ms ~ 2ms    O(len + ((k%len)-1) * (len-1)) <= O(len + (len-1) * (len-1)) = O(len^2 - len + 1) = O(len^2)
       Memory: 40.3MB   O((k%len) - 1) <= O(len-1) = O(len)
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;

        ListNode p1 = head;
        int len = 1;
        while (p1.next != null) {
            p1 = p1.next;
            len++;
        }

        ListNode headBeforeK = rotateRight(head, (k-1)%len);
        ListNode p = headBeforeK;
        while (p.next.next != null) p = p.next;
        ListNode new_head = p.next;
        new_head.next = headBeforeK;
        p.next = null;

        return  new_head;
    }
}