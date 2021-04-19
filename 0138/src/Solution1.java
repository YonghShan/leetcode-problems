import java.util.HashMap;
import java.util.Map;

/**
 * @author YonghShan
 * @date 2/12/21 - 23:43
 */

class Solution1 {
    // Recursion: 复制node的时候： 第一步先new node，第二步对old node的两个child (next, random) 进行recursion
    /* Runtime：0ms    O(n)
       Memory：39.2MB  O(n)
     */
    // Key node is the old node, Value node is the new node
    // 不能建在copyRandomList中，不然每次recursion都会重新建一个新的visitedHistory
    Map<Node, Node> visitedHistory = new HashMap<Node, Node>();

    public Node copyRandomList(Node head) {
        if (head == null) return head;

        // The old node head has been copied once and stored in the visitedHistory
        if (visitedHistory.containsKey(head)) return visitedHistory.get(head);

        // The old node head hasn't been copied,
        // and now we need to copy it and set the next and random field for the copied node.
        Node new_node = new Node(head.val);
        // Store this new copied node for the later reference
        // put行为必须放在这里，且不会引起Memory Limit Exceeded
        visitedHistory.put(head, new_node);
        new_node.next = copyRandomList(head.next);
        new_node.random = copyRandomList(head.random);
//        // 直觉上应该是new_node的next和random都设置好后才将其put进visitedHistory
//        // 但是这样做，将会导致重复new node，比如，当new_node.next = copyRandomList(head.next)运行结束，相当于所有复制node的next都串联好了
//        // 此时，要从tail往回依次设置random （new_node.random = copyRandomList(head.random);），
//        // 而因为此时visitedHistory是空的，导致明明原list中所有node都已经复制过一遍了，此时却要再重新new node，
//        // 更麻烦的是，当new了node后又要为这个node设置next，导致无限循环，最终引起Memory Limit Exceeded
//        visitedHistory.put(head, new_node);

        return new_node;
    }
}