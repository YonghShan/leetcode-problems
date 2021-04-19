/**
 * @author YonghShan
 * @date 2/3/21 - 16:12
 */

import java.util.HashSet;
import java.util.Set;

public class Solution1 {
    // 用一个HashSet去记录遍历该Linked List的结果，如果有重复的元素出现，则说明have a cycle。出现的第一个重复元素即为pos（tail.next）
    /* Runtime: 9ms   O(n)
       Memory: 43MB   O(n)
     */
    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<ListNode>();

        if (head == null) return false;

        while (head.next != null) {
            if (!set.contains(head)) {
                set.add(head);
                head = head.next;
            } else {
                return true;
            }
        }

        return false;
    }
}
