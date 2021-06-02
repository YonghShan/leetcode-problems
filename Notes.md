+ [0001]：Solution2 - Trick: 如何one pass同时完成对HashMap的insert和search

+ [0021]：无论是Iteration还是Recursion，关键都是比较l1.val和l2.val

+ [0572]：Recursion

  + 当s == null时: return t == null
  + 当s != null时：
    + t == null：false    // 可省略 
    + s.val == t.val && isSameTree(s, t)：    // 不能先判断s.val == t.val再判断isSameTree(s, t)
      + s == null: return t == null
      + s != null: 
        + t == null: false     // 不可省略
        + s.val != t.val: false
        + s.val == t.val: isSameTree(s.right, t.right) && isSameTree(s.left, t.left);
    + s.val != t.val：isSubtree(s.left, t) || isSubtree(s.right, t);

+ [0682]：Reverse Polish Notation

  + String —> Integer: Integer.valueOf(String)     // new Integer(String): deprecated

+ [0819]：

  + Solution 1: word层面

    + 正则表达式的写法: 双斜杠转义

    + 判断word是否在String[] banned时: Arrays.asList(banned).contains(word)  // 有点蠢，asList大概率takes O(m), contains takes O(m), for循环n次，则takes O(nm), 不如提前用banned建立一个HashSet.

    + 自定义

      Collections.sort(要sort的对象, new Comparator<>() {

      ​		@Override

      ​		public int compare() {

      ​		}

      })

  + Solution 2: char层面

+ [142]：典型的Floyd's Algorithm

  Floyd's Algorithm: 

  + Phase 1: tortoise 一步一node；hare 一步二nodes    --->    两者在intersection相遇
  + Phase 2: 因为intersection并不一定是the entrance of the cycle，所以还需要Phase 2: tortoise 回到起点一步一node；hare 待在intersection一步一node    --->    两者在entrance相遇                

  + Solution2Abbr: 写得好

+ [287]：参考[0142]: Linked List Cycle II

  + 如何将array转化为linked list：f(x) = nums[x]
  + Floyd's Algorithm: 
    + Phase 1: tortoise 一步一node；hare 一步二nodes    --->    两者在intersection相遇
    + Phase 2: 因为intersection并不一定是the entrance of the cycle，所以还需要Phase 2: tortoise 回到起点一步一node；hare 待在intersection一步一node    --->    两者在entrance相遇                

+ [719]：

  + Naive method: 找到所有pair的difference $(O(n^2))$ -> sort the differences $(O(n^2log(n^2)))$ -> 找到第kth小的value.

  + geekforgeeks (https://www.geeksforgeeks.org/k-th-smallest-absolute-difference-two-elements-array/)  比Leetcode Approach #2更好理解:

    + Step 1: Sort the array

    + Step 2: 找到min-difference（one pass：比较array中相邻两个值的difference即可 $O(n)$）作为left；找到max-difference（nums[nums.length-1] - nums[0]）作为right；

    + Step 3: 开始binary search:

      ```java
      while (left < right) {
        mid = left + (right - left) / 2;
        if (difference <= mid的pair的数量 < k) {
          left = mid + 1;
        } else {
          right = mid;
        }
      }
      ```

      如何找到difference ≤ mid的pair的数量：

      ```java
      result = 0
      for i = 0 to n-1:
      	result = result + (upper_bound(i, nums.length, nums[i] + mid) - (i+1))
      return result
      ```

      upperBond: 利用Binary Search快速返回在[src, end]范围内，nums中第一个大于nums[i]+mid (i.e. target) 的值的index

  + Leetcode Approach #3：相当于上面的简化版

    + main Binary Search的left直接定为0
    + 简化如何找到difference ≤ mid的pair的数量部分

+ [410]：

  + DP: 长度为n，subarray数为m，建立dp二维数组dp\[n+1][m+1]
    + dp\[i][j]: 将长度为i的array分割为j个subarray后，得到的minimum largest subarray sum
    + dp\[i][j] 由 max(dp\[k][j-1], nums[k+1]+...+nums[i])（解释：当长度为i时，从0～k的范围内分割出(j-1)个subarray后，从k+1～i组成第j个subarray）决定，而$k \in [0, i)$，所以更准确地说，dp\[i][j] = min[max(dp\[k][j-1], nums[k+1]+...+nums[i])]
    
  + Binary Search + Greedy:
  
    + 定义函数F(x)，表示：当存在一种分法使得各subarray的the maximum largest sum不超过x时，F(x)为true
  
    + $x \in [left, right]$ (left为nums最大值，right为nums所有元素之和)， F(x)一开始为false，直到$x=x_0$时，F(x)变为true，此后F(x)一直保持为true => $x_0$即为answer
  
    + 利用BS寻找$x_0$:
  
      ```java
      if F(mid) == false, then search [mid+1, right]
      if F(mid) == true, then search [left, mid-1]
      ```
  
    + F(mid)的值（true/false）由下面两个条件共同得出：
      + the maximum largest sum of each subarray doesn't exceed the mid. Once the sum exceeds the mid, split it and begin a new subarray   => 定义变量sum记录当前元素之和
      + the number of subarrays doesn't exceed m => 定义变量cnt记录subarray的数量
      + 同时满足上述两个条件，则F(mid) == true.

