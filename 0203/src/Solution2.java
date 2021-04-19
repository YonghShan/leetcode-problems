/**
 * @author YonghShan
 * @date 2/6/21 - 16:42
 */

class Solution2 {
    // Two Pointers: like Problem 0027
    /* Runtime: 4ms    O(n)
       Memory:  40MB   O(1)
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;

        ListNode dummy = new ListNode(0, head);
        ListNode i = dummy;
        ListNode j = dummy;

        while (j.next != null) {
            if (j.next.val != val) {
                i.next = j.next;
                i = i.next;
            }
            j = j.next;
        }

        // 上面循环结束时，j指向tail，要再判断一下tail.val，如果==val，则i（循环结束时指向tail前面的node）指向null
        if (j.val == val) i.next = null;

        return dummy.next;
    }
}