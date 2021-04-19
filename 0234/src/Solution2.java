import java.util.ArrayList;

/**
 * @author YonghShan
 * @date 2/7/21 - 01:09
 */


class Solution2 {
    // Recursion:
    //  1. 一个front、一个curr指针初始都指向head；
    //  2. 利用recursion的方式将curr送到tail:
    //          function print_values_in_reverse(ListNode head):
    //              if head is NOT null:
    //                  print_values_in_reverse(head.next);
    //                  print head.val;
    //  3. recursion回收时比较front和curr的val
    /* Runtime: 1ms       O(n)
       Memory: 45MB       O(n)
     */
    private ListNode front;

    public boolean isPalindrome(ListNode head) {
        front = head;
        return recursivelyCheck(head);
    }

    public boolean recursivelyCheck(ListNode curr) {
        if (curr != null) {
            if (!recursivelyCheck(curr.next)) return false;
            if (curr.val != front.val) return false;
            front = front.next; // 只有这一回合的curr等于front才有必要把front右移和下一回合左移后的curr继续比较
        }
        return true;
    }
}