import java.util.ArrayList;

/**
 * @author YonghShan
 * @date 2/6/21 - 23:35
 */

class Solution1 {
    // 把Linked List的每个node放入一个ArrayList中，然后two pointers一前一后同时向中心边移动边比较val
    /* Runtime: 1ms       O(n)
       Memory: 43MB       O(n)
     */
    public boolean isPalindrome(ListNode head) {
        //if (head == null) return true; // 空Linked List视为回文

        ArrayList<ListNode> arr = new ArrayList<>();

        while (head != null) {
            arr.add(head);
            head = head.next;
        }

        int i = 0;
        int j = arr.size()-1;

        while (i < j) {
            if (arr.get(i).val != arr.get(j).val) return false; // Attention!!! 要明确比较的是val，不然会因为same val but diff next而return false
            i++;
            j--;
        }

        return true;
    }
}
