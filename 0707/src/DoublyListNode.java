/**
 * @author YonghShan
 * @date 2/7/21 - 23:20
 */
public class DoublyListNode {
    int val;
    DoublyListNode prev;
    DoublyListNode next;
    DoublyListNode() {}
    DoublyListNode(int val) {this.val = val;}
    DoublyListNode(int val, DoublyListNode prev, DoublyListNode next) {this.val = val; this.prev = prev; this.next = next;}
}
