import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author YonghShan
 * @date 2/5/21 - 00:55
 */

public class Solution3 {
    // One Pass & One Pointer: First, use a HashMap to store the nodes in the list; Second, do the operation to the target node in the HashSet
    /* Runtime: 1ms     O(L), where L is the length of the list
       Memory:  38MB    O(L)
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        Map<Integer, ListNode> map = new HashMap<>();
        int key = 0;
        ListNode p = head;
        int len = 0;

        while (p != null) {
            map.put(key++, p);
            p = p.next;
            len++;
        }

        //len = map.size(); // 会增加Runtime至3ms

        if (len == n) {
            // Case 1: 要删去的node是head
            head = head.next;
        } else { // 必须写在else里：当len==n时，len-n-1抛NullPointerException
            // Case 2：要删去的node是原list的中间node或tail
            map.get(len-n-1).next = map.get(len-n).next;
        }

        return head;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        Map<Integer, ListNode> map = new HashMap<>();
        int key = -1;
        ListNode dummy = new ListNode(0, head);
        ListNode p =dummy;
        int len = 0;

        while (p != null) {
            map.put(key++, p);
            p = p.next;
            len++;
        }

        map.get(len-n-2).next = map.get(len-n-1).next;

        return dummy.next;
    }
}

