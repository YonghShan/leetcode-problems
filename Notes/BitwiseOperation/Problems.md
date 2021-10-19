### Bitwise Operation

##### [0067] Add Binary

==将两个binary strings相加==  *e.g.* Sting a = "11", String b = "1" $\rightarrow$ a + b = "11" + "1" = "100" (二进制数相加)

+ 最简单的做法：将a和b都转换为二进制数字，相加后在转化为二进制字符串：

  ```java
  return Integer.toBinaryString(Integer.parseInt(a, 2) + Integer.parseInt(b, 2));
  ```

  Drawbacks:

  + a和b所表示的二进制数很长（`1 <= a.length, b.length <= 10^4`）时，其对应的十进制数可能超过Integer / Long / BigInteger的范围；

    如果字符串超过 3333 位，不能转化为 Integer；
    如果字符串超过 6565 位，不能转化为 Long；
    如果字符串超过 500000001 位，不能转化为 BigInteger。

  + quite low performance ($\mathcal{O}(n+m)$, where $n$ and $m$ are the lengths of the input binary strings a and b) in the case of large input numbers.

+ 最intuitive的做法：从最低位逐位相加，注意进位即可      $\mathcal{O}(max(n,m))$

  *LeetCode Approach 1: Bit-by-Bit Computation就是这个思路，但是解释和代码都写得吊诡。*

  ```java
  public String addBinary(String a, String b) {
      StringBuffer ans = new StringBuffer();
  
    	int i = a.length() - 1;
    	int j = b.length() - 1;
      int carry = 0;
      for(; i >= 0 || j >= 0; i--, j--) {  // 从最低位开始相加，同时决定了TC为O(max(n, m))
        	// a和b的长度不一定相等，较短的用0补齐
          carry += i >= 0 ? a.charAt(i) - '0' : 0;
          carry += j >= 0 ? b.charAt(j) - '0' : 0;
        	// 至此，carry的值是由上一位的进位、a和b当前位的值，一共三个数相加后得到的
        	// 一共有3种可能：0（三个数都为0），1（有一个数为1），2（有两个数为1），3（全为1）
        	// 如果此时为0，则ans中记0，carry为0；
        	// 如果此时为1，则ans中记1，carry为0；
        	// 如果此时为2，则ans中记0，carry为1；
        	// 如果此时为3，则ans中记1，carry为1.
          ans.append(carry % 2);
          carry = carry / 2;
      }
    
  		// 最后如果有进位，则在前方进行字符串拼接添加进位
      ans.append(carry == 1 ? carry : "");
      return ans.reverse().toString();
  }
  ```

