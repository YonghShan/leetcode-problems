/**
 * @author YonghShan
 * @date 2/3/21 - 00:23
 */

class MyLinkedList1 {
    // Singly Linked List Solution 2
    private ListNode head;

    /** Initialize your data structure here. */
    public MyLinkedList1() {
        // 并没有定义size
        head = null; // 并没有将森停作为pseudo-head
    }

    /** Helper function to return the index-th node in the linked list. */
    private ListNode getNode(int index) {
        ListNode cur = head;
        for (int i = 0; i < index && cur != null; ++i) {
            cur = cur.next;
        }
        return cur;
    }

    /** Helper function to return the last node in the linked list. */
    private ListNode getTail() {
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            cur = cur.next;
        }
        return cur;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        ListNode cur = getNode(index);
        return cur == null ? -1 : cur.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        ListNode cur = new ListNode(val);
        cur.next = head;    // No sentinel!!!
        head = cur;
        return;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        if (head == null) { // 此时，List全空，在末尾加就是在开头加
            addAtHead(val);
            return;
        }
        ListNode prev = getTail();
        ListNode cur = new ListNode(val);
        prev.next = cur;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index == 0) {
            addAtHead(val);
            return;
        }
        ListNode prev = getNode(index - 1);
        if (prev == null) { // index is greater than the length（如果length是2，index=3就在尾部append一个；但是如果index=4（prev=3=null），那就加不了）
            return;
        }
        ListNode cur = new ListNode(val);
        ListNode next = prev.next;
        cur.next = next;
        prev.next = cur;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        ListNode cur = getNode(index);
        if (cur == null) {
            return;
        }
        ListNode prev = getNode(index - 1);
        ListNode next = cur.next;
        if (prev != null) {
            prev.next = next;
        } else {
            // modify head when deleting the first node.
            head = next;
        }
    }
}

