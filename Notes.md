### ***Concepts***

#### Time Complexity

$1 < klogn < log^kn < n< nlogn = log(n!) < n^k < k^n < n! < n^n$

******

*****

#### Tree

##### ***Terminology used in trees***

- Depth of node - the number of edges from the tree's root node to the node （从root到自己）
- Height of node - the number of edges on the longest path between that node and a leaf （从自己到leaf）
- Height of Tree - the height of its root node （从root到leaf）==决定了各operations的Time Complexity== 

********

##### ***Binary Search Tree的三个基本operations***

 ==TC都为$\mathcal{O}(heights)$, $heights$可以为$n$也可以为$logn$==

+ [0700] Search: Interation & Recursion

  从root开始，相等则返回；不等则利用BST的大小分布特性，选择进入哪一个subtree

+ [0701] Insertion: Interation & Recursion

  因为插入新node后，依旧要维持BST的property，所以第一步从root开始，做search操作，直到找到合适的空位

+ [0450] Deletion: Recursion

  1. If the target node has ***no child***, we can simply remove the node.
  2. If the target node has ***one child***, we can use its child to replace itself.
  3. If the target node has ***two children***, replace the node with its in-order successor or predecessor node and delete that node.

*******

##### ***Height-balanced (or self-balancing) BST***

+ Definition: 

  + the height of a balanced BST with $N$ nodes is always $logN$. 
  + the height of the two subtrees of every node never differs by more than 1. (每个节点的两个子树的深度相差不会超过1)

+ How to determine if a BST is height-balanced:

  + 利用Definition I: calculate the total number of nodes and the height of the tree;
  + 利用Definition II: validate the tree recursively, [0110].

+ Why Using a Height-balanced BST:

  This data structure supports the basic operations of BST, including search, insertion and deletion within $\mathcal{O}(logn)$ time even in worst case.

In Java, you may use a ==TreeSet== or a ==TreeMap== as a self-balancing BST.

******

*****

#### Data Structure

##### ***TreeSet 和 HashSet的区别***

+ 对于HashSet，Search / Insert / Delete all takes $\mathcal{O}(1)$；

  When there are ==too many elements with the same hash key==, it will cost $\mathcal{O}N)$ time complexity to look up for a specific element, where $N$ is the number of elements with the same hash key. ==此时，可以采用TreeSet，to improve the performance from $\mathcal{O}(N)$ to $\mathcal{O}(logN)$.==

+ 对于TreeSet，Search / Insert / Delete all takes $\mathcal{O}(logk$).

The essential difference between the hash set and the tree set is that ==keys in the tree set are ordered==.

*****

##### ***关于ArrayList和LinkedList的选用***

+ Search by value - `indexOf()`: Time complexity都为$\mathcal{O}(n)$，但是ArrayList将元素连续地存放在一起，而LinkedList则是在内存中随机存放，所以ArrayList实际运行会更快；
+ Get element by index - `get()`: ArrayList只需$\mathcal{O}(1)$ as the array has random access property, 可以直接访问任意index而不需要从头遍历（也是因为ArrayList在内存中是连续存储），但是LinkedList需要$\mathcal{O}(n)$，it needs to iterate through each element to reach a given index。

*****

##### ***PriorityQueue & Min/Max Heap***

+ PriorityQueue: 从队首获取元素时，总是获取优先级最高的元素

  + 创建：`PriorityQueue<> pq = new PriorityQueue<>();`

  + Common Method Summary:

    + `add()` / `offer()`
    + `clear()`
    + `contains()`
    + `remove()` / `poll()`：==返回的总是优先级最高的元素== 对于min heap，最返回最小的元素，反之亦然
    + `toArray()`
    + `peek()`
    + `size()`

  + `Comparator`自定义排序算法：==默认升序排列，即维护了一个min heap==

    ```java
    Queue<Object> priorityQueue = new PriorityQueue<>((optional)size, new Comparator<Object>(){
      @Override
      public int compare(Object o1, Object o2) {
        return o2.val-o1.val; // 当o2.val > o1.val时，return一个正数，即交换o1和o2的顺序，故实现max heap
      }
    });
    ```

+ Min/Max Heap:

  + Min Heap: a complete binary tree in which the value in each internal node is smaller than or equal to the values in the children of that node. （即：越小，优先级越高）
  + Max Heap: a complete binary tree in which the value in each internal node is bigger than or equal to the values in the children of that node. （即：越大，优先级越高）

******

##### HashMap定义时初始化

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

#### Algorithms

##### ***Floyd's Algorithm***

+ Phase 1: tortoise 一步一node；hare 一步二nodes    $\implies$    两者在intersection相遇
+ Phase 2: ==因为intersection并不一定是the entrance of the cycle，所以还需要Phase 2== tortoise 回到起点一步一node；hare 待在intersection一步一node    $\implies$    两者在entrance相遇