+ 如果要求不可以addition，则使用bit manipulation实现：   $\mathcal{O}(n+m)$

  ==此方法的对象不仅可以是binary strings，也可以是Integers==（Facebook面试题：不使用加法运算的情况下，两数相加）

  *依据：*

  1. 不带进位的加法：异或
  2. 进位：与操作后左移一位

  *Algorithm:*

  - Convert a and b into [BigInteger](https://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html)s x and y, x will be used to keep an answer, and y for the carry.
  - While carry is nonzero: `y != 0`:
    - Current answer without carry is XOR of x and y: `answer = x^y`.
    - Current carry is left-shifted AND of x and y: `carry = (x & y) << 1`.
    - Job is done, prepare the next loop: `x = answer`, `y = carry`.
  - Return x in the binary form.

  ```java
  public String addBinary(String a, String b) {
      // 1. convert a and b into integers x and y
      //    x will be used to keep an answer, y for the carry
    	// 因为题目中说明了1 <= a.length, b.length <= 10^4，
      // 所以a/b对应的十进制数可能超过long，但不超过BigInteger的范围
      BigInteger x = new BigInteger(a, 2); 
      BigInteger y = new BigInteger(b, 2);
      BigInteger zero = new BigInteger("0", 2);
      BigInteger carry, answer;
      // 2. while carry is nonzero:
      while (y.compareTo(zero) != 0) {
          answer = x.xor(y); // 异或：不加进位的加法
          carry = x.and(y).shiftLeft(1); // 进位：与操作后，左移一位
          x = answer;
          y = carry;
      }
      // 3. return x in the binary form
      return x.toString(2);
  }
  ```

  `and(BigInteger val)` , `compareTo(BingInteger val)` , `xor(BigInteger val)` , `shiftLeft(BigInteger val)` 均为 Java BigInteger 自带方法。

******

##### [0136] Single Number

数组中除了一个数只出现1次，其他都出现2次。在==liner time==和==no extra space==的情况下，找出该数字。

```java
public int singleNumber(int[] nums) {
    int a = 0;
    for (int i : nums) a ^= i;
    return a;
}
```

******

##### [0137] Single Number II

数组中除了一个数只出现1次，其他都出现3次。在==liner time==和==no extra space==的情况下，找出该数字。

如果没有linear time和no extra space的限制：

+ HashSet：将数组中的元素放入HashSet中，比较数组元素之和和HashSet之和  -  linear time & extra space
+ HashMap：记录数组中每个元素及其对应的出现次数  -  linear time & extra space
+ Sort：排序原数组后，寻找`nums[i] != nums[i+1]`  -  nonlinear time & no extra space

为了满足限制，只能Bit Manipulation：

+ Solution 1: 确定只出现一次的数字（即答案）的每一个二进制位值（是0还是1）      $\mathcal{O}(32n)=\mathcal{O}(n)$

  *Algorithm:* 将数组中每一个元素的同一二进制位相加，得到的结果对3取余，即为答案在该位的二进制值

  *Analysis:* 对于数组中非答案的元素，每一个元素都出现了 3 次，对应着第 $i$ 个二进制位的 3 个 0 或 3 个 1，无论是哪一种情况，它们的和都是 3 的倍数（即和为 0 或 3）。此时加上答案在第 $i$ 位的值后，得到总和。将总和除以3，得到的即为答案在在第 $i$ 位的二进制值。

  ```java
  public int singleNumber(int[] nums) {
      int ans = 0;
      for (int i = 0; i < 32; ++i) {  // 数组中的元素都在int（即32位整数）范围内: O(32)
          int total = 0;
          for (int num : nums) { // O(n)
            	total += ((num >> i) & 1);  // 将num右移i位后和1与，即可得到该num在第i位的二进制值
          }
          if (total % 3 != 0) { // 如果取余结果为0，不需要更改ans中对应位的值，因为默认即为0
            	// 但是取余结果若为1，则需要更改
            	ans |= (1 << i);  // 将1向左移i位后，变为第i位为1，其余全为0的数；再与ans或，即可将ans的第i位置为1
          }
      }
      return ans;
  }
  ```

+ Solution 2：

  存在两个32位bitmaps `seen_once` & `seen_twice`：当某元第一次出现时，将其加入`seen_once`中；当该元素第二次出现时，将其从`seen_once`中删去，加入`seen_twice`中；当该元素第三次出现时，将其从`seen_twice`中删去。

  $\implies$ 某元素只出现一次，其最终留在 `seen_once` 中；某元素出现三次，则两个bitmaps都无其值。

  ![](/Users/shanyonghao/IdeaProjects/LeetCodeProblems/Notes_img/[0137].png)

  由此可写出代码：  ==按位与的优先级大于异或，所以加括号==

  ```java
  public int singleNumber(int[] nums) {
      int seenOnce = 0, seenTwice = 0;
  
      for (int num : nums) {
        // first appearence: add num to seen_once 
        // second appearance: remove num from seen_once, add num to seen_twice
        // third appearance: remove num from seen_twice
        seenOnce = ~seenTwice & (seenOnce ^ num);  
        seenTwice = ~seenOnce & (seenTwice ^ num);
      }
  
      return seenOnce;
  }
  ```

  *Analysis:* [LeetCode Analysis](https://leetcode-cn.com/problems/single-number-ii/solution/single-number-ii-mo-ni-san-jin-zhi-fa-by-jin407891/)

*******

##### [0231] Power of Two

+ $\mathcal{O}(logn)$ : 不停对2整除，看最后的商是否为1

  ```java
  public boolean isPowerOfTwo(int n) {
      if (n == 0) return false;
      while (n % 2 == 0) n /= 2;
      return n == 1;
  }
  ```

+ $\mathcal{O}(1)$ : Bitwise Operators

  已知一个数如果是2的次方，那么该数的二进制只有一位为1。
  $$
  \begin{aligned}
  1=(00000001)_2 \quad\quad 3=(00000011)_2 \\
  2=(00000010)_2 \quad\quad 5=(00000101)_2 \\
  4=(00000100)_2 \quad\quad 6=(00000110)_2
  \end{aligned}
  $$

  + $x \& (-x)$ : Get / isolate the rightmost 1-bit

    ```java
    public boolean isPowerOfTwo(int n) {
        if (n == 0) return false;
        long x = (long) n;
        return (x & (-x)) == x;  // 二进制只有一位是1
    }
    ```

  + $x \& (x-1)$ : Turn off (= set to 0) the rightmost 1-bit

    ```java
    public boolean isPowerOfTwo(int n) {
        if (n == 0) return false;
        long x = (long) n;
        return (x & (x - 1)) == 0;  // 二进制中只有一位是1，且被set to 0
    }
    ```

*****

##### [0260] Single Number III

数组中除了两个数（$x$、$y$）只出现1次，其他都出现2次。在==liner time==和==no extra space==的情况下，找出这两个数字，顺序随意。

+ 区分出现奇数次和偶次数的数：XOR

  ```java
  int bitmask = 0;
  for (int num : nums) bitmask ^= num;   // 循环结束后，bitmask = x ^ y
  ```

+ 剩下的任务是怎么将 $x$ 和 $y$ 分开：

  + 利用 $bitmask \& (-bitmask)$ isolate the rightmost 1-bit in `bitmask`：==同样也是 $x$ 和 $y$ 存在区别的rightmost 1-bit==

    ![](/Users/shanyonghao/IdeaProjects/LeetCodeProblems/Notes_img/[260].png)

    ```java
    // rightmost 1-bit diff between x and y
    int diff = bitmask & (-bitmask);
    ```

    *补充：* 

    Let $x=00000111$, $y=00010111$, then the rightmost 1-bit, which is different between $x$ and $y$ is: 
    $$
    \begin{aligned}
    00000111 \\
    \underline{XOR \quad 00010111} \\
    00010000 \\ 
    \underline{\ \ \quad \& \quad 11110000} \\
    000\check{\check{1}}0000
    \end{aligned}
    $$

  + 将该位上为1的元素全部异或：$y$ 因为该位上不为1，并没有被异或，此时最终的异或结果`x_bitmask`即为 $x$ （对于有些该位上同样不为1的，但出现两次的元素，也同样没有被异或，但并不影响）

    ![](/Users/shanyonghao/IdeaProjects/LeetCodeProblems/Notes_img/[260]_2.png)

    ```java
    int x_bitmask = 0;
    // bitmask which will contain only x
    for (int num : nums) {
      if ((num & diff) != 0) x_bitmask ^= num;
    }  // 循环结束后，x_bitmask = x
    ```

  + 还剩下 $y$ 的值：

    ```java
    // 已知bitmask = x ^ y, x_bitmaskt = x,
    // 则y = bitmask ^ x_bitmask = x ^ y ^ x = y
    return new int[]{x_bitmask, bitmask^x_bitmask};
    ```

*******

##### [0318] Maximum Product of Word Lengths

Given a string array `words`, return *the maximum value of* `length(word[i]) * length(word[j])` *where the two words do not share common letters*. If no such two words exist, return `0`.

*The naive straightforward solution:*

```java
class Solution {
  public boolean noCommonLetters(String s1, String s2){
    // TODO
  }

  public int maxProduct(String[] words) {
    int n = words.length;

    int maxProd = 0;
    for (int i = 0; i < n; ++i)
      for (int j = i + 1; j < n; ++j)
        if (noCommonLetters(words[i], words[j]))
          maxProd = Math.max(maxProd, words[i].length() * words[j].length());

    return maxProd;
  }
}
```

*Two tasks:*

+ Optimize function `noCommonLetters`: implementation of `noCommonLetters` with minimum time complexity;
+ Optimize numbers of comparisons: minimize the number of word comparisons. There is no need to always perform $\mathcal{O}(N^2)$ comparisons. Among all the strings with the same set of letters ($ab,aaaaabaabaaabb,bbabbabba$) it's enough to keep the longest one ($aaaaabaabaaabb$).

*具体实现：*

+ 对于the implementation of `noCommonLetters`有三种方法：

  + Naive solution : Check the characters in the first word one by one. For each character ensure that this character is *not* in the second word. $\implies \mathcal{O}(L_1 \times L_2)$

    ```java
    public boolean noCommonLetters(String s1, String s2){
      for (char c : s1.toCharAarray())
        if (s2.indexOf(ch) != -1) return false;
      return true;
    }
    ```

  + 将两个word转换为其各自对应的bitmasks，通过两个bitmasks的与操作判断 : $\implies \mathcal{O}(L_1 + L_2)$

    1. 利用 `(int)letter - (int)'a'` 计算word中每个字母对应的bit number，即字母a对应数字0，字母b对应数字1，依次类推：

       *e.g.* word为 “leetcode” $\rightarrow$ bit number for each letter is $[11, 4,4,19,2,14,3,4]$  

    2. 因为有26个字母，故bitmask有26 bits。依据Step 1中得到的bit number for each letter得到bitmask：

       *e.g.* 由$[11, 4,4,19,2,14,3,4]$可得： 
       $$
       \begin{matrix}
       z&y&x&w&v&u&\color{red}{t}&s&r&q&p&\color{red}{o}&n&m&\color{red}{l}&k&j&i&h&g&f&\color{red}{e}&\color{red}{d}&\color{red}{c}&b&a\\
       25&24&23&22&21&20&\color{red}{19}&18&17&16&15&\color{red}{14}&13&12&\color{red}{11}&10&9&8&7&6&5&\color{red}{4}&\color{red}{3}&\color{red}{2}&1&0 \\
       0&0&0&0&0&0&\color{red}{1}&0&0&0&0&\color{red}{1}&0&0&\color{red}{1}&0&0&0&0&0&0&\color{red}{1}&\color{red}{1}&\color{red}{1}&0&0
       \end{matrix}
       $$
       ​		$\implies$ bitmask for word "leetcode" is $00000010000100100000011100$

    ```java
    public int bitNumber(char ch) {
      return (int)ch - (int)'a';
    }
    
    public boolean noCommonLetters(String s1, String s2) {
      int bitmask1 = 0, bitmask2 = 0;
      for (char ch : s1.toCharArray())
        // add bit number bit_number in bitmask
        bitmask1 |= 1 << bitNumber(ch);
      for (char ch : s2.toCharArray())
        bitmask2 |= 1 << bitNumber(ch);
    
      return (bitmask1 & bitmask2) == 0;
    }
    ```

  + Bitmasks + Precomputation：In the previous approach one computes a bitmask of each word N times. In fact, each bitmask could be precomputed just once, memorised and then used for the runtime comparison in a constant time.

    ```java
    public int bitNumber(char ch) {
      return (int)ch - (int)'a';
    }
    
    public int maxProduct(String[] words) {
      int n = words.length;
      int[] masks = new int[n];
    
      int bitmask = 0;
      for (int i = 0; i < n; ++i) {    // O(L), where L is a total length of all words together
        bitmask = 0;
        for (char ch : words[i].toCharArray()) {
          bitmask |= 1 << bitNumber(ch);
        }
        masks[i] = bitmask;
      }
    
      int maxVal = 0;
      for (int i = 0; i < n; ++i)
        for (int j = i + 1; j < n; ++j)    // O(n^2)
          if ((masks[i] & masks[j]) == 0)   // O(1)
            maxVal = Math.max(maxVal, words[i].length() * words[j].length());
    
      return maxVal;
    }
    ```

    *Time Complexity:* $\mathcal{O}(n^2+L)$

    *Space Complexity:* $\mathcal{O}(n)$ for array

+ 在上面代码的基础上，进行第二个优化：借助`hashmap(bitmask, maxLength)` (一个bitmask实际表示的是用到了哪些字母，而这些字母可以有很多的不同组合，但只记录最长组合的长度)

  ```java
  public int bitNumber(char ch) {
    return (int)ch - (int)'a';
  }
  
  public int maxProduct(String[] words) {
    HashMap<Integer, Integer> hashmap = new HashMap<>();
  
    int bitmask = 0;
    for (String word : words) {    // O(L), where L is a total length of all words together
      bitmask = 0;
      for (char ch : word.toCharArray()) {
        bitmask |= 1 << bitNumber(ch);
      }
      hashmap.put(bitmask, Math.max(hashmap.getOrDefault(bitmask, 0), word.length()));
    }
  
    int maxVal = 0;
    for (int x : hashmap.keySet())
      for (int y : hashmap.keySet())    // O(n^2)
        if ((x & y) == 0)   // O(1)
          maxVal = Math.max(maxVal, hashmap.get(x) * hashmap.get(y));
  
    return maxVal;
  }
  ```

  *Time Complexity:* $\mathcal{O}(n^2+L)$      *因为Java HashMap的性能比起array差非常多，所以性能并没有很大提升。其他语言会好很多*

  *Space Complexity:* $\mathcal{O}(n)$ for Hashmap

******

********

### 