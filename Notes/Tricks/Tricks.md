### Tricks

##### [0160] Intersection of Two Linked Lists

Given the heads of two singly linked-lists `headA` and `headB`, return *the node at which the two lists intersect*. If the two linked lists have no intersection at all, return `null`.

+ Solution 1: Nested Loop: 将List A中的Node逐个和List B中的Node比较 $\implies \mathcal{O}(n·m)\ \&\ \mathcal{O}(1)$​​​​

  ```java
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) return null;
    
    while (headB != null) {
      ListNode p = headA;
      while (p != null) {
        if (p == headB) return p;
        p = p.next;
      }
      headB = headB.next;
    }
    
    return null;
  }
  ```

+ Solution 2: Use HashSet存储List A中的Node，再与List B中的Node比较 $\implies \mathcal{O}(n+m)\ \&\ \mathcal{O}(n)$​

  ```java
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) return null;
    
    Set<ListNode> set = new HashSet<>();
    while (headA != null) {
      set.add(headA);
      headA = headA.null;
    }
    
    while (headB != null) {
      if (set.contains(headB)) return headB;
      headB = headB.next;
    }
    
    return null;
  }
  ```

+ Solution 3: 同时遍历两条Lists，当本List遍历结束时，将pointer置为另一条List的head $\implies \mathcal{O}(\le(n+m))\ \&\ \mathcal{O}(1)$​​​​​   ==**巧妙**==

  ```java
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
  	ListNode p1 = headA;
    ListNdoe p2 = headB;
    
    while (p1 != p2) {
      p1 = (p1 != null) ? p1.next : headB;
      p2 = (p2 != null) ? p2.next : headA;
    }
    
    return p1;
  }
  ```

+ Solution 4: Pointer 1从List A第0个Node开始，Pointer 2从List B第m-n个Node开始，在经过n个iterations后两者在intersection相遇 $\implies \mathcal{O}(\le min(n,m))\ \&\ \mathcal{O}(1)$​​​   ==**更巧妙**==

  <img src="/Users/shanyonghao/IdeaProjects/LeetCodeProblems/Notes_img/[0160].png" style="zoom:50%;" />

  ```java
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    ListNode p1 = headA;
    ListNdoe p2 = headB;
    
    // To reset the beginning of headA or headB
    // Really Clever Trick!!!
    while (p1 != null || p2 != null) {
      if (p1 != null) 
        p1 = p1.next;
      else 
        headB = headB.next;
      if (p2 != null)
        p2 = p2.next;
      else 
        headA = headA.next;
    }
    
    while (headA != headB) {
      headA = headA.next;
      headB = headB.next;
    }
    
    return headA;
  }
  ```

  ==注意：== 这个方法，除了思路巧妙以外，如何找到两条Lists遍历的起点也非常巧妙。如果只是简单地利用while循环分别遍历两条Lists记录各种的长度，最终的TC仍为 $\mathcal{O}(n+m)$:

  ```java
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    int lenA = getLength(headA);
    int lenB = getLength(headB);
    int diff = lenA > lenB ? lenA - lenB : lenB - lenA;
  
    // To reset the beginning of headA or headB
    for (int i = 0; i < diff; i++) {
      if (lenA > lenB)
        headA = headA.next;
      else
        headB = headB.next;
    }
  
    ...
  }
  ```

  ```java
  public int getLength(ListNode head) {
    int len = 0;
  
    while (head != null) {
      len++;
      head = head.next;
    }
  
    return len;
  }
  ```

********

##### [0189] Rotate Array

Given an array, rotate the array to the right by `k` steps, where `k` is non-negative.

+ Solution 1: Brute Force $\implies \mathcal{O}(n ·k)$

  ```java
  public void rotate(int[] nums, int k) {
      if (k == 0) return;
      k %= nums.length;  // in case that k > nums.length
  
      int len = nums.length;
      int temp;
      for (int i = 0; i < k; i++) {  // O(nk)
          temp = nums[len-1];
          for (int j = len-1; j > 0; j--) nums[j] = nums[j-1];
          nums[0] = temp;
      }
  }
  ```

