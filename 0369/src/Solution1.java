/**
 * @author YonghShan
 * @date 6/29/21 - 17:04
 */
public class Solution1 {
    // 先reverse原linked list，加1后再reverse
    /* Runtime: 0ms        O(n)
       Memory: 36.6MB      O(1)
     */
    public ListNode plusOne(ListNode head) {
        if (head == null) return head;

        ListNode list = reverse(head);  // reverse原linked list，此时list为翻转后的list的head
        int carry = 1;  // carry初始为1，表示要对linked list加的1
        ListNode cur = list;
        while (cur != null && carry != 0) {  // 循环条件包括carry != 0 是为了无进位时提前结束循环
            int val = cur.val + carry;
            carry = val / 10;
            cur.val = val % 10;
            cur = cur.next;
        }

        if (carry > 0) head.next = new ListNode(carry);

        return reverse(list);
    }

    private ListNode reverse(ListNode node) {
        ListNode head = null;
        ListNode cur = node;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = head;
            head = cur;
            cur = next;
        }

        return head;
    }
}