*LeetCode:* [[0142]](#[0142] Linked List Cycle II)     [[0287]](#[0287] Find the Duplicate Number)

******

#####  String-searching algorithms

Best [String-searching algorithms](https://en.wikipedia.org/wiki/String-searching_algorithm#Single-pattern_algorithms) have a linear execution time in average. 

The most popular ones are [Aho-Corasick](https://en.wikipedia.org/wiki/Aho–Corasick_algorithm), [KMP](https://en.wikipedia.org/wiki/Knuth–Morris–Pratt_algorithm) and [Rabin-Karp](https://en.wikipedia.org/wiki/Rabin–Karp_algorithm): 

+ Aho-Corasick is used by [fgrep](https://en.wikipedia.org/wiki/Grep#Variations)
+ KMP is used for [chinese string searching](https://www.aclweb.org/anthology/C96-2200)
+ Rabin-Karp is used for plagiarism detection and in bioinformatics to look for similarities in two or more proteins.

The first two are optimised for a single pattern search, and Rabin-Karp for a multiple pattern search.

******

##### ***Rabin-Karp Algorithm***

[Rabin-Karp algorithm](https://en.wikipedia.org/wiki/Rabin%E2%80%93Karp_algorithm) is used to perform a multiple pattern search. It's used for plagiarism detection and in bioinformatics to look for similarities in two or more proteins.

*实现：*将要查找的内容（字符串）通过散列函数转换为hash值。同样，将待查找的文本的内容也通过散列函数转换为hash值。通过hash值的对比，来确定待查找文本中是否含有目标字符串。

```java
function RabinKarp(string s[1..n], string pattern[1..m])
    hpattern := hash(pattern[1..m]);
    for i from 1 to n-m+1
        hs := hash(s[i..i+m-1])
        if hs = hpattern // 仅仅是hash值相同，可能是因为hash collision导致的伪重复 
            if s[i..i+m-1] = pattern[1..m] // 因此保险起见，还需要再检测子字符串是否真的相同
                return i
    return not found
```

*注意：*待查找文本内容中的sequences在转换为hash值时，可以直接通过确定好的散列函数实现，也可以通过已有字符串的hash值推测出来。后者 (***[Rolling Hash](https://en.wikipedia.org/wiki/Rolling_hash)***) 在要查找的sequence较长时，可以极大地节约时间。

*e.g.* 文本串为“abracadabra”，则首个可能匹配的串为“abr”，此时通过散列函数得到“abr”对应的hash值。而第二个要匹配的串为“bra”，此时可以通过“abr”的hash值来计算：只需减去首字母“a”的hash值，将整个串偏移一位再加上新的末位字母对应的散列值即可。

假设计算the first sequence of Length $L$ 的hash值的公式为
$$
\begin{split}
h_0 &=\sum_{i=0}^{L-1}c_ibase^{L-1-i} \\
&=c_0base^{L-1}+c_1base^{L-2}+ \dots + c_{L-2}base + c_{L-1}\\
&=(\dots((((c_0base + c_1)\times base+c_2)\times base+\dots + c_{L-2})\times base + c_{L-1}
\end{split} \tag{1.1}
$$
则通过*Rolling Hash*计算之后sequences的hash值可以有多种方式:

​	 $h_i$ : 第 $i$ 个sequence的hash值 $h_i$    	$h_{i-1} $ : 第 $i-1$ 个sequence的hash值

​	$c_{i-1}$ : 第 $i-1$ 个sequence的第一个字符       $c_{L-1+i}$ : 第 $i$ 个sequence的最后一个字符         

+ 第一种方式： $\begin{matrix} 
  ①\ remove\ the\ first\ digit \ \ ③\ add\ the\ last\ digit \\
  h_i = \underbrace{\overbrace{(h_{i-1}-c_{i-1}base^{L-1})} \times base} \overbrace{+c_{L-1+i}} \ (i\ge1)  \\
  ②\ shift\ left\ one\ digit\quad\quad\quad\quad\quad\ \
  \end{matrix}$

+ 第二种方式： $\begin{matrix} 
  ①\ shift\ left\ one\ digit \ \ ③\ add\ the\ last\ digit \\
  h_i = \overbrace{h_{i-1}\times base} \underbrace{-c_{i-1}base^L} \overbrace{+c_{L-1+i}} \ (i\ge1)  \\
  ②\ remove\ the\ first\ digit
  \end{matrix}$ 

  （第一种方式内部项乘开 / In a generalised way）<a name="第二种方式"></a>

*解释：*

+ *为什么在 remove the first digit 时，要减去 $c_{i-1}base^{L-1}$ ？*

  对于第 $i-1$ 个sequence，其第一位字符为 $c_{i-1}$。根据公式 $(1.1)$ 可知，在计算任一sequence的hash值时，其第一个字符的权重为 $base^{L-1}$。故从 $h_{i-1}$ 中移除第一位字符时，要减去 $c_{i-1}base^{L-1}$ 。

+ *为什么在 shift left one digit 时，要乘以 $base$ ？*

  类比Bitwise Operation中的移位，左移 $n$ 位相当于原值乘以 $base^n$。

+ *为什么在 add the last digit 时，可以直接加上 $c_{L-1+i}$ ？*

  根据公式 $(1.1)$ 可知，在计算任一sequence的hash值时，其最后一位字符的权重为 $base^0=1$。

*LeetCode:* [[0187]](#[0187] Repeated DNA Sequences)（采用的第二种方式的Rolling Hash）  [[1044]](#[1044] Longest Duplicate Substring)       [1062]

******

******

#### Iteration & Recursion

##### ***关于Recursion的Top-down和Bottom-up***

+ If recursive calls before conditional check, then it's bottom up. 
+ If recursive calls after conditional check, then it's top down.

******

******

#### 基本数据类型 / 数据结构 / 数据对象 之间的相互转换 (Conversion)

##### ***Integer 与 Binary String 相互转换***

+ `Integer.parseInt(String s, int radix)` - 输出一个十进制数，其中`int radix `表示`String s`的进制

  *e.g.* `Integer.parseInt("1011", 2)` - 输出二进制字符串"1011"在十进制下的数，即11

+ `Integer.toBinaryString(int i)` - 输出一个二进制字符串（即字符串的内容是二进制数，如"1011"）

  *e.g.* `Integer.toBinaryString(11)` - 输出十进制数11在二进制下的字符串，即"1011"

  ==注意：输出的二进制字符串的位数不定，原则是除十进制数0外，二进制首位都为1==

  | 十进制数 | 二进制字符串 |
  | :------: | :----------: |
  |    0     |      0       |
  |    3     |      11      |
  |    10    |     1010     |

******

********

#### Bitwise Operation 位操作

##### ***Two's complement 补码***

+ 表示：$-x=\neg x+1$ 

  ![](/Users/shanyonghao/IdeaProjects/LeetCodeProblems/Notes_img/bitwiseOperators_1.png)

+ 负数的补码：两种理解方式

  + 将==其对应正数==按位取反加1（此时的取反加一是<u>所有位</u>都取反且接受进位，包括符号位由 $0$ 变为 $1$）

    *e.g.* $-128$ 对应的正数为 $128$ : $128=(10000000)_2 \rightarrow 01111111 \rightarrow 10000000$

  + $2^{n-1}$ ($n$ 为位数) 与该负数绝对值的差值。

    *e.g.* $-128 = 2^{8-1}-|-128|=256-128=128$，所以它的补码为10000000。

+ 与有符号数 (signed binary number) 的区别: ==因为正数和 $0$ 的补码和其原码相同，只考虑负数==

  + Signed binary number的读法：

    第一位为符号位，其余位正常读：

    ![](/Users/shanyonghao/IdeaProjects/LeetCodeProblems/Notes_img/SignedBinaryNum.gif)

  + 补码的读法：依据==补码的补码为原码==

    已知是某负数的补码，直接取反加一（此时的取反加一同样是<u>所有位</u>都取反且接受进位，包括符号位由 $1$ 变为 $0$）即得原码。但要注意，*就像依据负数写出其补码时，是根据其对应的正数取反加一*，这里<u>得到的原码所表示的数同样是指原负数所对应的正数</u>，即还需再加上负号。
    $$
    \begin{gathered}
    10110101 \rightarrow 01001010 \rightarrow 01001011 = (75)_{10} \implies 原负数为-75 \\
    100 \rightarrow 011 \rightarrow 100 = (4)_{10} \implies 原负数为-4
    \end{gathered}
    $$

******

##### ***Bit Shifts 移位***

+ [Arithmetic shift](https://en.wikipedia.org/wiki/Arithmetic_shift)

  + arithmetic left shift: ==整体左移，左侧原数字不会移走，最右边补 $0$== 

    *e.g.* $0 <<1 \rightarrow 0$    ( $0$ 乘以或除以 $2^n$ 仍为0  $\implies$ 对 $0$ 左移右移 $n$ 位仍为 $0$ )

    ​	   $10111 << 1 \rightarrow 10111\underline{0}$

    Shifting left by $n$ bits on a <u>signed or unsigned</u> binary number has the effect of multiplying it by $2^n$.

  + arithmetic right shift: ==整体右移，但右侧原数字会移走，最左边空出来的位数由原符号位补齐==

    *e.g.* $0 >> 1 \rightarrow 0$

    ​       $10010111 >> 2 \rightarrow \underline{11}100101{1\mkern-9mu/}{1\mkern-9mu/}$

    Shifting right by $n$ bits on a <u>two's complement signed</u> binary number (我的理解是，计算机中无论正数还是负数都用其补码表示) has the effect of dividing it by $2^n$, but it always rounds down (towards negative infinity). 

+ Logical shift

  In a logical shift, zeros are shifted in to replace the discarded bits. Therefore, the logical and arithmetic left-shifts are exactly the same.

+ 对应Java中的运算符：
  + `<<` : arithmetic / logical left shift
  + `>>` : arithmetic right shift
  + `>>>` : logical right shift

*****

##### ***XOR (exclusive OR) 异或***

+ 表示：$\bigoplus$（数学符号）、^（程序符号）

+ 性质：

  1. $p \bigoplus q = (p \and \neg q) \or (\neg p \and q)$

  2. 如果a、b两个值不相同，则异或结果为1。如果a、b两个值相同，异或结果为0。

     $0$ ^ $0=0$          $0$ ^ $1=1$          $1$ ^ $1=0$           助记: ==不带进位的加法==

  3. 交换律：$a$ ^ $b=b$ ^ $a$

  4. 结合律：$a$ ^ $b$ ^ $c=a$ ^ $(b$ ^ $c)=(a$ ^ $b)$ ^ $c$

  5. ==归零律：$x$ ^ $x=0$==

  6. ==恒等律：$x$ ^ $0=x$==

  7. ==自反性：$a$ ^ $b$ ^ $a=b$==

  8. ==If $a$ ^ $b=c$ , then $a$ ^ $c=b$  and  $b$ ^ $c=a$ .==  (Prove: 两边同时异或 $a/b/c$ 中任一即可)    [0421]

+ 应用：

  1. 在不引入中间变量的情况下，交换两个变量的值：

     ```java
     int a = 5, b = 10;
     a = a ^ b;   
     b = a ^ b;   // b = a ^ b ^ b = a
     a = a ^ b;   // a = a ^ b ^ a = b
     ```

  2. 一个数组中包含从1~1000共1001个元素，只有一个元素值重复，其他均只出现一次。每个数组元素只能访问一次，且不用辅助存储空间，找出该元素？

     *Algorithm:* 将所有数异或后，得到的结果与$1$ ^ $2$ ^ $...$ ^ $999$ ^ $1000$ 的结果 $T$ 进行异或，得到的结果即为重复元素。

     *Analysis:* 假设第$n$个数重复，则数组中所有数的异或结果为$1$ ^ $2$ ^ $...$ ^ $n$ ^ $n$ ^ $...$ ^ $999$ ^ $1000$ $=$ $T$ ^ $n$。再与 $T$ 进行异或：$T$ ^ $n$ ^ $T$ $=n$。

  3. Google面试题的变形：一个数组存放若干整数，一个数出现奇数次，其余数均出现偶数次，找出这个出现奇数次的数？

     ```java
     public void fun() {
         int a[] = { 22, 38,38, 22,22, 4, 4, 11, 11 };
         int temp = 0;
         for (int i = 0; i < a.length; i++) {
             temp ^= a[i];
         }
         System.out.println(temp);
     }
     ```

  4. Facebook面试题：不使用加法运算的情况下，两数相加   [[0067]](#[0067] Add Binary)

+ LeetCode中的相关题目：

  [[0067]](#[0067] Add Binary)      [[0136]](#[0136] Single Number)     [[0137]](#[0137] Single Number II)     [[0187]](#[0187] Repeated DNA Sequences)     [[0260]](#[0260] Single Number III)     [0318]      [[0421]](#[0421] Maximum XOR of Two Numbers in an Array)     

******

##### ***Useful operations***

+ $x \& (-x)$ : Get / isolate the rightmost 1-bit

![](/Users/shanyonghao/IdeaProjects/LeetCodeProblems/Notes_img/bitwiseOperators_2.png)

+ $x \& (x-1)$ : Turn off (= set to 0) the rightmost 1-bit

![](/Users/shanyonghao/IdeaProjects/LeetCodeProblems/Notes_img/bitwiseOperators_3.png)

+ LeetCode中的相关题目：

  [[0231]](#[0231] Power of Two)      [[0260]](#[0260] Single Number III)

******













**********

### ***Problems***

#### Binary Search

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

#### Binary Tree Traversal

##### [0098] Validate Binary Search Tree

一共5种方法：

+ Solution 1：利用recursion判断当前root.val比其左子树中的最大值要大，比其右子树中的最小值要小，同时其左右子树也都为valid BST。因为每次recursion时，对于当前的root都要利用while循环去寻找其左子树中的rightmost作为最大值和其右子树中的leftmost作为最小值，所以costs $\mathcal{O}(n^2)$.

+ Solution2：同样是recursion，但是思路变了：对于当前root，其val作为其左子树的high（即其左子树中的最大值也要小于其val），其val作为其右子树的low（即其右子树中的最小值也要大于其val）。

+ Solution3：Solution2的Iteration实现

+ Solution4：==***推荐***== 利用==对于valid BST来说，其inorder traversal的结果是an ascending order的特性来判断是否为valid BST==，关键判断为

  ```java
  if (prev != null && root.val <= prev) {  // recursion: root
      return false;
  }
  // prev为当前root在遍历结果中的前一个node的值，如果当前root.val <= prev，即当前root的值小于等于其前面node的值，不再构成ascending order，故return false
  // 可不可以等于看题目具体要求，本题中对valid BST的要求是不可以等于
  // prev的update非常重要，见源代码
  ```

+ Solution5：Solution4的Iteration实现（Inorder traversal的iteration实现）

*****

##### [0173] Binary Search Tree Iterator

+ Solution 1: next()参考了[0285] Solution 3: 每次执行next()，都需要$\mathcal{O}(n)$，不好
+ Solution 2: 先执行一遍Inorder Traversal，再对遍历结果编写Iterator，最容易理解
+ Solution 3: ==***Clever Trick***==  总的过程相当于iterative inorder traversal，但是一开始只进行最左边的遍历，之后只有在调用next()时才继续剩下（向右）的遍历  ==Amortized $\mathcal{O}(1)$==

******

##### [0285] Inorder Successor in BST

+ Solution 1: 并没有利用BST inorder traversal得到的结果为升序的特性

  + successor的位置有两种情况：
    + case 1: p有right subtree
    + case 2: p无right subtree
  + Solution 1中Step 1多余，Step 2 case 2不如Solution 1 Advanced（Solution 1 Advanced中case 2的处理方法==推荐==）

+ Solution 2: 并没有利用BST inorder traversal得到的结果为升序的特性 （采用ArrayList）==可以为任一Binary Tree中的node寻找successor==

  + ==Tips: 关于ArrayList和LinkedList的选用==
    + Search by value - `indexOf()`: Time complexity都为$\mathcal{O}(n)$，但是ArrayList将元素连续地存放在一起，而LinkedList则是在内存中随机存放，所以ArrayList实际运行会更快；
    + Get element by index - `get()`: ArrayList只需$\mathcal{O}(1)$ as the array has random access property, 可以直接访问任意index而不需要从头遍历（也是因为ArrayList在内存中是连续存储），但是LinkedList需要$\mathcal{O}(n)$，it needs to iterate through each element to reach a given index。

+ Solution 3: ==最佳== 利用了BST的特性

  根据p.val和当前root.val的大小关系，来判断discard哪一半subtree，we can search for our inorder successor in logarithmic time rather than linear time (balanced BST / average case).

******

********

#### Bitwise Operation

##### [0067] Add Binary

==将两个binary strings相加==  *e.g.* Sting a = "11", String b = "1" $\rightarrow$ a + b = "11" + "1" = "100" (二进制数相加)

+ 最简单的做法：将a和b都转换为二进制数字，相加后在转化为二进制字符串：

  ```java
  return Integer.toBinaryString(Integer.parseInt(a, 2) + Integer.parseInt(b, 2));
  ```

  Drawbacks:

  + a和b所表示的二进制数很长时，其对应的十进制数可能超过Integer / Long / BigInteger的范围；

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

  - Convert a and b into integers x and y, x will be used to keep an answer, and y for the carry.
  - While carry is nonzero: `y != 0`:
    - Current answer without carry is XOR of x and y: `answer = x^y`.
    - Current carry is left-shifted AND of x and y: `carry = (x & y) << 1`.
    - Job is done, prepare the next loop: `x = answer`, `y = carry`.
  - Return x in the binary form.

  ```java
  public String addBinary(String a, String b) {
      // 1. convert a and b into integers x and y
      //    x will be used to keep an answer, y for the carry
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

******

********

#### Design Data Structure

##### [0208] Implement Trie (Prefix Tree)

Trie (can be pronounced "try" or "tree") or prefix tree is a tree data structure, which is used for retrieval of a key in a dataset of strings.     ==快速查询「某个字符串/字符前缀」是否存在==

图解一般表示为：使用「边」来代表有无字符，使用「点」来记录是否为「单词结尾」以及「其后续字符串的字符是什么」。如：

![](/Users/shanyonghao/IdeaProjects/LeetCodeProblems/Notes_img/[0208]_2.png)

+ 利用TrieNode构建Trie：

  但实际设计中，如果采用的是建立TrieNode结构节点，则是每个TrieNode有 $n$ 个空位（如下图，$n = 26$），每选择一个空位（如下图，root中的 $l$ 位被选择，而Node $l$ 中 $e$ 位继续被选择...）则在其中嵌入一个新的有 $n$ 个空位的TrieNode。

  ![](/Users/shanyonghao/IdeaProjects/LeetCodeProblems/Notes_img/[0208].png)

  由上图可知：

  1. Trie由一个root作为起始；
  2. Trie中每个node都有 $n$ 个空位，$n$ 的大小由实际存储的文本数据决定（可能除26个字母外，还需存储符号等）；
  3. 设置属性 `isEnd` 作为辅助，表示该node中是否继续嵌套其他node，使得设计的Trie支持题目要求的以下操作：
     + `search(String word)`：检查某word是否在Trie中；
     + `startWith(String prefix)`：检查Trie中是否有以prefix开头的单词。

  $\implies$ TrieNode的设计可以采用大小为 $n$ 的一维数组：

  ```java
  class TrieNode {
      private TrieNode[] node;
      private final int n = 26;
      private boolean isEnd;
  
      public TrieNode() {
          node = new TrieNode[n];
      }
  }
  ```

  $\implies$ 基于TrieNode的Trie：

  ```java
  class Trie {
    	private TrieNode root;
    
      /** Initialize your data structure here. */
      public Trie() {
          root = new TrieNode();
      }
      
      /** Inserts a word into the trie. */
      public void insert(String word) {
          TrieNode p = root;
          for (int i = 0; i < word.length(); i++) {
              char c = word.charAt(i);
              int idx = c - 'a';
              if (p.node[idx] == null) p.node[idx] = new TrieNode();
              p = p.node[idx];
          }
          p.isEnd = true;
      }
  
      /** Returns if the word is in the trie. */
      public boolean search(String word) {
          TrieNode p = root;
          for (int i = 0; i < word.length(); i++) {
              char c = word.charAt(i);
              int idx = c - 'a';
              if (p.node[idx] == null) return false;
              p = p.node[idx];
          }
          return p.isEnd;
      }
  
      /** Returns if there is any word in the trie that starts with the given prefix. */
      public boolean startsWith(String prefix) {
          TrieNode p = root;
          for (int i = 0; i < prefix.length(); i++) {
              char c = prefix.charAt(i);
              int idx = c - 'a';
              if (p.node[idx] == null) return false;
              p = p.node[idx];
          }
          return true;
      }
  }
  ```

  *Time Complexity:* $\mathcal{O}(words以及prefixes的总长度)$

  *Space Complexity:* 结点数量为 $n$，字符集大小为 $k(=26)$。复杂度为 $\mathcal{O}(nk)$

+ [利用二维数组构建Trie](https://leetcode-cn.com/problems/implement-trie-prefix-tree/solution/gong-shui-san-xie-yi-ti-shuang-jie-er-we-esm9/)：

  还可以使用二维数组实现Trie，因为内层数组之间关系平行，<u>故并不存在*表面上的*嵌套关系</u>。

  + 使用二维数组 `trie[行数][每行有多少种字符的可能，如26]` 来存储我们所有的单词字符，每一行相当于一个TrieNode。
  + 使用 `index` 自增，记录已经使用了二维数组中的哪些行（而 `++index` 则指明了此时二维数组中第一个未使用的TrieNode位于哪一行）。
  + 使用 `count[]` 数组记录某个格子被「被标记为结尾的次数」（当 `idx` 编号的格子被标记了 n 次，则有 `cnt[idx] = n`）。

  ```java
  class Trie {
      // 以下 static 成员独一份，被创建的多个 Trie 共用  ([0421]中涉及多次创建Trie)
      static int N = 100009; // 直接设置为十万级
      static int[][] trie = new int[N][26];
      static int[] count = new int[N];
      static int index = 0;
  
      // 在构造方法中完成重置 static 成员数组的操作
      // 这样做的目的是为减少 new 操作（无论有多少测试数据，上述 static 成员只会被 new 一次）
      public Trie() {
          for (int row = index; row >= 0; row--) { // 注意这里row=index，是因为index指示上一次Trie用了多少行
              Arrays.fill(trie[row], 0);
          }
          Arrays.fill(count, 0);
          index = 0;
      }
      
      public void insert(String s) {
          int p = 0;
          for (int i = 0; i < s.length(); i++) {
              int u = s.charAt(i) - 'a';
              if (trie[p][u] == 0) trie[p][u] = ++index;  // 要嵌套插入新的TrieNode，而此时第一个还未使用的TrieNode位于二维数组中的哪一行由++index指定
              p = trie[p][u];
          }
          count[p]++;
      }
      
      public boolean search(String s) {
          int p = 0;
          for (int i = 0; i < s.length(); i++) {
              int u = s.charAt(i) - 'a';
              if (trie[p][u] == 0) return false;
              p = trie[p][u];
          }
          return count[p] != 0;
      }
      
      public boolean startsWith(String s) {
          int p = 0;
          for (int i = 0; i < s.length(); i++) {
              int u = s.charAt(i) - 'a';
              if (trie[p][u] == 0) return false;
              p = trie[p][u];
          }
          return true;
      }
  }
  ```

  $\implies$ *Time Complexity:* $\mathcal{O}(words以及prefixes的总长度)$

  ​		  *Space Complexity:* $\mathcal{O}(1e5)$

  + *Analysis:*

    假设插入字符串 abc ，前面三行会被占掉：

    + 第 0 行 a 所对应的`trie[0][0] = ++0`，代表前缀 a 后面接的字符串会被记录在第 1 行；
    + 第 1 行 b 所对应的`trie[1][1] = ++1`，代表前缀 ab 后面接的字符串会被记录在第 2 行；
    + 第 2 行 c 所对应的`trie[2][2] = ++2`，代表前缀 abc 后面接的字符串会被记录在第 3 行。

    当再插入 abcl 时，这时候会先定位到 abc 的前缀行（第 3 行），将 l 所对应的`trie[3][11] = ++3`，代表前缀 abcl 后面接的字符串会被记录在第 4 行。

    紧接着，当插入 abl 时，则会定位到 ab 的前缀行（第 2 行），然后将 l 所对应的`trie[2][11] = ++4`，代表前缀 abl 后面接的字符串会被记录在第 5 行。

    紧接着，当插入 abclm 时，则会定位到 abcl 的前缀行（第 4 行），然后将 m 所对应的`trie[4][12] = ++5`，代表前缀 abclm 后面接的字符串会被记录在第 6 行。

    ... 

    假如经过一系列操作，此时 `index = 20` ，下一个要插入的字符串为 abd：先定位到 ab 的前缀行（第 2 行），将 d 所对应的`trie[2][3] = ++20`，代表前缀 abd 后面接的字符串会被记录在第 21 行。

  + *为什么行数估算是 1e5？*

    已知调用次数为$10^4$，传入的字符串长度为$10^3$。假设每一次的调用都是 `insert`，并且每一次调用都会使用到新的$10^3$行。那么我们的行数需要开到$10^7$。但由于我们的字符集大小只有26，因此不太可能在$10^4$次调用中都用到新的$10^3$行。而且正常的测试数据应该是 `search` 和 `startsWith` 调用次数大于 `insert` 才有意义的，一个只有 `insert`调用的测试数据，任何实现方案都能 AC。因此设定行数为$10^5$，直接开到$10^6$也没有问题。

  + *Summary:*

    没有频繁 new 对象的开销。但是需要根据数据结构范围估算我们的「二维数组」应该开多少行，而且由于通常对行的估算会很大，导致使用的二维数组开得很大，使用的空间通常是「TrieNode」方式的数倍。

    而且如果每次创建 Trie 对象时，都去创建数组的话，会比较慢，而且当样例多的时候甚至会触发 GC（因为 OJ 每测试一个样例会创建一个 Trie 对象）。That's why we used `static` to define the variables at the beginning.

+ [Application of Trie](https://leetcode-cn.com/problems/implement-trie-prefix-tree/solution/gong-shui-san-xie-yi-ti-shuang-jie-er-we-esm9/):

  首先，在纯算法领域，前缀树算是一种较为常用的数据结构。不过如果在工程中，不考虑前缀匹配的话，基本上使用 hash 就能满足。如果考虑前缀匹配的话，工程也不会使用 Trie 。一方面是字符集大小不好确定（题目只考虑 26 个字母，字符集大小限制在较小的 26 内）因此可以使用 Trie，但是工程一般兼容各种字符集，一旦字符集大小很大的话，Trie 将会带来很大的空间浪费。

  另外，对于个别的超长字符 Trie 会进一步变深。这时候如果 Trie 是存储在硬盘中，Trie 结构过深带来的影响是多次随机 IO，随机 IO 是成本很高的操作。同时 Trie 的特殊结构，也会为分布式存储将会带来困难。

  因此在工程领域中 Trie 的应用面不广。至于一些诸如「联想输入」、「模糊匹配」、「全文检索」的典型场景在工程主要是通过 ES (ElasticSearch) 解决的。而 ES 的实现则主要是依靠「倒排索引」。

******

******

#### Floyd's Algorithm

##### [0142] Linked List Cycle II

典型的Floyd's Algorithm

+ ==Floyd's Algorithm:== 
  + Phase 1: tortoise 一步一node；hare 一步二nodes    $\implies$    两者在intersection相遇
  + Phase 2: 因为intersection并不一定是the entrance of the cycle，所以还需要Phase 2: tortoise 回到起点一步一node；hare 待在intersection一步一node    $\implies$    两者在entrance相遇                
+ Solution2Abbr: ==写得好==

*****

##### [0287] Find the Duplicate Number

参考[[0142]](#[0142] Linked List Cycle II)

+ 如何将array转化为linked list：f(x) = nums[x]
+ Floyd's Algorithm: 
  + Phase 1: tortoise 一步一node；hare 一步二nodes    $\implies$    两者在intersection相遇
  + Phase 2: 因为intersection并不一定是the entrance of the cycle，所以还需要Phase 2: tortoise 回到起点一步一node；hare 待在intersection一步一node    $\implies$    两者在entrance相遇    

****

******

#### Greedy Algorithm

##### [0421] Maximum XOR of Two Numbers in an Array

+ 思路1：数组中两两相异或，保留最大值 $\implies$ $\mathcal{O}(n^2)$ for nested for loop.

+ 思路2：对于异或结果的大小，越高位的权重越大。且已知数组中的元素对应的二进制最大位数为 $L$ 位，则异或的最大可能值为 $\begin{matrix} \underbrace{ 111\ldots111 } \\ L\end{matrix}$ 。

  *e.g.*  nums = [3, 10, 5, 25, 2, 8]

  ​		In binary form: $3=(11)_2 \quad 10=(1010)_2 \quad 5=(101)_2 \quad 25=(11001)_2 \quad 2=(10)_2 \quad 8=(1000)_2$

   	  $\implies$ the length of the max number in the binary representation is $5$ 

  ​	   $\implies$ the maximum XOR is no more than $11111$

   故，==确定异或结果最大的位数 $L$ 后，从最高位开始，依次向下判断XOR结果的各位是否能为1==。

  ==**贪心算法：**== 追求XOR从最高位开始每位都为1

  *cont.* $3=(00011)_2 \quad 10=(01010)_2 \quad 5=(00101)_2 \quad 25=(11001)_2 \quad 2=(00010)_2 \quad 8=(01000)_2$

  ​		$\implies$ 若要确定XOR能否为 $(1****)_2$，只需要 $\begin{aligned}3=(0****)_2 \quad 10=(0****)_2 \quad 5=(0****)_2 \\ 25=(1****)_2 \quad 2=(0****)_2 \quad 8=(0****)_2 \end{aligned}$

  ​		$\implies$ 若要确定XOR能否为 $(11***)_2$，只需要 $\begin{aligned}3=(00***)_2 \quad 10=(01***)_2 \quad 5=(00***)_2 \\ 25=(11***)_2 \quad 2=(00***)_2 \quad 8=(01***)_2 \end{aligned}$

  ​		$\implies \dots$

  ```java
  public int findMaximumXOR(int[] nums) {
      // Step 1: 确定异或结果最大的位数L：数组中最大值所对应的二进制位数
      // 根据题目的限制，L不会超过31，所以也可以直接取L = 31
      int maxNum = nums[0];
      for(int num : nums) maxNum = Math.max(maxNum, num);
      int L = (Integer.toBinaryString(maxNum)).length();
  
   		// Step 2: 从最高位开始，依次向下判断XOR的各位
      int maxXor = 0, currXor;
      Set<Integer> prefixes = new HashSet<>();
      for(int i = L - 1; i > -1; --i) {  // 这里i从L-1开始是因为异或结果要从最高位开始依次向后确定
          // 通过左移一位为当前的maxXor在最右侧增加一位，e.g. 0变为0，1变为10
          maxXor <<= 1;
          // 将最右侧新增的一位置1，接下来检验这一位到底能不能为1，e.g. 0变为1，10变为11
          currXor = maxXor | 1;
          prefixes.clear();
          // 假如现在要判断XOR的前两位的值，则需要用到数组中元素的前两位的值
        	// 通过下面右移i位的操作，取到数组中各元素相应的前几位
        	// e.g. 现在currXor为11，那么就需要数组中所有元素的前两位。已知数组中元素对应二进制的最长位数为L，要取前两位，只需右移3位即可（01000变为00001，11001变为00011）==>> 这也是为什么最开始for循环i从L-1开始取
          for(int num: nums) prefixes.add(num >> i);
        	// 最右侧能否保留刚置的1，取决于数组中是否有两元素相对应位的异或结果==curXor
          // 变换下思路：将确定 是否有p1^p2 == currXor 变为 是否有p1 == currXor^p2.
          for(int p: prefixes) {
              if (prefixes.contains(currXor^p)) {
                // 新增的一位可以为1，更新maxXor
                maxXor = currXor;
                break;
              }
          }
      }
      return maxXor;
  }
  ```

  *Time Complexity:* 外层for循环 $L (\le 31)$ 次，内层两个for循环分别循环 $n$ 和 $\le n$ 次 $\implies \mathcal{O}(2Ln)$  *i.e.* $\mathcal{O}(n)$    

  *Space Complexity:* $\mathcal{O}(n)$ for HashSet

+ [思路2改进](https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array/solution/gong-shui-san-xie-noxiang-xin-ke-xue-xi-bmjdg/)：利用[[0421]](#[0208] Implement Trie (Prefix Tree))构建Trie的两种方式来取代HashSet  ==***最佳***==

  Hashset structure, used to store the prefixes in Approach 1, doesn't provide the functionality to cut off some paths which don't lead to the solution. 

  *e.g.* 

  ![](/Users/shanyonghao/IdeaProjects/LeetCodeProblems/Notes_img/[0421].png)

  + 上面的例子中，after two steps of max XOR computation $(11***)_2$ , it's quite obvious that $25=(11001)_2$ should be paried with $(00***)_2$ prefix, i.e. with $2=(00010)_2, 3=(00011)_2, 5=(00101)_2$ .       

    $\implies$ 在确定max XOR的第三位（及以后）时，$8=(01000)_2, 10=(01010)_2$ 就不再需要考虑了。

  + 同理，after three steps of max XOR computation $(111**)_2$ , it's quite obvious that $25=(11001)_2$ should be paried with $(001**)_2$ prefix, i.e. with $5=(00101)_2$ .       

    $\implies$ 在确定max XOR的第四位（及以后）时，$2=(00010)_2, 3=(00011)_2，8=(01000)_2, 10=(01010)_2$ 就不再需要考虑了。

  仅展示采用TrieNode实现的Trie：==尤其注意理解`getOppoVal(int x)`的思路==

  ==**贪心算法：**== 在Trie中找到与数x每位都尽可能相反的数

  ```java
  class Solution {
      class TrieNode {
          TrieNode[] node = new TrieNode[2];   // 不同于[0421]中每一个TrieNode有26种可能，此处只有2种可能：0和1
      }
    
      TrieNode root = new TrieNode();
    	
    	// 向Trie中插入数据 
      void add(int x) {   
          TrieNode p = root;
          for (int i = 31; i >= 0; i--) {
              int u = (x >> i) & 1;   // 取x的第i位值
              if (p.node[u] == null) p.node[u] = new TrieNode();
              p = p.node[u];
          }
      }
    	
    	// 在Trie中找出与数x每位都尽可能相反的数（对于某一位，若无相反值，则跳过）
    	// 假设x=3=(00011)_2，则找寻最高位为1，次高位为1，第三位为1，第四位为0，第五位为0的数
    	// 如果Trie中所有数的第三位都为0，则继续找寻第四位为0的数
      int getOppoVal(int x) {
          int ans = 0;
          TrieNode p = root;
          for (int i = 31; i >= 0; i--) {
              int a = (x >> i) & 1, b = 1 - a;
              if (p.node[b] != null) { // 对于第i位，存在相反值
                  ans |= (b << i);
                  p = p.node[b];
              } else { // 对于第i位，不存在相反值
                  ans |= (a << i);
                  p = p.node[a];
              }
          }
          return ans;
      }
    
      public int findMaximumXOR(int[] nums) {
          int ans = 0;
          for (int i : nums) {
              add(i);
              int j = getOppoVal(i); // j为Trie中从最高位开始，每位都尽可能与数i相反的数 ==>> 故i^j的结果才会尽可能的大
              ans = Math.max(ans, i ^ j);
          }
          return ans;
      }
  }
  ```

  *Time Complexity:* 外层for循环 $n$ 次，内层两个for循环都循环 $L (\le 31)$ 次 $\implies \mathcal{O}(2Ln)$  *i.e.* $\mathcal{O}(n)$    

  *Space Complexity:* $\mathcal{O}(n)$ for Trie

+ Summary:

  |   方法    | 数据结构 |               贪心算法                |
  | :-------: | :------: | :-----------------------------------: |
  |   思路2   | HashSet  |     追求XOR从最高位开始每位都为1      |
  | 思路2改进 |   Trie   | 在Trie中找到与数x每位都尽可能相反的数 |

******

******

#### Hash

##### [0217] Contains Duplicate

+ Solution 1 & 2: 最intuitive的思路：用HashSet $\mathcal{O}(n)$，两种判断方法：

  + `set.contains()`
  + `set.size() == 原数组的长度 ?`

+ Solution 3: 先sort $\mathcal{O}(nlogn)$，然后`nums[i] == nums[i+1] ?`

  当待判定的数组的长度不是很大(not sufficiently large)时，Solution 3会比Solution 1快

  ==The Big-O notation only tells us that for sufficiently large input, one will be faster than the other.==

*****

******

#### Rabin-Karp Algorithm

##### [0187] Repeated DNA Sequences

+ Solution 1: 最intuitive的方法，肯定是两层for循环嵌套，两两子字符串进行匹配。利用[[0001]](#[0001] Two Sum)中的==***Trick:*** *当需要两层嵌套的 for 循环时，考虑引入 HashSet / HashMap 转变为 one pass*==改进为one pass。

  $\implies \mathcal{O}((n-10)10)$

==下面的两个做法，都只是为了省去Solution 1中`substring(i, i+10)`的操作，从而降低TC==

+ Solution 2: [Rabin-Karp](#Rabin-Karp Algorithm) : Constant-time Slice Using Rolling Hash

  + Prerequisite: 将字母对应成数字：$'A' \rightarrow 0,\ 'C' \rightarrow 1,\ 'G' \rightarrow 2,\ 'T' \rightarrow 3 \implies base =4$

    *e.g.* $AAAAACCCCCAAAAA \rightarrow 000001111100000$

  + Step 1: 计算待查找字符串中首10位子字符串的hash值：==代码中的实现是依据公式的第三行==
    $$
    \begin{split}
    h_0 &=\sum_{i=0}^{L-1}c_i4^{L-1-i}\ (L=10,\ base=4) \\
    &=c_04^9+c_14^8+c_24^7+c_34^6+c_44^5+c_54^4+c_64^3 + c_74^2+c_84 + c_9\\
    &=((((((((c_0\times4 + c_1)\times 4+c_2)\times 4+c_3)\times 4+c_4)\times 4+c_5)\times4+c_6)\times4+c_7)\times4+c_8)\times4 + c_9
    \end{split}
    $$
    *e.g.* The first sequence of length 10 is $AAAAACCCCC$, so $c_{0,1,2,3,4}=0$ and $c_{5,6,7,8,9}=1$ are digits of $0000011111$.

  + Step 2: 通过[Rolling Hash的第二种方式](#第二种方式)依次计算之后的子字符串：
    $$
    h_1=(h_0 \times4-c_04^L)+c_{10} \ (L=10,\ base=4,\ i\ge1)\\
    \implies h_i=(h_{i-1}\times4-c_{i-1}4^L)+c_{L-1+i}
    $$
    *e.g.* $AAAAACCCCC \rightarrow AAAACCCCCA$ means $0000011111 \rightarrow 0000111110$, to remove leading 0 and to add trailing 0. 

  + Step 3: 同Solution 1中利用`HashSet seen`和`HashSet res`进行两两子字符串匹配。

  $\implies \mathcal{O}(n-10)$

+ Solution 3: Bit Manipulation : Constant-time Slice Using Bitmask

  + Prerequisite: 将字母对应成二进制：$'A' \rightarrow 00,\ 'C' \rightarrow 01,\ 'G' \rightarrow 10,\ 'T' \rightarrow 11$

    *e.g.* $AAAAACCCCCAAAAA \rightarrow 000000000011111111110000000000$

  + Step 1: 计算the first sequence of Length L的对应的长度为 $2L$ 的二进制串 (bitmask)

  + Step 2: 已知 $h_{i-1}$ 对应的长度为 $2L$ 的二进制串 (bitmask)，如何得出 $h_i$ 的二进制串 (bitmask)？

    ==借用Rolling Hash的思路==

    + Do left shift to free the last two bits: `bitmask <<= 2`
    + Add the new last bits: `biitmask != nums[L-1+i]`
    + Remove the original first two bits: `bitmask &= ~(3 << 2 * L)`

  + Step 3: 同Solution 1中利用`HashSet seen`和`HashSet res`进行两两子字符串匹配。

  *Analysis: Why `bitmask &= ~(3 << 2 * L)` can set the original first two bits to 0?*

  已知与上 `1<<n` 可以 set n-th bit equal to 1，则与上 `~(1<<n)` 可以 set n-th bit equal to 0。

  假如此时 $h_{i-1}$ 为 $\begin{matrix}\underbrace{110000\dots001001} \\ 20\end{matrix}$，左移两位且加上新的值 $'T'=11$ 后，变为 $\begin{matrix}\underbrace{110000\dots001001\underline{11}} \\ 22\end{matrix}$。现在要置最开始的两位 $11$ 为 $00$ ，则需要与上 $\begin{matrix} \underbrace{001111\dots\dots111111)} \\ 22\end{matrix}=$$\begin{matrix}\neg \underbrace{(110000\dots\dots000000)} \\ 22\end{matrix}$，而这个数即为 `~(3 << 2 * 10)`。

  $\implies \mathcal{O}(n-10)$ ==Performance最佳，但Solution 3主要还是借用了Rabin-Karp Algorithm的思路和Rolling Hash的思路==

******

##### [1044] Longest Duplicate Substring

这道题和[[0187]](#[0187] Repeated DNA Sequences)相比，惟一的区别是并没有明确sequence的length ([0187]中明确了 $L=10$ )，而是需要我们自己找出length可能的最大值，故将问题分为两个子问题：

1. Perform a search by a substring length in the interval from 1 to n-1;
2. Check if there is a duplicate substring of a given length L $\implies \mathcal{O}(n-L)$.

子问题2就是[0187] Rabin-Karp + Rolling Hash，不多考虑。子问题1第一反应是从n-1开始，依次循环递减至1。但假如length最大值为0 (无重复的substring)，则复杂度仍需 $\mathcal{O}(n)$。故借助Binary Search，可将复杂度降至 $\mathcal{O}(logn)$。因此该问题总的复杂度为 $\mathcal{O}((n-L)logn)=\mathcal{O}(nlogn)$。

*具体实现：*

+ 子问题1: 

  ```java
  int left = 1, right = n;
  while ()
  ```

+ 子问题2:

  因为本题中 $base=26$，且 $L_{max}=3 \times 10^4$，故在[0187]的代码上要注意以下几点：

  +  





******

******

#### Recursion

##### [0108] Convert (strictly increrasing) Sorted Array to (height-balanced) Binary Search Tree

题目关键是：height-balanced BST，所以root必须要选取array中排在中间的值。而root的左右子树则可以通过recursion的方式取得。

```java
public TreeNode helper(int left, int right) {
  	if (left > right) return null;

  	int rootIdx = left + (right - left) / 2;
  	TreeNode root = new TreeNode(nums[rootIdx]);
  	// root的左子树由[left, rootIdx-1]范围内的数组构成
  	root.left = helper(left, rootIdx-1);
  	// root的右子树由[rootIdx+1, right]范围内的数组构成
  	root.right = helper(rootIdx+1, right);

  	return root;
}
```

******

##### [0110] Balanced Binary Tree

+ Solution 1: Top-down

  1. 写一个getHeight(TreeNode node)
     $$
     height(n) =  \left\{
     \begin{array}{ll}
           -1 & if\ node\ is\ null \\
           max(height(n.left), height(n.right))+1 & otherwise \\
     \end{array} 
     \right.
     $$

  2. 从root开始，判断：

     + 两subtrees的height是否相差≤1；
     + 两subtrees是否为balanced （recursion）

  *缺点*：`isBalanced(node.left/right)`会对较低层的node重复调用`getHeight()`，类似Fibonacci Sequence的recursion.

  *分析*：假设$f(h)$表示高度为h的balanced BST中最少节点数，则$f(h) = f(h-1) + f(h-2) + 1$ (如果左子树的高度为h-1，则右子树的高度最小为h-2，不然不符合balanced BST的property)。Therefore, the height $h$ of a balanced tree is bounded by $\mathcal{O}(\log_{1.5}(n))$. With this bound we can guarantee that `getHeight()` will be called on each node $\mathcal{O}(\log n)$ times.   $\implies \mathcal{O}(nlogn)$  [LeetCode Analysis](https://leetcode.com/problems/balanced-binary-tree/solution/)

+ Solution 2: Bottom-up    ==***Clever Trick***==

  Since the height of a tree is always greater than or equal to 0, we use -1 as a flag to indicate if the subtree is not balanced. 在`getHeight()`中增加判断，以决定是否return -1. 因此，`isBalanced(node)`中不再需要recursion.    $\implies \mathcal{O}(n)$

******

##### [0235] Lowest Common Ancestor of a Binary Search Tree

利用[[0236]](#[0236] Lowest Common Ancestor of a Binary Tree) Solution 3的思路

*区别：*与[0236] Solution 3不同的是，在确定p和q是否位于当前node的子树中时，可以利用val的大小来判断

*****

##### [0236] Lowest Common Ancestor of a Binary Tree

三种方法都算得上巧妙

+ Solution 1: 理论上复杂度应该较高 (> $\mathcal{O}(n)$)，但实际performance不错（?）

  + 首先判断q是否在以p为root的subtree中 (`isInTheTree(p, q)`)，如果不在，则判断q是否在以p.parent (循环`findParent()`) 为root的subtree中    ==最容易想到和理解==
  + `isInTheTree()`和`findParent()`两个方法的实现可以参考，非常简洁 

+ Solution 2: Iteration with pointer parent   ==“笨“方法==

  + 首先，从root向下遍历，并且将tree中每个node和其parent作为一对放入HashSet parent中；
  + 然后，利用HashSet parent中的内容，得到p的ancestors (即：p的父节点，以及父节点的父节点...)；
  + 最后，判断q是否出现在p的ancestors中，如果没有，则q置为q的parent，继续判断

+ Solution 3: Recursion "Top-down"   ==代码最简练且performance优秀，但是较难理解==

  + 如果root不是p和q的LCA，那就判断root.left或者root.right是不是p和q的LCA

  + （从下往上的角度）更具体地解释代码，其实是从root开始，向下recursion的过程，为每个node都赋予left和right属性。如果left或者right不为null，则说明该node的左子树或者右子树中有p或者q（或者两者都有）。因此，当left child的left或者right不为null时，作为parent其left即不为null，同理，当right child的left或者right不为null时，其right即不为null。直到发现一个parent，它的left和right都不null，即这个node为p和q的LCA。

  + （从上往下的角度）

    + Start traversing the tree from the root node.
    + If both the nodes p and q are in the right subtree, then continue the search with right subtree starting step 1.
    + If both the nodes p and q are in the left subtree, then continue the search with left subtree starting step 1.
    + If both step 2 and step 3 are not true, this means we have found the node which is common to node p's and q's subtrees. and hence we return this common node as the LCA.

    *e.g.* node A有左孩子B和右孩子C，此时：

    ​       B的left不为null，但right为null，即B的左子树中有p / q $\implies$ A的left不为null

    ​       C的left不为null，但right为null，即C的左子树中有q / p $\implies$ A的right不为null

    ​      $\implies$ A是一个left和right都不为null的node $\implies$ A为LCA

+ [0235] & [0236]都是规定了p和q都存在于BST中，如果==不清楚p和q是否存在==时，最简单的方法是先在BST中搜索两者，takes $\mathcal{O}(n)$ in worst case，并不会提高复杂度。

*****

##### [0572] Subtree of Another Tree

Recursion

```java
if (s == null) return t== null;

if (s != null) {
  if (t == null) return false;  // 可省略
  
  if (s.val == t.val && isSameTree(s, t){  // 不能先判断s.val == t.val再判断isSameTree(s, t))
    // isSameTree()的逻辑：
    if (s == null) return t == null;
    if (s != null) {
      if (t == null) return false;  // 不可省略
      if (s.val != t.val) return false;
      if (s.val == t.val) return isSameTree(s.right, t.right) && isSameTree(s.left, t.left);
    }
  }
  
  if (s.val != t.val) return isSubtree(s.left, t) || isSubtree(s.right, t);
}
```

**********

******

#### Reverse Polish Notation

##### [0682] Baseball Game

+ String —> Integer: `Integer.valueOf(String) // new Integer(String): deprecated`

*****

******

#### Sliding Window

##### [0219] Contains Duplicate II

在索引值差k的范围内，有两数相同（`nums[i]==nums[j] && |i-j|≤k`）

+ Solution 1: 将每个数依次与其后的k个数（注意别超限）比较（是否相同） $\implies$ $\mathcal{O}(n^2$)

+ Solution 2 & 3: 都采用==滑动窗口==，但是一个用==HashSet==作为窗口，一个用==TreeSet (BST)==作为窗口 

  + 代码：

    ```java
    for (int i = 0; i < nums.length; i++) {    // O(n)
      	if (set.contains(nums[i])) return true;      // Search
      	set.add(nums[i]);                            // Insert/Add
      	if (set.size() > k) set.remove(nums[i-k]);   // Delete
    }
    return false;
    ```

  + 区别：

    + 对于HashSet，Search (当一个hash key只对应一个值时) / Insert / Delete all takes $\mathcal{O}(1)$；
    + 对于TreeSet，Search / Insert / Delete all takes $\mathcal{O}(logk$).

*******

*****

#### Sort

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

#### String

##### [0819] Most Common Word

+ Solution 1: word层面

  + 正则表达式的写法: 双斜杠转义

  + 判断word是否在String[] banned时: `Arrays.asList(banned).contains(word)`  有点蠢，`asList`大概率takes $\mathcal{O}(m)$, `contains` takes $\mathcal{O}(m)$, for循环n次，则takes $\mathcal{O}(nm)$, 不如提前用banned建立一个HashSet.

  + 自定义

    ```java
    Collections.sort(要sort的对象, new Comparator<>() {
    	@Override
    	public int compare() {
    	}
    })
    ```

+ Solution 2: char层面

******

******









********


*****

#### Misc.

##### [0001] Two Sum

Solution 2：如何one pass同时完成对HashMap的insert和search 

==***Trick:*** *当需要两层嵌套的 for 循环时，考虑引入 HashSet / HashMap 转变为 one pass*==  [[0187]](#[0187] Repeated DNA Sequences)

*******

##### [0021] Merge Two Sorted Lists

无论是Iteration还是Recursion，关键都是比较l1.val和l2.val

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

