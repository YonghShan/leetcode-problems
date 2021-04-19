/**
 * @author YonghShan
 * @date 2/10/21 - 23:56
 */

class Solution1 {
    // In-place: 逐位相加，考虑进位
    /* Runtime: 2ms      O(max(m,n)), where m is the length of list1 and n is the length of list 2
       Memory: 39MB      O(1)
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0; //
        ListNode p1 = l1;
        ListNode p2 = l2;
        //ListNode temp = new ListNode(0, null);

        while (p1 != null && p2 != null) {
            p2.val = p1.val + p2.val + carry;
            carry = p2.val / 10; // 进位是相加结果对10取商
            p2.val %= 10; // 更新后的val应当是相加结果对10取余                                      // 不能简单地赋为temp，会导致dead-lock
            if (p1.next == null && p2.next != null) p1.next = new ListNode(0, null); // list2表示的加数位数长于list1表示的加数，对list1进行扩展
            if (p1.next != null && p2.next == null) p2.next = new ListNode(0, null); // list1表示的加数位数长于list2表示的加数，对list2进行扩展
            if (p1.next == null && p2.next == null && carry != 0) { // 注意当最高位相加结果仍有进位的情况
                p2.next = new ListNode(carry, null);
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        return l2; // 因为是对list2做updates，保证in-place
    }
}