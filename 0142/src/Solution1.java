import java.util.HashSet;
import java.util.Set;

/**
 * @author YonghShan
 * @date 2/3/21 - 22:20
 */

public class Solution1 {
    // 用一个HashSet去记录遍历该Linked List的结果，如果有重复的元素出现，则说明have a cycle。出现的第一个重复元素即为pos（tail.next）
    /* Runtime: 5ms   O(n)
       Memory: 43MB   O(n)
     */
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<ListNode>();

        if (head == null) return null;

        while (head.next != null) {
            if (!set.contains(head)) {
                set.add(head);
                head = head.next;
            } else {
                return head;
            }
        }

        return null;
    }
}

