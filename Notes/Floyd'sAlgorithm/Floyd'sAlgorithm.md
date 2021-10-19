#### Floyd's Algorithm

+ Phase 1: tortoise 一步一node；hare 一步二nodes    $\implies$    两者在intersection相遇
+ Phase 2: ==因为intersection并不一定是the entrance of the cycle，所以还需要Phase 2== tortoise 回到起点一步一node；hare 待在intersection一步一node    $\implies$    两者在entrance相遇

*LeetCode:* [[0142]](#[0142] Linked List Cycle II)     [[0287]](#[0287] Find the Duplicate Number)

******

******

##### [0142] Linked List Cycle II

> Given a linked list, return the node where the cycle begins. If there is no cycle, return `null`. **Notice** that you **should not modify** the linked list.    

分别引自[[0141]](#[0141] Linked List Cycle)的两个方法

+ Solution 1: Use HashSet $\implies \mathcal{O}(n)\ \&\ \mathcal{O}(n)$​

  ```java
  public boolean hasCycle(ListNode head) {
    Set<ListNode> set = new HashSet<ListNode>();
  
    if (head == null) return false;
  
    while (head.next != null) {
      if (!set.contains(head)) {
        set.add(head);
        head = head.next;
      } else {
        return head;
      }
    }
  
    return false;
  }
  ```

+ Solution 2: Two-Pointer Scenario II / Floyd's Algorithm  $\implies \mathcal{O}(n)\ \&\ \mathcal{O}(1)$    ==**写得好**==

  ```java
  public boolean hasCycle(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;
    
    while (fast != null && fast.next != null) { // Phase I: Find the intersection
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) { // Phase II: Find the entrance
        slow = head; // 题目要求不能修改原list，不然这里可以直接让fast和head比即可
        while (fast != slow) {
          slow = slow.next;
          fast = fast.next;
        }
        return slow;
      }
    }
    
    return false;
  }
  ```

*****

##### [0287] Find the Duplicate Number

参考[[0142]](#[0142] Linked List Cycle II)

+ 如何将array转化为linked list：f(x) = nums[x]
+ Floyd's Algorithm: 
  + Phase 1: tortoise 一步一node；hare 一步二nodes    $\implies$    两者在intersection相遇
  + Phase 2: 因为intersection并不一定是the entrance of the cycle，所以还需要Phase 2: tortoise 回到起点一步一node；hare 待在intersection一步一node    $\implies$    两者在entrance相遇    

**********