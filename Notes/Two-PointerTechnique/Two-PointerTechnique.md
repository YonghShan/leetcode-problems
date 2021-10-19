### Two-Pointer Technique

#### ***Scenario I***

To summarize, one of the typical scenarios to use two-pointer technique is that you want to

> Iterate the array from two ends to the middle.

So you can use the two-pointer technique:

> One pointer starts from the beginning while the other pointer starts from the end.

And it is worth noting that this technique is often used in a ==sorted== array.

==看到***sorted*** array，不仅需要考虑Binary Search，还可以考虑Two-Pointer Technique.== 

*LeetCode:*[[0167]](#[0167] Two Sum II - Input array is sorted)    [[0344]](#[0344] Reverse String)   [0349]    [0350]

********

#### ***Scenario II***

This is a very common scenario of using the two-pointer technique when you need:

> One slow-runner and one fast-runner at the same time.

The key to solving this kind of problems is to

> Determine the movement strategy for both pointers.

Similar to the previous scenario, you might sometimes need to **sort** the array before using the two-pointer technique. And you might need a **greedy** thought to determine your movement strategy.

*LeetCode:* [[0027]](#[0027] Remove Element)

==补充：== Two-Pointer Technique Scenario II 还可以应用于以下问题：

+ Slow-pointer and fast-pointer problem in Linked List: [[0141]](#[0141] Linked List Cycle)     [[0160]](#[0160] Intersection of Two Linked Lists)     [[0019]](#[0019] Remove Nth Node From End of List)
  + Floyd's Algorithm: [[0142]](#[0142] Linked List Cycle II) 
+ Sliding Window Problem: [[0209]](#[0209] Minimum Size Subarray Sum) 

******

********

##### [0019] Remove Nth Node From End of List



********

##### [0026] Remove Duplicates from Sorted Array

> Given an integer array `nums` sorted in **non-decreasing order**, remove the duplicates [**in-place**](https://en.wikipedia.org/wiki/In-place_algorithm) such that each unique element appears only **once**. The **relative order** of the elements should be kept the **same**. Return `k` *after placing the final result in the first* `k` *slots of* `nums`.

+ Solution 1: Brute Force $\implies \mathcal{O}(n^2)$

  ```java
  public int removeDuplicates(int[] nums) {
    int len = nums.length;
  
    for (int i = 1; i < len; i++) {
      if (nums[i] == nums[i-1]) {
        for (int j = i; j<len-1; j++) nums[j] = nums[j+1];
        len--;
        i--;
      }
    }
    return len;
  }
  ```

+ Solution 2: Two-Pointer $\implies \mathcal{O}(n)$​    ==**写得好**==

  ```java
  public int removeDuplicates(int[] nums) {
    int len = nums.length;
    int i = 0;
    
    for (int j = 1; j < len; j++) {
      if (nums[j] != nums[i]) nums[++i] = nums[j];
    }
    
    // 当上面的循环结束时，此时的第i+1位元素是一串重复字符nums[i+1...len-1]的首位，所以第i+1位元素也要算在内
    return i+1;
  }
  ```

******

##### [0027] Remove Element

 将数组 `nums` 中所有等于 `val` 的元素移走（in-place），返回新数组的长度。The relative order of the elements may be changed.

+ Solution 1: Compare the elements of array with the reference value from the end of array. If equal, all the elements behind this elements left shift by one. $\implies \mathcal{O}(n^2)$

  ```java
  public int removeElement(int[] nums, int val) {
    int len = nums.length;
  
    for (int i = len-1; i>=0; i--) {
      if (nums[i] == val) {
        for (int j = i+1; j<len; j++) {
          nums[j-1] =nums[j];
        }
        len--;
      }
    }
    return len;
  }
  ```

+ Solution 2: Two-Pointer Technique: the elements before pointer `i` must be safe (not equal to val), so `i` is the length of the result array. Pointer `j` is to find the first safe element which need to be moved into the safe part. The relative order of the elements won't be changed. $\implies \mathcal{O}(n)$

  ```java
  public int removeElement(int[] nums, int val) {
      int i = 0;
  
      for (int j = 0; j < nums.length; j++) {
          if (nums[j] != val) { // find the safe element
              nums[i] = nums[j]; // move it into the end of the safe part
              i++; // i redirects to the new boundary of the safe part
          } // only in this case can i be changed!!!
      }
      return i;
  }
  ```

+ Solution 3: Two pointers <u>when the elements to remove are rare</u>. To avoid unnecessary copy/move operations required in Solution 2. The relative order of the elements may be changed. $\implies \mathcal{O}(n)$

  ```java
  public int removeElement(int[] nums, int val) {
    	int i = 0;
    	int len = nums.length;
  
    	while (i < len) {
      		if (nums[i] == val) {
          		nums[i] = nums[len - 1];
        			len--;  // reduce array size by one
          } else {
        			i++;
      		}
    	}
    	return len;
  }
  ```

**********

##### [0141] Linked List Cycle

Given `head`, the head of a linked list, determine if the linked list has a cycle in it. Return `true` *if there is a cycle in the linked list*. Otherwise, return `false`.

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
        return true;
      }
    }
  
    return false;
  }
  ```

+ Solution 2: Two-Pointer Scenario II  $\implies \mathcal{O}(n)\ \&\ \mathcal{O}(1)$

  ```java
  public boolean hasCycle(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;
    
    while (fast != null && fast.next != null) { 
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) return true;
    }
    
    return false;
  }
  ```

**************

##### [0167] Two Sum II - Input array is sorted

给定target的值，返回input array中相加等于target的两个元素的indexes。

+ Solution 1: 和[[0001]](#[0001] Two Sum) Solution 2相同，注意本题是1-indexed. $\implies \mathcal{O}(n)$​​ ==没有利用***sorted***==

==看到***sorted*** array，不仅需要考虑Binary Search，还可以考虑Two-Pointer Technique.== 

+ Solution 2: Two-Pointer Technique

  一首一尾两个指针，如果两指针对应的元素之和 > target，则right--；如果两指针对应的元素之和 < target，则left++。$\implies \mathcal{O}(n)$

  ```java
  public int[] twoSum(int[] numbers, int target) {
    int left = 0;
    int right = numbers.length-1;
    while (left < right) { // Since the problems claimed you may not use the same element twice.
      int tmp = numbers[left] + numbers[right];
      if (tmp == target) {
        return new int[]{left+1, right+1};
      } else if (tmp < target) {
        left++;
      } else {
        right--;
      }
    }
  
    return new int[]{-1, -1};
  }
  ```

+ Solution 3: Binary Search

  如果挨个遍历array中的元素，利用Binary Search找该元素的complement (= target - nums[i]) 的index，则Time Complexity为 $\mathcal{O}(nlogn)$​​。所以本Solution用到Binary Search的变形 with clever trick。==思路很巧妙，但是不如Solution好写好理解== $\implies$​​ $\mathcal{O}(logn)$​​ and deteriorates to $\mathcal{O}(n)$​​ when the two numbers are in the center of the input.

  已知 `nums` 是sorted，则
  $$
  \begin{gathered}
  nums[left]<nums[mid]<nums[right] \\
  \implies nums[left]+nums[mid]<nums[left]+nums[right]<nums[mid]+nums[right]
  \end{gathered}
  $$
  此时：

  + If $target<nums[left]+nums[mid]$​, two nums we are looking for must be within $[left,mid-1]$​. 

  + If $target>nums[mid]+nums[right]$​​, two nums we are looking for must be within $[mid+1,right]$​​. 

  但是如果 $nums[left]+nums[mid]<target<nums[mid]+nums[right]$​，则完全不知道如何缩小范围。因此，target应该首先和 $nums[left]+nums[right]$ 相比，再和两边相比后，即可。
  $$
  \begin{matrix}
  nums[left]+nums[mid]<nums[left]+nums[right]<nums[mid]+nums[right] \\
  \quad \uparrow① \quad\quad\quad\quad\quad\quad\quad\quad\quad\ \ \ \ \ \uparrow② \quad\quad\quad\quad\quad\quad\quad\quad\quad\quad\ \ \ \uparrow③ \quad\quad\quad\quad\quad\quad\quad\quad\quad\quad\ \ \uparrow④ 
  \end{matrix}
  $$

  + target在①: mid-1
  + target在②: right-1
  + target在③: left+1
  + taregt在④: mid+1

  ```java
  public int[] twoSum(int[] numbers, int target) {
    int left = 0;
    int right = numbers.length-1;
    while (left < right) {
      int mid = left + (right - left) / 2;
      int tmp = numbers[left] + numbers[right];
      if (tmp == target) {
        return new int[]{left+1, right+1};
      } else if (tmp < target) { // 此时需要确定target在③还是④
        left = numbers[mid] + numbers[right] < target ? mid+1 : left+1;
      } else { // 此时需要确定target在①还是②
        right = numbers[left] + numbers[mid] > target ? mid-1 : right-1;
      }
    }
  
    return new int[]{-1, -1};
  }
  ```

******

##### [0209] Minimum Size Subarray Sum

+ Solution 1: Two-Pointer：构建滑动窗口，窗口内的元素和一旦大于target，则contract窗口。$\implies \mathcal{O}(n)$

  ```java
  public int minSubArrayLen(int target, int[] nums) {
    	int i = 0, j = 0;
    	int sum = 0;
    	int minLen = nums.length;  // 也可以将minLen初始为一个大于nums.length的数，这样return时如果minLen未改变，即满足条件的subarray不存在，也就不需要设置isExists
    	boolean isExists = false;
          
    	for (; j < nums.length; j++) {
      		sum += nums[j];
      		while (sum >= target) {
        			isExists = true;
       	 			minLen = Math.min(minLen, j-i+1);
        			sum -= nums[i++];
      		}
    	}
  
    	return isExists ? minLen : 0;
  }
  ```

+ Solution 2: [Binary Search](https://leetcode.com/problems/minimum-size-subarray-sum/solution/): C++写的，不想看。。。

******

##### [0283] Move Zeroes

Given an integer array `nums`, move all `0`'s to the end of it while maintaining the relative order of the non-zero elements. **In-place**

+ Solution 1: Brute Force 没写

+ Solution 2: Two-Pointer  $\implies \mathcal{O}(n)$    ==**写得好**==

  ```java
  public void moveZeroes(int[] nums) {
    int i =0;
    for (int j = 0; j < nums.length; j++) {
      if (nums[j] != 0) {
        nums[i] = nums[j];
        // Without this, we just simply move all the non-zero elements to left.
        // We should also set the last few elements to 0.
        // Attention! We cannot just simply set nums[j] to 0 because we might cover the new nums[i] when i==j.
        if (i != j) nums[j] = 0;
        i++;
      }
    }
  }
  ```

  `if (i != j) nums[j] = 0` 这个判断很重要，它实现了将最后几位置为0。在LeetCode Approach #2中，这一过程是通过另一for循环实现的, which is dumb.

******

##### [0344] Reverse String

String是以char[]的形式给出的，所以只需一首一尾依次向内交换两元素即可。或者通过Recursion的方式完成两个元素的swap。

*****

##### [0485] Max Consecutive Ones

Given a binary array `nums`, return *the maximum number of consecutive* `1`*'s in the array*.

+ Solution 1: One Pass $\implies \mathcal{O}(n)$

  ```java
  public int findMaxConsecutiveOnes(int[] nums) {
      int count = 0;
      int maxCount = 0;
      for(int i = 0; i < nums.length; i++) {
          if(nums[i] == 1) {
              // Increment the count of 1's by 1, whenever you encounter a 1
              count += 1;
          } else { //whenever you encounter 0
              // Find the maximum till now
              maxCount = Math.max(maxCount, count);
              // Reset count of 1s to 0
              count = 0;
          }
      }
      return Math.max(maxCount, count);
  }
  ```

+ Solution 2: Two-Pointer $\implies \mathcal{O}(n)$​  ==**最佳**==

  ```java
  public int findMaxConsecutiveOnes(int[] nums) {
      int start = 0;
      int end = 0;
    	int maxLen = 0;
  
    	for (; end<nums.length; end++){
        	if (nums[end] == 0) {
            	maxLen = Math.max(maxLen, end - start);
            	start = end+1;
          }
      }
  
    	return Math.max(maxLen, end - start);
  }
  ```

+ Solution 3: 将0作为隔板，利用0的index计算。需要注意的是要在数组首尾各加一个0。**是个独特的思路，但是本质还是和two-pointer一样，只是阐述方式不同，而且实现上稍复杂。** $\implies \mathcal{O}(n)$

  ```java
  public int findMaxConsecutiveOnes(int[] nums) {
      int prev = -1; // prev是数组首加的0
      int max = 0;
      for (int i = 0; i < nums.length; i++) {
          if (nums[i] == 0) {
              max = Math.max(i - prev - 1, max);
              prev = i;
          }
      }
      return Math.max(nums.length - prev - 1, max); // nums.length是数组尾加的0
  }
  ```

+ Solution 4: 将int[]转为String，按照0分割后，返回最长字符串的长度。*思路新颖，但表现最差* $\implies \mathcal{O}(n)$

  ```java
  public int findMaxConsecutiveOnes(int[] nums) {
      StringBuilder sb = new StringBuilder();
      for (int i : nums) sb.append(i);
      String s = sb.toString();
      // String s = Arrays.toString(nums); // 不可以这么转换，数字[1,1,0,1,1]的输出结果为"[1,1,0,1,1,1]"，导致之后split出错
      String[] ss = s.split("0");
    
      int maxLen = 0;
      for (String sss : ss) 
          maxLen = Math.max(maxLen, sss.length());
  
      return maxLen;
  }
  ```

******

##### [0487] Max Consecutive Ones II

Given a binary array `nums`, return *the maximum number of consecutive* `1`*'s in the array if you can flip at most one* `0`.

+ Solution 1: Brute Force $\implies \mathcal{O}(n^2)$​

  ```java
  public int findMaxConsecutiveOnes(int[] nums) {
      int longestSequence = 0;
      for (int left = 0; left < nums.length; left++) {
          int numZeroes = 0;
          // check every consecutive sequence
          for (int right = left; right < nums.length; right++) {
              // count how many 0's
              if (nums[right] == 0) numZeroes += 1;
              // update answer if it's valid
              if (numZeroes <= 1) {
                  longestSequence = Math.max(longestSequence, right - left + 1);
              }
          }
      }
      return longestSequence;
  }
  ```

+ Solution 2: 借鉴[0485] Solution 2的方法，找到原数组中连续全为1的子串的长度，相邻两两拼接，输出最大值 $\implies \mathcal{O}(n)$​

  ```java
  public int findMaxConsecutiveOnes(int[] nums) {
      int start = 0, end = 0, i = 0;
      int[] lens = new int[nums.length+1]; // 因为下面第一个for循环本是为了记录全为1的子串的长度，但是写的却是每当值为0时也会记下长度（=0），导致所需要的lens数组长度过长
      int maxLen = 1;
  
      if (nums.length == 1) return 1;
  
      for (; end < nums.length; end++) {
          if (nums[end] == 0) {
              lens[i++] = end - start;
              start = end + 1;
          }
      }
      lens[i] = end - start;
      //System.out.println(Arrays.toString(lens));
      if (i == 0) return lens[i]; // 原数组全为1的情况，并不需要拼接/并不需要+1
  
      for (int j = 0; j < lens.length-1; j++)
          if (lens[j] + lens[j+1] + 1 > maxLen)
              maxLen = lens[j] + lens[j+1] + 1;
          
      return maxLen;
  }
  ```

+ Solution 3: Expand Around Center: 只要遇到0，就以这个0为中心向两边扩散找1 $\implies \mathcal{O}(n^2)$​

  ```java
  public int findMaxConsecutiveOnes(int[] nums) {
      int maxLen = 0;
      int iLen = 0;
      boolean all1s = true; // 全为0的情况不用特别考虑，但是全为1需要
  
      for (int i = 0; i < nums.length; i++) {
          if (nums[i] == 0) {
              all1s = false;
              iLen = expandAroundCenter(nums, i);
          }
          if (iLen > maxLen) maxLen = iLen;
      }
  
      return all1s ? nums.length : maxLen;
  }
  
  public int expandAroundCenter(int[] nums, int index) {
      int left = index - 1;
      int right = index + 1;
  
      while (left >= 0 && nums[left] == 1) left--;
      while (right < nums.length && nums[right] == 1) right++;
      return right - left - 1;
  }
  ```

+ Solution 4: Two-Pointer Technique with `numZeroes` - *Solution 1的改良版* $\implies \mathcal{O}(n)$​​

  ```java
  public int findMaxConsecutiveOnes(int[] nums) {
      int longestSequence = 0;
      int left = 0;
      int right = 0;
      int numZeroes = 0;
  
      while (right < nums.length) {
          if (nums[right] == 0) numZeroes++;
          // if our window is invalid, contract our window
          while (numZeroes == 2) {
              if (nums[left] == 0) numZeroes--;
              left++;
          }
          longestSequence = Math.max(longestSequence, right - left + 1);
          right++;
      }
    
      return longestSequence;
  }
  ```

+ Solution 5: Two-Pointer Technique with `lastZeroIndex`  ==**最佳**==  $\implies \mathcal{O}(n)$

  ```java
  public int findMaxConsecutiveOnes(int[] nums) {
      int len = nums.length;
      int max1s = 0;
      int count = 0;
      int lastZeroIndex = -1; // 关键！！非常巧妙的设置！！
  
      for (int i = 0; i < len; i++) {
          if(nums[i] == 0) {
              count = i - lastZeroIndex;
              lastZeroIndex = i;
          } else {
              count++;
          }
          max1s = Math.max(count, max1s);
      }
  
      return max1s;
  }
  ```

******

******

### 