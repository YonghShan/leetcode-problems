### Misc.

##### [0001] Two Sum

Solution 2：如何one pass同时完成对HashMap的insert和search 

==***Trick:***== 当需要两层嵌套的 for 循环时，考虑引入 HashSet / HashMap 转变为 one pass<a name="hashsetTrick"></a>  

[[0187]](#[0187] Repeated DNA Sequences)

******

##### [0014] Longest Common Prefix

+ Solution 1: 最先想到的就是利用Trie

  *思路：*将所有字符串插入Trie后，从root开始遍历，沿着只有一个childNode的TrieNode构建prefix。但有个特殊情况需要注意，假如strs = ["a", "ab"]，则按照上述思路得到的prefix为"ab"，但实际应为"a"。故还需==判断得到的prefix和strs中最短字符串的长度关系==：the length of the longest common prefix ≤ the length of the shortest string.

  ```java
  class TrieNode {
    private TrieNode[] node;
    private int childNodesNum;
  
    public TrieNode() {
      node = new TrieNode[26];
      childNodesNum = 0;
    }
  }
  
  class Trie {
    private TrieNode root;
  
    public Trie() {
      root = new TrieNode();
    }
  
    public void insert(String s) {
      TrieNode p = root;
      for (int i = 0; i < s.length(); i++) {
        int idx = s.charAt(i) - 'a';
        if (p.node[idx] == null) {
          p.node[idx] = new TrieNode();
          p.childNodesNum++;
        }
        p = p.node[idx];
      }
    }
  
    public String findLongestPrefix() {
      TrieNode p = root;
      StringBuilder sb = new StringBuilder();
  
      while (p.childNodesNum == 1) {
        int idx = 0;
        for (int i = 0; i < 26; i++) {
          if (p.node[i] != null) idx = i;
        }
        sb.append((char)(idx + 'a'));
        p = p.node[idx];
      }
  
      return sb.toString();
    }
  }
  
  public String longestCommonPrefix(String[] strs) {
    Trie trie = new Trie();
  
    // 假设strs为["a", "ab"]，则调用findLongestPrefix()的结果为"ab"，但实际结果应为"a"
    // 为防止这种情况，在遍历strs时，记录长度最小的str
    int shortestLen = Integer.MAX_VALUE;
    String shortestStr = "";
    for (String str : strs) {  // O(S), where S is the total length of all str in strs together
      if (str.length() == 0) return ""; // 只要strs中有str为""，则return "";
      if (str.length() < shortestLen) {
        shortestLen = str.length();
        shortestStr = str;
      }
      trie.insert(str);
    }
  
    // the longest prefix的长度不可能比shortestLen大
    String res= trie.findLongestPrefix();  // O(P), where P is the length of the potential longest prefix
    return res.length() > shortestLen ? shortestStr : res;
  }
  ```

  *Time Complexity:* $\mathcal{O}(S+P)$

  *Space Complexity:* $\mathcal{O}(S)$ for all the TrieNodes

+ Solution 2: Horizontal Scanning

  *思路：*strs中第一个String与第二个String比较得出common prefix后，再将common prefie与第三个String比较，更新common prefix后再与第四个...

  *关键：* `indexOf(Stirng s)` 的使用： == `indexOf()` 的参数不仅可以是char，也可以是String==

  ```java
  public String longestCommonPrefix(String[] strs) {
    String prefix = strs[0];
    for (int i = 1; i < strs.length; i++) {
      while (strs[i].indexOf(prefix) != 0) { // 如果strs[i]中并不包含prefix或者包含prefix但位置不是在最开头，则需要修剪prefix
        prefix = prefix.substring(0, prefix.length()-1);
        if (prefix.isEmpty()) return "";
      }
    }
    return prefix;
  }
  ```

  *Time Complexity:* $\mathcal{O}(S=m·n)$. In the worst case there will be $n$ equal strings with length $m$ and the algorithm performs $S=m·n$ comparisons. 第一个String要和之后的所有的Strings全部比一遍。

  *Space Complexity:* $\mathcal{O}(1)$

+ Solution 3: Vertical Scanning  ==**最佳**==

  *思路：*Compare characters from top to bottom on the same column before moving on to the next column.

  ```java
  public String longestCommonPrefix(String[] strs) {
    for (int i = 0; i < strs[0].length(); i++) {
      char c = strs[0].charAt(i);
      for (int j = 1; j < strs.length; j++) {
        if (i == strs[j].length() || c != strs[j].charAt(i)) // strs[j]可能短于strs[0]
          return strs[0].substring(0, i);
      }
    }
    return strs[0];
  }
  ```

  *Time Complexity:* $\mathcal{O}(S=m·n)$. 同样in the worst case all $n$ strings are the same. 此时还是需要完成 $S$ comparisons. 而in the best case there are at most $n·minLen$ comparisons. 因此，与Solution 2相比，对于a very short string is the common prefix at the end of the array这一情况，Solution 3可以提前结束，不必完成 $S$ comparisons.

  *Space Complexity:* $\mathcal{O}(1)$

+ Solution 4: Divide and Conquer

  *思路：*对strs[]分组后分别找寻longest common prefix.

  ```java
  public String longestCommonPrefix(String[] strs) {
    return divideAndConquer(strs, 0, strs.length-1);
  }
  
  public String divideAndConquer(String[] strs, int left, int right) {
    if (left == right) { // 不停分割，直到不能再分割
      return strs[left];
    } else {
      int mid = left + (right - left) / 2;
      String lcpLeft = divideAndConquer(strs, left, mid);
      String lcpRight = divideAndConquer(strs, mid+1, right);
      return commonPrefix(lcpLeft, lcpRight); // Conquer
    }
  }
  
  public String commonPrefix(String left, String right) { 
    int minLen = Math.min(left.length(), right.length());
    for (int i = 0; i < minLen; i++) {
      if (left.charAt(i) != right.charAt(i))
        return left.substring(0, i);
    }
    return left.substring(0, minLen);
  }
  ```

  *Time Complexity:* $T(n)=2·T(\frac{n}{2})+\mathcal{O}(m) \implies \mathcal{O}(S=m·n)$. In the best case this algorithm performs $\mathcal{O}(n·minLen)$.

  *Space Complexity:* $\mathcal{O}(m·logn)$. There are $logn$ recursive calls, each call needs $m$ space to store the result.

+ Solution 5: Binary Search

  *思路：*已知the length of the longest common prefix ≤ the length of the shortest string，所以可以对the length of the longest common prefix进行binary search，上限为 $minLen$ 。

  ```java
  public String longestCommonPrefix(String[] strs) {
    // Step 1: find the minLen
    int minLen = Integer.MAX_VALUE;
    for (String str : strs) 
      minLen = Math.min(minLen, str.length());
  
    // Step 2: Binary Search
    int left = 0;
    int right = minLen;
    while (left < right) {
      int mid = left + (right - left) / 2;
      if (mid == left) mid++; // 防止String S的长度为3，此时left=0，right=2，mid=1；如果满足条件，则left=mid=1，right=2，陷入循环
      if (isCommonPrefix(strs, mid)) left = mid;
      else right = mid-1;
    }
    
    return strs[0].substring(0, left);
  }
  
  public boolean isCommonPrefix(String[] strs, int len) {
    String prefix = strs[0].substring(0, len);
    for (int i = 1; i < strs.length; i++) 
      if (!strs[i].startsWith(prefix))
        return false;
    return true;
  }
  ```

  *Time Complexity:* $\mathcal{O}(S·logm)$. The algorithm makes $logm$ iterations, for each of them there are $S=m·n$ comparisons.

  *Space Complexity:* $\mathcal{O}(1)$

*Summary:* In the worst case there will be $n$ equal strings with length $m$.

|         Solution          |                       Time Complexity                        |             Space Complexity              |
| :-----------------------: | :----------------------------------------------------------: | :---------------------------------------: |
|           Trie            |                      $\mathcal{O}(S+P)$                      |      $\mathcal{O}(S)$ for TrieNodes       |
|    Horizontal Scanning    |                     $\mathcal{O}(S=m·n)$                     |             $\mathcal{O}(1)$              |
| ==**Vertical Scanning**== | $\mathcal{O}(S=m·n)$ in worst case; $\mathcal{O}(n·minLen)$ in best case |             $\mathcal{O}(1)$              |
|     Divide & Conqure      | $\mathcal{O}(S=m·n)$ in worst case; $\mathcal{O}(n·minLen)$ in best case | $\mathcal{O}(m·logn)$ for recursive calls |
|       Binary Search       |                    $\mathcal{O}(S·logm)$                     |             $\mathcal{O}(1)$              |

*******

##### [0021] Merge Two Sorted Lists

无论是Iteration还是Recursion，关键都是比较l1.val和l2.val

*****

##### [0054] Spiral Matrix

将一个二维数组按照如下方式遍历为一个一维数组：$[[1,2,3,4],[5,6,7,8],[9,10,11,12]] \rightarrow [1,2,3,4,8,12,11,10,9,5,6,7]$

![](/Users/shanyonghao/IdeaProjects/LeetCodeProblems/Notes_img/[0054].jpg)

Solution: Layer-by-Layer

![](/Users/shanyonghao/IdeaProjects/LeetCodeProblems/Notes_img/[0054]_1.png)

```java
int r1 = 0, r2 = matrix.length-1;
int c1 = 0, c2 = matrix[0].length-1;

while (r1 <= r2 && c1 <= c2) {
    for (int c = c1; c <= c2; c++) res.add(matrix[r1][c]);
    for (int r = r1+1; r <= r2; r++) res.add(matrix[r][c2]);
    // 如果r1 == r2，则说明此次进while循环时，只剩一行元素，已经由第一个for循环结束，无需在进行下面的循环
    // 如果c1 == c2，则说明此次进while循环时，只剩一列元素，已经由第二个for循环结束，无需在进行下面的循环
    if (r1 < r2 && c1 < c2) {
        for (int c = c2-1; c > c1; c--) res.add(matrix[r2][c]);
        for (int r = r2; r > r1; r--) res.add(matrix[r][c1]);
    }
    r1++;
    r2--;
    c1++;
    c2--;
}
```

*******

##### [0066] Plus One

==操作对象是array== 一个数组表示一个数字，*e.g.* [1,2,3]表示数123，现在需对该数+1变为124，返回相应的数组[1,2,4]。     *和 [[0369]](#[0369] Plus One Linked List) 是类似题目*

注意的点：

1. 当前位的值为9，+1后存在进位；

   ```java
   for (int i = len-1; i >= 0; i--) {
       if (digits[i] == 9) {
           digits[i] = 0;
       } else {
           digits[i] += 1;
           return digits;
       }
   }
   ```

2. 从最低位至最高位全都存在进位，原数组长度需+1。

   ```java
   digits = new int[len+1];
   digits[0] = 1;
   return digits;
   ```

******

##### [0369] Plus One Linked List

==操作对象是Linked List== *和 [[0066]](#[0066] Plus One) 是类似题目*

+ Solution 1: 因为所要操作的Linked List只给了作为head的ListNode，所以先将原Linked List reverse，再逐位+1，最后将结果reverse：      $\mathcal{O}(n)$

  ```java
  public ListNode plusOne(ListNode head) {
      if (head == null) return head;
  
      ListNode list = reverse(head);  // reverse原linked list，此时list为翻转后的list的head
      int carry = 1;  // carry初始为1，表示要对linked list加的1
      ListNode cur = list;
      while (cur != null && carry != 0) {  // 循环条件包括carry !=0 是为了无进位时提前结束循环
          int val = cur.val + carry;
          carry = val / 10;
          cur.val = val % 10;
          cur = cur.next;
      }
  
      if (carry > 0) head.next = new ListNode(carry);
  
      return reverse(list);
  }
  ```

  ```java
  private ListNode reverse(ListNode node) {
      ListNode head = null;
      ListNode cur = node;
  
      while (cur != null) {
          ListNode next = cur.next;
          cur.next = head;
          head = cur;
          cur = next;
      }
  
      return head;
  }
  ```

+ Solution 2:       $\mathcal{O}(n)$

  + find the rightmost not-nine digit: 这一位之后的数字全都为9，如果不存在这种情况，则rightmost not-nine digit为最后一位；
  + increase this rightmost not-nine digit by 1：
    + 如果rightmost not-nine digit为最后一位，则加1后任务完成；
    + 如果rightmost not-nine digit不为最后一位，则说明此后的所有位都要由9变为0；
  + 要注意的一种情况是，Linked List初始值为99...99，此时结果应为100...00 $\implies$ rightmost not-nine digit要从head的前面（sentinel node）开始寻找。

  ```java
  public ListNode plusOne(ListNode head) {
      // sentinel head
      ListNode sentinel = new ListNode(0);
      sentinel.next = head;
      // 防止Linked List初始值为99...99的情况，not-nine要从sentinel开始寻找
      ListNode notNine = sentinel;
  
      // find the rightmost not-nine digit
      while (head != null) {
          if (head.val != 9) notNine = head;
          head = head.next;
      }
  
      // increase this rightmost not-nine digit by 1
      notNine.val++;
      notNine = notNine.next;
  
      // rightmost not-nine digit不为最后一位：set all the following nines to zeros
      while (notNine != null) {
          notNine.val = 0;
          notNine = notNine.next;
      }
  
      return sentinel.val != 0 ? sentinel : sentinel.next;
  }
  ```

*****

##### [0410] Split Array Largest Sum

+ DP: 长度为n，subarray数为m，建立dp二维数组dp\[n+1][m+1]

  + dp\[i][j]: 将长度为i的array分割为j个subarray后，得到的minimum largest subarray sum
  + dp\[i][j] 由 max(dp\[k][j-1], nums[k+1]+...+nums[i])（解释：当长度为i时，从0～k的范围内分割出(j-1)个subarray后，从k+1～i组成第j个subarray）决定，而$k \in [0, i)$，所以更准确地说，dp\[i][j] = min[max(dp\[k][j-1], nums[k+1]+...+nums[i])]

+ Binary Search + Greedy:

  + 定义函数F(x)，表示：当存在一种分法使得各subarray的the maximum largest sum不超过x时，F(x)为true

  + $x \in [left, right]$ (left为nums最大值，right为nums所有元素之和)， F(x)一开始为false，直到$x=x_0$时，F(x)变为true，此后F(x)一直保持为true $\implies$ $x_0$即为answer

  + 利用BS寻找$x_0$:

    ```java
    if F(mid) == false, then search [mid+1, right]
    if F(mid) == true, then search [left, mid-1]
    ```

  + F(mid)的值（true/false）由下面两个条件共同得出：

    + the maximum largest sum of each subarray doesn't exceed the mid. Once the sum exceeds the mid, split it and begin a new subarray  $\implies$ 定义变量sum记录当前元素之和
    + the number of subarrays doesn't exceed m $\implies$ 定义变量cnt记录subarray的数量
    + 同时满足上述两个条件，则F(mid) == true.

*****

##### [0498] Diagonal Traverse

将一个二维数组按照如下方式遍历为一个一维数组：$[[1,2,3],[4,5,6],[7,8,9]] \rightarrow [1,2,4,7,5,3,6,8,9]$

![](/Users/shanyonghao/IdeaProjects/LeetCodeProblems/Notes_img/[0498].jpg)

由上图红色部分标注可知：

Input : `int[m+1][n+1] mat`     Output : `int[(m+1)*(n+1)] res`

+ 同一条对角线上元素的index $(i,j)$ 之和sum ($=i+j$) 相同，范围为sum $\in [0, m+n]$；

+ 若sum为偶数，则对角线自左下向右上，故 $i$ 先大后小，$\implies i$ 从 $min(sum,m)$ 减至 $[max(sum-n,0)],\ j=sum-i$；

  若sum为奇数，则对角线自右上向左下，故 $i$ 先小后大，$\implies i$从 $max(sum-n,0)$ 增至 $min(sum,m)],\ j=sum-i$。

  ==$i$ 的范围取法是为了确保 $i$ 和 $j$ 都不越界==     [自己写的题解](https://leetcode.com/problems/diagonal-traverse/discuss/1342850/Java-20-line-code-Only-using-array-100-faster)

*****

##### [0561] Array Partition I

将长度为 $2n$ 的数组中的元素两两配对（$n$ pairs），保证所有pair中的最小值之和最大。 

+ Solution 1: sort数组后，两两配对 $\implies \mathcal{O}(nlogn)$

+ Solution 2: 将nums转换为count，避免sort
  $$
  nums:\ [6,2,6,5,1,2] \rightarrow
  count:\ \begin{matrix}
  \ \ \small{0} & \small{\color{red}{1}} & \small{\color{red}2} & \small{3} & \small{4} & \small{\color{red}5} & \small{\color{red}6}\ \  \\
  				 [\ 0 & \color{red}1 & \color{red}2 & 0 & 0 & \color{red}1 & \color{red}2\ ]
          \end{matrix}
  $$
  *解释：*nums中6出现2次，则count中对应6的位置值为2

  对应代码：

  ```java
  // 已知2 ≤ nums.length ≤ 20000
  int[] count = new int[2*10000+1];
  for (int num : nums) {
    count[num+10000]++; // 因为-10^4 ≤ num ≤ 10^4，所以+10000保证为非负数
  }
  ```

  接着，只需从头遍历count即可组成pairs:
  $$
  \begin{aligned}
  count:&\ 
  \begin{matrix}
  \ \ \small{0} & \small{\color{red}{1}} & \small{\color{red}2} & \small{3} & \small{4} & \small{\color{red}5} & \small{\color{red}6}\ \  \\
  [\ 0 & \color{red}1 & \color{red}2 & 0 & 0 & \color{red}1 & \color{red}2\ ]
  \end{matrix} \\
  pairs:&\ 
  \begin{matrix}
  (1,\ \ 2)\quad\quad\quad\quad\quad\ \  \\
  \ \ (2,\quad\quad\quad\ \ 5) \\
  \quad\quad\quad\quad\quad\quad\quad\quad\quad\ (6,6)
  \end{matrix}
  \end{aligned}
  $$

  ```java
  int i = 0;
  int pairsNum = nums.length / 2;
  int ans = 0;
  while (pairsNum-- > 0) {
    while (count[i] == 0) i++;
    ans += i - 10000;
    count[i]--;
    while (count[i] == 0) i++;
    // 为了跳过(a,b)中的b
    count[i]--;
  }
  ```

  *Time Complexity:* $\mathcal{O}(20001)$

  *Space Complexity:* $\mathcal{O}(20001)$

*****

##### [0709] To Lower Case

+ Solution 1: PriorityQueue 构建min heap  ==最简单，也最容易想到==
+ Solution 2: [LeetCode Analysis](https://leetcode.com/explore/learn/card/introduction-to-data-structure-binary-search-tree/142/conclusion/1009/) 是个有趣的思路，但没必要且不好理解

******

##### [0724] Find Pivot Index

惟一要注意的点是：pivot index可以为0或者nums.length-1，只要剩余部分之和为0即可。Two pointers一首一尾向中间逼近的做法并不能满足这个条件。

Solution1 / 2相同，只是Solution1中要不断更新rightSum和leftSum两个值，而Solution2只要更新leftSum。

```java
int leftSum = 0;
for (int i = 0; i < nums.length; i++) {     // O(n)
  	if (leftSum == totalSum - leftSum - nums[i]) return i;
  	leftSum += nums[i];
}
```

*******

##### [0747] Largest Number At Least Twice of Others

定义maxIdx和secondMaxIdx（==secondMaxIdx不能和maxIdx一样从0开始==，可初始化为除0以外的任意值），最终比较`nums[maxIdx] >= 2 * nums[secondMaxIdx]` 。

在从左到右遍历元素的过程中，需要注意的点是：

+ 如果maxIdx变动，则secondMaxIdx变为原本的maxIdx；
+ 如果maxIdx不变，依旧需要判断需不需要更新secondMaxIdx。

*****

##### [1089] Duplicate Zeros

Given a fixed length array `arr` of integers, duplicate each occurrence of zero, shifting the remaining elements to the right. Note that elements beyond the length of the original array are not written. **In-place**

+ Solution 1: Brute Force $\implies \mathcal{O}(n^2)$

  ```java
  public void duplicateZeros(int[] arr) {
      for (int i=0; i<arr.length; i++) {
          if (arr[i] == 0) {
              for (int j=arr.length-1; j>i; j--) arr[j] = arr[j-1];
              i++;
          }
      }
  }
  ```

+ Solution 2: Two Pass $\implies \mathcal{O}(n)$​

  + First Pass: 通过扫描数组中0的个数，确认还会出现在array中的元素范围*（下例中，范围为$[0,5]$​）*

  + Second Pass: 利用上一个pass中确定的范围内的元素从后向前重构array*（下例中，index=5的元素放在index=7；index=4的元素放在index=6 / 5；...)*

    *e.g.* $\begin{matrix} \ \small0&\small1&\small2&\small3&\small4&\small5&\small6&\small7\ \\ [1&0&2&3&0&4&5&0]\end{matrix} \ \rightarrow\ \begin{matrix} \ \small0&\small1&\small2&\small3&\small4&\small5&\small6&\small7\ \\ [1&0&0&2&3&0&0&4&\color{red}{5\mkern-9mu/}&\color{red}{0\mkern-9mu/}]\end{matrix}$

  ==Edge case:== 原数组中最后一位会被考虑进新数组的值正好为0，但新数组已经没有位置放这个0的duplicate了，所以只需把原数组的最后一位直接置为0即可。

  *e.g.* $\begin{matrix} \ \small0&\small1&\small2&\small3&\small4&\small5&\small6&\small7\ \\ [1&0&2&0&3&0&4&5]\end{matrix} \ \rightarrow\ \begin{matrix} \ \small0&\small1&\small2&\small3&\small4&\small5&\small6&\small7\ \\ [1&0&0&2&0&0&3&0&\color{red}{4\mkern-9mu/}&\color{red}{5\mkern-9mu/}]\end{matrix}$

  *位于index=5的0本也该被duplicate，但是已没有多余的位置，所以直接将原数组的末尾置为0。*

  ```java
  public void duplicateZeros(int[] arr) {
      int possibleDups = 0;
      int len = arr.length - 1;
  
      // Find the number of zeros to be duplicated
      // Stopping when left points beyond the last element in the original array
      // which would be part of the modified array
      for (int left = 0; left <= len - possibleDups; left++) {
          if (arr[left] == 0) {
              // Edge case: This zero can't be duplicated. We have no more space,
              // as left is pointing to the last element which could be included
              if (left == len - possibleDups) {
                  // For this zero we just copy it without duplication.
                  arr[len] = 0;
                  len -= 1;
                  break;
              }
              possibleDups++;
          }
      }
  
      // Start backwards from the last element which would be part of new array.
      int last = len - possibleDups;
      // Copy zero twice, and non zero once.
      for (int i = last; i >= 0; i--) {
          if (arr[i] == 0) {
              arr[i + possibleDups] = 0;
              possibleDups--;
              arr[i + possibleDups] = 0;
          } else {
              arr[i + possibleDups] = arr[i];
          }
      }
  }
  ```

*******

