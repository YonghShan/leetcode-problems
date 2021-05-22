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

