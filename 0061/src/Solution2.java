/**
 * @author YonghShan
 * @date 2/13/21 - 20:22
 */

class Solution2 {
    // Recursion: 先得到rotateRight(head, k-1)，再进行右转一次
    /* Runtime: O((k-1) * (n-1)) = O(kn)
       Memory: StackOverflowError   O(k-1) = O(k) (k最多取到3,100,000不报错, 但k最大可达2*10^9)
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;

        ListNode headBeforeK = rotateRight(head, k-1);
        ListNode p = headBeforeK;
        while (p.next.next != null) p = p.next;
        ListNode new_head = p.next;
        new_head.next = headBeforeK;
        p.next = null;

        return  new_head;
    }
}