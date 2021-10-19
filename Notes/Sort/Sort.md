### Sort

##### [0220] Contains Duplicate III

在索引值差k的范围内，有两数相差t（`|nums[i]-nums[j]|≤t && |i-j|≤k`）

+ Solution 1: 将每个数依次与其后的k个数（注意别超限）比较（是否相差t以内） $\implies$ $\mathcal{O}(n^2$)

+ Solution 2: TreeSet (BST) as Sliding Window  $\implies$ $\mathcal{O}(nlog(min(n,k)))$

  1. 维护一个长度为k的BST，在将每个值`nums[i]`放入BST之前，利用TreeSet的`ceiling()`和`floor()`方法分别寻找到当前BST中大于或等于`nums[i]`的最小值ceiling，以及小于或等于`nums[i]`的最大值floor；==注意：这两个值可能不存在== 

  2. 如果ceiling或floor与`nums[i]`相差t，则找到duplicate。

  如果在k的范围内，存在一个大于`nums[i]`的数与其相差t，那么ceiling也一定与`nums[i]`相差t；同理，如果在k的范围内，存在一个小于`nums[i]`的数与其相差t，那么floor也一定与`nums[i]`相差t。

+ Solution 3: ==***Clever Trick***== Inspired by Bucket Sort $\implies \mathcal{O}(n)$

  假设每个桶的宽度 (capacity) 为t+1，则原数组中的每个数都可以放进相应的桶中。用一个`HashMap(bucketID, nums[i]) buckets`记录每个桶中所放的`nums[i]`。当出现以下三种情况之一时，找到duplicate：

  + `nums[i]`对应的桶中已有值：`buckets.containsKey(bucketID)`;

  + `nums[i]`对应的桶中无值，但前一个桶中有值，且与其相差t：`buckets.containsKey(bucketID - 1) && Math.abs(buckets.get(bucketID-1)-nums[i]) <= t`;
  + `nums[i]`对应的桶中无值，但后一个桶中有值，且与其相差t：`buckets.containsKey(bucketID + 1) && Math.abs(buckets.get(bucketID+1)-nums[i]) <= t`.

  ***e.g.*** nums = [-9, -5, -1, 1, 5, 9]      k = 2     t = 3 (w = t + 1 = 4)

  ```java
  // Get the ID of the bucket from element value x and bucket width w
  // In Java, `-3 / 5 = 0` and but we need `-3 / 5 = -1`.
  public long getBucketID(long x, long w) {
    	return x < 0 ? (x + 1) / w - 1 : x / w;
  }
  ```

  | nums[i] | bucketID |
  | :-----: | :------: |
  |   -9    |    -3    |
  |   -5    |    -2    |
  |   -1    |    -1    |
  |    1    |    0     |

  此时，bucketID = -1 的桶中的值（-1）与bucketID = 1 的桶中的值（1）相差小于3，return true。

*****

*****