+ Solution 2: Using Extra Space $\implies \mathcal{O}(n)$

  ```java
  public void rotate(int[] nums, int k) {
      int[] a = new int[nums.length];
      for (int i = 0; i < nums.length; i++) {
          a[(i + k) % nums.length] = nums[i];  // 这么写是因为无论是(i+k)还是(i+k%nums.length)都有可能越界
      }
      for (int i = 0; i < nums.length; i++) {
          nums[i] = a[i];
      }
  }
  ```

+ Solution 3: Using Cyclic Replacements $\implies \mathcal{O}(n)$

  我们从位置 $0$​​ 开始，最初令 $\textit{temp}=\textit{nums}[0]$​​。根据规则，位置 $0$​​ 的元素会放至 $(0+k)\bmod n$​​ 的位置，令 $x=(0+k)\bmod n$​​，此时交换 $\textit{temp}$​​ 和 $\textit{nums}[x]$​​，完成位置 $x$​​ 的更新。然后，我们考察位置 $x$​​ (即，原本放在 $x$​ 的值的新位置在 $(x+k) \bmod n$​ )，并交换 $\textit{temp}$​ 和 $\textit{nums}[(x+k)\bmod n]$​，从而完成下一个位置的更新。不断进行上述过程，直至回到初始位置 $0$​​​。如果还有元素未被移动（$nums.length$ 为偶数），则移至下一个未移动的元素，重复上述过程。

  这里回到初始位置有两种可能：

  + $nums.length$ 为奇数（无论 $k$​ 为奇还是偶数）：当回到初始位置时，数组中所有的元素都已重构完毕。

    <img src="/Users/shanyonghao/IdeaProjects/LeetCodeProblems/Notes_img/[0189]_1.png" style="zoom:50%;" />

  + $nums.length$ 为偶数（无论 $k$ 为奇还是偶数）：当回到初始位置时，数组中的元素并没有全部重构完毕。

    <img src="/Users/shanyonghao/IdeaProjects/LeetCodeProblems/Notes_img/[0189]_2.png" style="zoom:50%;" />

  因此，还需要在更新时，记录已经被移动过的元素的个数 `count` ，当 `count==nums.length` 时，重构结束。

  ```java
  public void rotate(int[] nums, int k) {
      k = k % nums.length;
      int count = 0;
    	// nums.length为奇数：for循环1次，do-while循环nums.length次；
    	// nums.length为偶数：for循环a次，do-while循环b次 ==>> ab = nums.length
      for (int start = 0; count < nums.length; start++) { 
        	int current = start; // 即上述文字表述中的x
        	int prev = nums[start]; 
        	do {
          		int next = (current + k) % nums.length;
          		int temp = nums[next];
          		nums[next] = prev;
          		prev = temp;
          		current = next;
          		count++;
        	} while (start != current); // 终止条件：回到初始位置
      }
  }
  ```

+ Solution 4: Using Reverse $\implies \mathcal{O}(n)$​    ==实际运行速度最快==

  For example: Let $n=7$ and $k=3$​.

  |             Original List              | $[1,2,3,4,5,6,7]$ |
  | :------------------------------------: | :---------------: |
  |    **After reversing all numbers**     | $[7,6,5,4,3,2,1]$ |
  | **After reversing first $k$ numbers**  | $[5,6,7,4,3,2,1]$ |
  | **After reversing last $n-k$ numbers** | $[5,6,7,1,2,3,4]$ |

  ```java
  public void rotate(int[] nums, int k) {
      k %= nums.length;
      reverse(nums, 0, nums.length - 1);
      reverse(nums, 0, k - 1);
      reverse(nums, k, nums.length - 1);
  }
    
  public void reverse(int[] nums, int start, int end) {
      while (start < end) {
        	int temp = nums[start];
        	nums[start] = nums[end];
        	nums[end] = temp;
        	start++;
        	end--;
      }
  }
  ```

********


*****

### 