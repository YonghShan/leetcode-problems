### Binary Search

##### [0719] Find K-th Smallest Pair Distance

+ Naive method: 找到所有pair的difference $(\mathcal{O}(n^2))$ $\rightarrow$ sort the differences $(\mathcal{O}(n^2log(n^2)))$ $\rightarrow$ 找到第 $k^{th}$ 小的value.

+ [geekforgeeks](https://www.geeksforgeeks.org/k-th-smallest-absolute-difference-two-elements-array/)  比Leetcode Approach #2更好理解:

  + Step 1: Sort the array

  + Step 2: 找到min-difference（one pass：比较array中相邻两个值的difference即可 $\mathcal{O}(n)$）作为left；找到max-difference（nums[nums.length-1] - nums[0]）作为right；

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

    如何找到difference $\le$ mid的pair的数量：

    ```java
    result = 0
    for i = 0 to n-1:
    	result = result + (upper_bound(i, nums.length, nums[i] + mid) - (i+1))
    return result
    ```

    upperBond: 利用Binary Search快速返回在[src, end]范围内，nums中第一个大于nums[i]+mid (i.e. target) 的值的index

+ Leetcode Approach #3：相当于上面的简化版

  + main Binary Search的left直接定为0
  + 简化如何找到difference $\le$ mid的pair的数量部分

******

****

