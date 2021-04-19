/**
 * @author YonghShan
 * @date 2/7/21 - 16:43
 */

class Solution3 {
    // In-place: reverse the second half
    //   Step 1: find the end of the first half;
    //   Step 2: reverse the second half;
    //   Step 3: determine whether or not there is a palindrome;
    //   Step 4: restore the list (optional);
    //   Step 5: return the result
    /* Runtime: 1ms       O(n)
       Memory: 43MB       O(1)
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;

        // Step 1:
        ListNode firstHalfEnd = findFirstHalfEnd(head); //虽然是把原list的head作为实参传了进去，但是function内部并没有影响到原list的nodes的排序
        // Step 2:
        ListNode secondHalfStart = reverseList(firstHalfEnd.next); // function只影响了原list后半段nodes的排序，对前半段无影响

        // Step 3:
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        // 不能用head != null作为循环判断的条件，因为原list的前半段的结尾并没有处理好，而后半段翻转后结尾有很好的处理
        // 并且，对于原list长度为奇的情况，middle node在后半段是故意忽略的，但是保留在了前半段中tail（i.e. 对于原list长度为奇时，前半段长度比后半段长度多1）
        while (p2 != null) {
            if (p1.val != p2.val) return false;
            p1 = p1.next;
            p2 = p2.next;
        }

        // Step 4: restore the list (optional)
        firstHalfEnd.next = reverseList(secondHalfStart);

        return true;
    }

    // Two Pointers: 当fast到tail时，slow正好在中间
    public ListNode findFirstHalfEnd(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    // Problem  [0206]
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode reversedHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return reversedHead;
    }
}