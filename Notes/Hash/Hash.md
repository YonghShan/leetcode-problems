### Hash

#### HashMap定义时初始化

```java
Map<Character, Integer> toInt = new HashMap<>() {
  {
    put('A', 0); 
    put('C', 1); 
    put('G', 2); 
    put('T', 3); 
  }
};
// or
Map<Character, Integer> toInt = new
                HashMap<>() {{put('A', 0); put('C', 1); put('G', 2); put('T', 3); }};
```

*解释：*外层大括号为匿名内部类；内层大括号内还可以写for循环。

******

*****

##### [0217] Contains Duplicate

+ Solution 1 & 2: 最intuitive的思路：用HashSet $\mathcal{O}(n)$，两种判断方法：

  + `set.contains()`
  + `set.size() == 原数组的长度 ?`

+ Solution 3: 先sort $\mathcal{O}(nlogn)$，然后`nums[i] == nums[i+1] ?`

  当待判定的数组的长度不是很大(not sufficiently large)时，Solution 3会比Solution 1快

  ==The Big-O notation only tells us that for sufficiently large input, one will be faster than the other.==

*******

