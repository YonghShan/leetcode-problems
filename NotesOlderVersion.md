## ***Concepts***

### Time Complexity

$1 < klogn < log^kn < n< nlogn = log(n!) < n^k < k^n < n! < n^n$

******

*****

### Algorithms

#### ***Floyd's Algorithm***

+ Phase 1: tortoise 一步一node；hare 一步二nodes    $\implies$    两者在intersection相遇
+ Phase 2: ==因为intersection并不一定是the entrance of the cycle，所以还需要Phase 2== tortoise 回到起点一步一node；hare 待在intersection一步一node    $\implies$    两者在entrance相遇

*LeetCode:* [[0142]](#[0142] Linked List Cycle II)     [[0287]](#[0287] Find the Duplicate Number)

******

####  String-searching algorithms

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

| Variable  |             Meaning             |  Variable   |             Meaning             |
| :-------: | :-----------------------------: | :---------: | :-----------------------------: |
|   $h_i$   |    第 $i$ 个sequence的hash值    |  $h_{i-1}$  |   第 $i-1$ 个sequence的hash值   |
| $c_{i-1}$ | 第 $i-1$ 个sequence的第一个字符 | $c_{L-1+i}$ | 第 $i$ 个sequence的最后一个字符 |

+ 第一种方式： $\begin{matrix} 
  \text{① remove the first digit}\ \ \text{③ add the last digit}\\
  h_i = \underbrace{\overbrace{(h_{i-1}-c_{i-1}base^{L-1})} \times base} \overbrace{+c_{L-1+i}} \ (i\ge1)  \\
  \text{② shift left one digit}\quad\quad\quad\quad\quad\ \
  \end{matrix}$

+ 第二种方式： $\begin{matrix} 
  \text{① shift left one digit}\ \ \text{③ add the last digit}\\
  h_i = \overbrace{h_{i-1}\times base} \underbrace{-c_{i-1}base^L} \overbrace{+c_{L-1+i}} \ (i\ge1)  \\
  \text{② remove the first digit}
  \end{matrix}$ 

  （第一种方式内部项乘开 / In a generalised way）<a name="第二种方式"></a>

*解释：*

+ *为什么在 remove the first digit 时，要减去 $c_{i-1}base^{L-1}$ ？*

  对于第 $i-1$ 个sequence，其第一位字符为 $c_{i-1}$。根据公式 $(1.1)$ 可知，在计算任一sequence的hash值时，其第一个字符的权重为 $base^{L-1}$。故从 $h_{i-1}$ 中移除第一位字符时，要减去 $c_{i-1}base^{L-1}$ 。

+ *为什么在 shift left one digit 时，要乘以 $base$ ？*

  类比Bitwise Operation中的移位，左移 $n$ 位相当于原值乘以 $base^n$。

+ *为什么在 add the last digit 时，可以直接加上 $c_{L-1+i}$ ？*

  根据公式 $(1.1)$ 可知，在计算任一sequence的hash值时，其最后一位字符的权重为 $base^0=1$。

*LeetCode:* [[0187]](#[0187] Repeated DNA Sequences)（采用的第二种方式的Rolling Hash）  [[1044]](#[1044] Longest Duplicate Substring)（采用的第二种方式的Rolling Hash）   [1062]

******

******

### Bitwise Operation 位操作

#### ***Two's complement 补码***

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

#### ***Bit Shifts 移位***

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

#### ***XOR (exclusive OR) 异或***

+ 表示：$\oplus$（数学符号）、^（程序符号）

+ 性质：

  1. $p \oplus q = (p \and \neg q) \or (\neg p \and q)$

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

#### ***Useful operations***

+ $x \& (-x)$ : Get / isolate the rightmost 1-bit

![](/Users/shanyonghao/IdeaProjects/LeetCodeProblems/Notes_img/bitwiseOperators_2.png)

+ $x \& (x-1)$ : Turn off (= set to 0) the rightmost 1-bit

![](/Users/shanyonghao/IdeaProjects/LeetCodeProblems/Notes_img/bitwiseOperators_3.png)

+ LeetCode中的相关题目：

  [[0231]](#[0231] Power of Two)      [[0260]](#[0260] Single Number III)

******

******

### 基本数据类型 / 数据结构 / 数据对象 之间的相互转换 (Conversion)

#### ***Integer 与 Binary String 相互转换***

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

#### List 与 Array 相互转换

+ `List<String>` 与 `char[]`：

  ```java
  for (int i = 0; i < List.size(); i++) char[i] = List.get(i).toCharArray();
  ```

  

+ `````

******

********

### Data Structure

#### ***TreeSet 和 HashSet的区别***

+ 对于HashSet，Search / Insert / Delete all takes $\mathcal{O}(1)$；

  When there are ==too many elements with the same hash key==, it will cost $\mathcal{O}N)$ time complexity to look up for a specific element, where $N$ is the number of elements with the same hash key. ==此时，可以采用TreeSet，to improve the performance from $\mathcal{O}(N)$ to $\mathcal{O}(logN)$.==

+ 对于TreeSet，Search / Insert / Delete all takes $\mathcal{O}(logk$).

The essential difference between the hash set and the tree set is that ==keys in the tree set are ordered==.

*****

#### ***关于ArrayList和LinkedList的选用***

+ Search by value - `indexOf()`: Time complexity都为$\mathcal{O}(n)$，但是ArrayList将元素连续地存放在一起，而LinkedList则是在内存中随机存放，所以ArrayList实际运行会更快；
+ Get element by index - `get()`: ArrayList只需$\mathcal{O}(1)$ as the array has random access property, 可以直接访问任意index而不需要从头遍历（也是因为ArrayList在内存中是连续存储），但是LinkedList需要$\mathcal{O}(n)$，it needs to iterate through each element to reach a given index。

*****

#### ***PriorityQueue & Min/Max Heap***

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

### Dynamic Programming

#### Techniques

+ **如何确定可以使用动态规划来解决的？**

  通常我们要从**「有无后效性」**进行入手分析。

  如果对于某个状态，我们可以只关注状态的值，而不需要关注状态是如何转移过来的话，那么这就是一个无后效性的问题，可以考虑使用 DP 解决。==后续变化只取决于当前状态 而与当前状态是如何来的无关。==

  另外一个更加实在的技巧，我们还可以通过 **数据范围** 来猜测是不是可以用 DP 来做。

  ==因为 DP 是一个递推的过程，因此如果数据范围是 $10^5～10^6$ 的话，可以考虑是不是可以使用一维 DP 来解决；如果数据范围是 $10^2～10^3$​​ 的话，可以考虑是不是可以使用二维 DP 来做；如果不考虑加入记忆化的DFS，数据范围在 $30$​​​​ 以内。==

  如果题目是需要求得方案数（[[0062]](#[0062] Unique Paths)），可以用DP。==但是如果题目要求枚举具体方案（的细节）则一般不使用DP，因为不满足无后效性（即需要关注状态是如何转移过来的）。要求求得所有的方案，采用回溯算法。但是如果求最佳方案的话，还是能用 DP，只不过在 DP 过程中还需要额外的数据结构（例如数组）来记录路径。== [[0064]](#[0064] Minimum Path Sum)

+ **如何确定本题的状态定义的？**

  说实话，DP 的状态定义很大程度是靠经验去猜的。

  虽然大多数情况都是猜的，但也不是毫无规律，相当一部分题目的状态定义是与**「结尾」**和**「答案」**有所关联的。

+ **如何确定状态转移方程的？**

  通常来说，如果我们的状态定义猜对了，**「状态转移方程」**就是对**「最后一步的分情况讨论」**。

  如果我们有一个对的**「状态定义」**的话，基本上**「状态转移方程」**就是呼之欲出。

  因此一定程度上，**状态转移方程可以反过来验证我们状态定义猜得是否正确**：

  如果猜了一个状态定义，然后发现无法列出涵盖所有情况（不漏）的状态转移方程，多半就是**状态定义猜错了，赶紧换个思路，而不是去死磕状态转移方程**。

+ **对状态转移的要求是什么？**

  状态转移是要做到**「不漏」**还是**「不重不漏」**取决于问题本身：

  + 如果是求最值的话，只需要确保**「不漏」**即可，因为重复不影响结果。

  + 如果是求方案数的话，需要确保**「不重不漏」**。

+ **如何分析动态规划的时间复杂度的？**

  对于动态规划的复杂度/计算量分析，有多少个状态，复杂度/计算量就是多少。

  因此一维 DP 的复杂度通常是线性的，而二维 DP 的复杂度通常是平方的。
  
+ **如何从二维数组降到一维数组或者其中一维降为常数？**

  + 「一维空间优化」：经过分析，根据状态依赖，调整迭代/循环的方向
  + 「滚动数组」：当计算「某一行」的时候只需要依赖「前一行」时，可以根据==当前的行号是偶数还是奇数来交替使用第 $0$​ 行还是第 $1$​ 行==。因此将其中一维直接改成 $2$​​​，并将任何在该维的 `f[i]` 改成 `f[i&1]` 或者 `f[i%2]` 即可（推荐前者，在不同架构的机器上，运算效率更加稳定）。
  
  ==**注意：**== 「滚动数组」只会降低「空间复杂度」，不会改变「时间复杂度」。但是，「一维空间优化」同样可能降低「时间复杂度」。
  
  [[0120]](#[0120] Triangle)

*******

#### Memoization

记忆化搜索，其本质是「递归」（通常是**自顶而上**的解决）。是降低递归复杂度的技巧（==将递归的复杂度降到和DP一致==），可以保证递归的做法在数据范围较大的情况下也可以AC。比如，DFS 通常要求数据范围在30左右，而利用 Memoization 辅助的DFS则可以通过数据范围 $10^2$​​ 的题目。

需要掌握 Memoization 最重要的一个原因是，其是「动态规划」的前置思考。当在不能轻易地猜到「状态定义」以及推导「状态转移方程」时，都可以先使用Memoization求解，再改成DP。==因此，能够使用Memoization求解的前提也是：**问题本身具有无后效性**。==

**如何根据「Memoization」解法写出「Dynamic Programming」解法：**

1. 从 DFS 方法签名出发。分析哪些入参是可变的，将其作为 DP 数组的维度；将返回值作为 DP 数组的存储值。

   $\implies$​ 对应DP的「状态定义」

2. 从 DFS 的主逻辑可以抽象中单个状态的计算方法。

   $\implies$​ 对应DP的「状态转移方程」

到目前为止，我们已经掌握了两种求解「动态规划」问题的方法：

1. 根据经验猜一个「状态定义」，然后根据「状态定义」去推导一个「状态转移方程」。

2. 先写一个「记忆化搜索」解法，再将「记忆化搜索」改写成「动态规划」。

由于「动态规划」的状态定义猜测，是一门很讲求经验的技能。因此对于那些你接触过的模型，我建议你使用第一种方式。

如果遇到一道你从来没接触过的题目时，我建议你先想想「记忆化搜索」该如何实现，然后反推出「动态规划」。

**这里说的想想「记忆化搜索」该如何实现，不需要真正动手实现一个「记忆化搜索」解法，而只需要想清楚，如果使用「记忆化搜索」的话，我的 DFS 函数签名如何设计即可。**

**当搞清楚「记忆化搜索」的函数签名设计之后，「状态定义」部分基本就已经出来了，之后的「状态转移方程」就还是一样的分析方法。**

当然，如果你觉得「记忆化搜索」更好实现的话，大可直接使用「记忆化搜索」求解，不一定需要将其转化为「动态规划」。因为由「记忆化搜索」直接转过来的「动态规划」，两者复杂度是一样的。而且通常「记忆化搜索」的实现难度通常要低很多。

+ **Memoization会比DP快的原因:**

  在 DP 中我们很少会去考虑剪枝，或者很难得到一个很好的剪枝效果。而 DFS 通常会一直往下搜索，得到一个「合法值」，然后利用这个合法值直接减掉一些不必要的递归树。但是 DP 本质是通过 BFS 来得到起始状态到目标状态的拓扑序，无法像 DFS 那样先得到一个合法值。

  另外，以[1575]和[0576]为例，在使用Memoization时，都可以对base case剪枝。DP 解法却更近似于原始未剪枝的Memoizaition。

+ **总结：**

  ==Recursion with Memoization对于大部分的input，其速度都会更快（即使两者复杂度相同），但DP更利于debug，因为可以将DP数组直观地输出，发现哪里出错。==
  
  「记忆化搜索」和「动态规划」思想上主要区别还是在于「自顶向下」和「自底向上」，实现上则是「递归」和「递推」。

[[1575]](#[1575] Count All Possible Routes)     [[0576]](#[0576] Out of Boundary Paths)

********

#### Applications

##### Pascal's Triangle

[[0118]](#[0118] Pascal's Triangle)     [[0119]](#[0119] Pascal's Triangle II)     [[0120]](#[0120] Triangle)

******

##### 路径问题

[宫水三叶的刷题日记](https://mp.weixin.qq.com/s/flnaRo6VnvkeUQoRDkin9w)

###### 类型一

**特定「起点」，明确且有限的「移动方向」（转移状态），求解所有状态中的最优值。**

*解释：*给定了某个「形状」的数组（三角形或者矩形），使用 **题目给定的起点** 或者 **自己枚举的起点** 出发，再结合题目给定的具体转移规则（往下方/左下方/右下方进行移动）进行转移。

[[0062]](#[0062] Unique Paths)     [[0063]](#[0063] Unique Paths II)     [[0064]](#[0064] Minimum Path Sum)     [[0120]](#[0120] Triangle)     [[0931]](#[0931] Minimum Falling Path Sum)     [[1289]](#[1289] Minimum Falling Path Sum II)     [[1301]]()

###### 类型二

只是告诉了我们移动规则，没有告诉我们具体该如何移动，即**不明确但有限的「移动方向」**。

[[1575]](#[1575] Count All Possible Routes)     [[0576]]()     

###### Template

```java
public int DP(int[][] arr) {
  // Step 1: 定义dp array
  int n = arr.length;
  int[][] f = new int[n][n];

  // Step 2: 初始化dp array，两种情况：
  if (起点固定为arr[0][0]) {
    f[0][0] = arr[0][0]
  } else if (起点为第一行任一元素) {
    for (int i = 0; i < n; i++) f[0][i] = arr[0][i];
  }

  // Step 3: 从第二行进行状态转移
  for (int i = 1; i < n; i++) { // 从f的第二行开始更新
    for (int j = 0; j < n; j++) {
      f[i][j] = Integer.MAX_VALUE;
      int val = arr[i][j];
      // 具体根据移动方向的限制而定：正下方/左下方/右下方/非同列
      ...
    }
  }

  // Step 4: 取结果，两种情况：
  if (终点固定为arr[n-1][n-1]) {
    return f[n-1][n-1];
  } else if (终点为f array最后一行中的最小值) {
    int ans = Integer.MAX_VALUE;
    for (int i = 0; i < n; i++) ans = Math.min(ans, f[n-1][i]);
    return ans;
  }
}
```

******

##### 背包问题

**背包问题是「动态规划」中十分经典的一类问题，背包问题本质上属于组合优化的「 NP完全问题」。**

可以将「 NP完全问题」简单理解为「无法直接求解」的问题。例如「分解质因数」问题，我们无法像四则运算（加减乘除）那样，按照特定的逻辑进行求解。只能通过「穷举」+「验证」的方式进行求解。

既然本质上是一个无法避免「穷举」的问题，自然会联想到「动态规划」，事实上背包问题也同时满足「无后效性」的要求。**这就是为什么「背包问题」会使用「动态规划」来求解的根本原因。**

如果按照常见的「背包问题」的题型来抽象模型的话，「背包问题」大概是对应这样的一类问题：**泛指一类「给定价值与成本」，同时「限定决策规则」，在这样的条件下，如何实现价值最大化的问题。**

******

###### 01背包

指**给定物品价值与体积**（对应了「给定价值与成本」），**在规定容量下**（对应了「限定决策规则」）**如何使得所选物品的总价值最大**。

> 有 $N$​​ 件物品和一个容量是 $C$​​ 的背包。每件物品有且只有一件。第 $i$​​ 件物品的体积是 $v[i]$​​，价值是 $w[i]$​​​。
>
> 求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。

*e.g.* 

```java
Input: N = 3, C = 5, v = [4,2,3], w = [4,2,3]
Output: 5
Explanation: 不选第一件物品，选择第二件和第三件物品，可使价值最大。
```

如果使用在 [路径问题](#路径问题) 中学到的「技巧解法」来分析：设计一个 DFS 函数对所有的方案进行枚举的话，大概如下：

```java
int dfs (int[] v, int[] w, int i, int c);
```

其中 $v[]$​ 和 $w[]$​ 对应了输入的「物品体积」和「物品价值」，属于不变参数，无须考虑。而 $i$ 和 $c$ 分别代表「当前枚举到哪件物品」和「可使用的背包容量」。返回值（即「状态定义」）则是：**当有物品 $1$、 $2$、... $i$ 可供选择（即仅考虑前 $i$ 件物品）且可使用的背包容量不超过 $c$ 时， 背包的最大价值。**

那么根据变化参数和返回值，可以抽象出 dp 数组：**一个二维数组，其中一维代表「当前枚举到哪件物品」，另外一维「当前可使用的背包容量」，数组内容是「最大价值」。**第一维的取值范围为 $i\in[0,N-1]$​​​​​，第二维的取值范围为 $c \in [0, C]$​​​​​。**因此，$dp[N-1][C]$​​​​​ 即为答案。**

当有了「状态定义」之后，再根据「最后一步」选择来推导「状态转移方程」：**只需要考虑第 $i$​ 件物品如何选择即可：对于第 $i$​ 件物品，我们有「选」和「不选」两种决策。**

+ 「不选」方案的「最大价值」：**「不选」等效于我们只考虑前 $i-1$ 件物品，当前容量为 $c$ 的情况下的最大价值，即 $dp[i-1][c]$。**

+  「选」方案的「最大价值」：「选」方案必须满足一个前提：「当前剩余的背包容量」$\ge$「物品 $i$ 的体积」。**如果选了第 $i$ 件物品的话，代表消耗了 $v[i]$ 的背包容量，获取了  $w[i]$ 的价值，那么留给前 $i-1$ 件物品的背包容量就只剩 $c-v[i]$，因此最大价值为 $dp[i-1][c-v[i]]+w[i]$。**

在「选」和「不选」之间取最大值，就是「考虑前 $i$​ 件物品，使用容量不超过 $c$​​」的条件下的「背包最大价值」。可得「状态转移方程」为：
$$
dp[i][c]=max(dp[i-1][c],dp[i-1][c-v[i]]+w[i])
$$

+ $dp[N][C+1]$​​​​ 解法

  ```java
  public int maxValue(int N, int C, int[] v, int[] w) {
    // 根据两个维度的取值范围new dp数组
  	int[][] dp = new int[N][C+1];
    // 初始化：先处理「考虑第一件物品」的情况（即dp数组的第一横行的初始值）
    for (int c = 0; c <= C; i++) dp[0][c] = c >= v[0] ? w[0] : 0;
    // 状态转移：再处理「考虑其余物品」的情况
    for (int i = 1; i < N; i++) {
      for (int c = 0; c <= C; c++) {
        // 不选第i件物品
        int ns = dp[i-1][c];
        // 满足前提：选第i件物品
        int s = c >= v[i] ? dp[i-1][c-v[i]] + w[i] : 0;
        dp[i][c] = Math.max(ns, s);
      }
    }
    return dp[N-1][C];
  }
  ```

  针对上面的例子，最终dp数组的内容为：
  $$
  \begin{gathered}
  \small\color{blue}
  \begin{matrix}
  \ \ \ 0\ &1\ &2\ &3\ &4\ &5
  \end{matrix}\\
  \begin{matrix}
  \small\color{blue}0 \\ 
  \small\color{blue}1 \\
  \small\color{blue}2 \\
  \end{matrix}
  \begin{bmatrix}
  [0&0&0&0&4&4] \\
  [0&0&2&2&4&4] \\
  [0&0&2&2&4&\color{red}\bold5\color{black}] \\
  \end{bmatrix}
  \end{gathered}
  $$

  + *Time Complexity:* $\mathcal{O}(N*C)$​​

  + *Space Complexity:* $\mathcal{O}(N*C)$​

+ 「滚动数组」：$dp[2][C+1]$​​

  根据「转移方程」，计算第 $i$ 行格子只需要第 $i-1$ 行中的某些值。也就是计算「某一行」的时候只需要依赖「前一行」。因此可以用一个只有两行的数组来存储中间结果，根据当前计算的行号是偶数还是奇数来交替使用第 0 行和第 1 行。

  ```java
  public int maxValue(int N, int C, int[] v, int[] w) {
  	int[][] dp = new int[2][C+1];
    // 初始化：先处理「考虑第一件物品」的情况（即dp数组的第一横行的初始值）
    for (int c = 0; c <= C; i++) dp[0][c] = c >= v[0] ? w[0] : 0;
    // 状态转移：再处理「考虑其余物品」的情况
    for (int i = 1; i < N; i++) {
      for (int c = 0; c <= C; c++) {
        // 不选第i件物品
        int ns = dp[(i-1)&1][c];
        // 满足前提：选第i件物品
        int s = c >= v[i] ? dp[(i-1)&1][c-v[i]] + w[i] : 0;
        dp[i&1][c] = Math.max(ns, s);
      }
    }
    return dp[(N-1)&1][C];
  }
  ```

  + *Time Complexity:* $\mathcal{O}(N*C)$​

  + *Space Complexity:* $\mathcal{O}(C)$​

+ 「一维空间优化」：$dp[C+1]$​​     **==重点==**

  不难发现当求解第 $i$ 行格子的值时，不仅是只依赖第 $i-1$ 行，还明确只依赖第 $i-1$ 行的第 $c$ 个格子和第 $c-v[i]$ 个格子（也就是对应着第 $i$ 个物品「不选」和「选」的两种情况）。换句话说，只依赖于「上一个格子的位置」以及「上一个格子的左边位置」。

  <img src="/Users/shanyonghao/IdeaProjects/LeetCodeProblems/Notes_img/背包问题_1.jpg" style="zoom:50%;" />

  因此，只要将求解第 $i$​ 行格子的顺序「从 $0$​ 到 $c$​」改为「从 $c$​ 到 $0$​」，就可以将原本 2 行的二维数组压缩到一行（转换为一维数组）。

  故，「01背包」在采用「一维空间优化」解法时，「状态转移方程」修改为：
  $$
  dp[c]=max(dp[c], dp[c-v[i]]+w[i])
  $$
  同时，容量维度 $c$ 的遍历顺序为**==从大到小==**。

  **这样做的空间复杂度和「滚动数组」优化的空间复杂度是一样的。但仍然具有意义，而且这样的「一维空间」优化，是求解其他背包问题的基础，需要重点掌握。**

  ```java
  public int maxValue(int N, int C, int[] v, int[] w) {
  	int[] dp = new int[C+1];
    
    for (int i = 0; i < N; i++) {
      for (int c = C; c >= v[i]; c--) {
        // 不选第i件物品
        int ns = dp[c];
        // 选第i件物品
        int s = dp[c-v[i]] + w[i];
        dp[c] = Math.max(ns, s);
      }
    }
    return dp[C];
  }
  ```
  
  + *Time Complexity:* $\mathcal{O}(N*C)$​

  + *Space Complexity:* $\mathcal{O}(C)$​​

**总结：** ==在众多背包问题中，「01背包」是最为核心的，且「01背包」的「一维空间优化」解法是几乎所有背包问题的基础，重点掌握。==

************

###### 完全背包

在 0-1 背包问题的基础上，增加了每件物品可以选择多次的特点（在容量允许的情况下）。

> 有 $N$ 种物品和一个容量为 $C$ 的背包，每种物品都有无限件。第 $i$ 件物品的体积是 $v[i]$，价值是 $w[i]$。
>
> 求解将哪些物品装入背包可使这些物品的费用总和不超过背包容量，且价值总和最大。

*e.g.*

```java
Input: N = 2, C = 5, v = [1, 2], w = [1, 2]
Output: 5
Explanation: 选第一件物品 1， 再选两件物品 2，可使价值最大。
```

直接将 01 背包的「状态定义」拿过来用：**$dp[i][j]$​ 代表考虑前 $i$​ 件物品，放入一个容量为 $c$​ 的背包可以获得的最大价值。**

由于每件物品可以被选择多次，因此对于某个 $dp[i][c]$​ 而言，其值应该为以下所有可能方案中的最大值：

- 选择 0 件物品 $i$​​ 的最大价值，即 $dp[i-1][c]$​

- 选择 1 件物品 $i$​ 的最大价值，即 $dp[i-1][c-v[i]]+w[i]$​

- 选择 2 件物品 $i$​ 的最大价值，即 $dp[i-1][c-2*v[i]]+2*w[i]$​

  $\dots$

- 选择 k 件物品 $i$​ 的最大价值，即 $dp[i-1][c-k*v[i]]+k*w[i]$​

由此，可以得出「状态转移方程」为：
$$
dp[i][c] = max(dp[i-1][c], dp[i-1][c-k*v[i]]+k*w[i]), 0 < k*v[i]\le c
$$
**从「数学」的角度进一步简化上面的「状态转移方程」：**<a name="「完全背包」一维方程推导"></a>

1. 展开「完全背包」原本的「状态转移方程」：
   $$
   \begin{aligned}
   dp[i][c] &= max(dp[i-1][c], \underline{dp[i-1][c-k*v[i]]+k*w[i]}),0 <k*v[i]\le c\\
   &= max(dp[i-1][c], \underline{\color{blue}{dp[i-1][c-v[i]]+\bold{w[i]}},\color{green}{dp[i-1][c-2*v[i]]+\bold{2*w[i]}}\color{black}{+\dots} + \color{orange}{dp[i-1][c-k*v[i]]+\bold{k*w[i]}}}),0<k*v[i]\le c
   \end{aligned}
   $$

2. 同理，$dp[i][c-v[i]]$​​ 的展开式为：
   $$
   dp[i][c-v[i]] = max(\underline{\color{blue}{dp[i-1][c-v[i]]},\color{green}{dp[i-1][c-2*v[i]]+\bold{w[i]}}\color{black}{+\dots} + \color{orange}{dp[i-1][c-k*v[i]]+\bold{(k-1)*w[i]}}}),0<k*v[i]\le c
   $$

3. 比较发现，上述两式的划线部分具有“等差”特性，总是相差 $w[i]$，因此
   $$
   dp[i][c] = max(dp[i-1][c],dp[i][c-v[i]]+w[i])
   $$

代码部分，分别提供对应两版「状态转移方程」的不同解法：

==**只是已经推导到第二版「状态转移方程」后，完全可以直接写「一维空间优化」解法**==

+ $dp[N][C+1]$​ 解法：

  ```java
  public int maxValue(int N, int C, int[] v, int[] w) {
    int[][] dp = new int[N][C+1];
    
    // 先预处理「第一件物品」
    for (int c = 0; c <= C; c++) {
      // 显然当只有一件物品，在容量允许的情况下，能选多少件就选多少件
      int maxK = j / v[0];
      dp[0][c] = maxK * w[0];
    }
    
    // 再处理「剩余物品」
    for (int i = 1; i < N; i++) {
      for (int c = 0; c <= C; c++) {
        // 「不选」：选择 0 件物品 i
        int ns = dp[i-1][c];
        // 「选」：
        int s = 0;
        for (int k = 0; k * v[i] <= c; k++) { // 当v[i] = 1时，k可取到c
          s = Math.max(s, dp[i-1][c-k*v[i]] + k*w[i]); 
        }
        dp[i][c] = Math.max(ns, s);
      }
    }
    
    return dp[N-1][C];
  }
  ```
  
  + *Time Complexity:* $\mathcal{}(N*C*C)$​​​
  
  + *Space Complexity:* $\mathcal{O}(N*C)$​​
  
  ```java
  public int maxValue(int N, int C, int[] v, int[] w) {
    int[][] dp = new int[N][C+1];
    
    // 先预处理「第一件物品」
    for (int c = 0; c <= C; c++) {
      // 显然当只有一件物品，在容量允许的情况下，能选多少件就选多少件
      int maxK = j / v[0];
      dp[0][c] = maxK * w[0];
    }
    
    // 再处理「剩余物品」
    for (int i = 1; i < N; i++) {
      for (int c = 0; c <= C; c++) {
        // 「不选」：选择 0 件物品 i
        int ns = dp[i-1][c];
        // 「选」：
        int s = c >= v[i] ? dp[i][c-v[i]] + w[i] : 0;
        dp[i][c] = Math.max(ns, s);
      }
    }
    
    return dp[N-1][C];
  }
  ```
  
  + *Time Complexity:* $\mathcal{O}(N*C)$​​​​
  
  + *Space Complexity:* $\mathcal{O}(N*C)$​
  
+ 「滚动数组」：

  ```java
  public int maxValue(int N, int C, int[] v, int[] w) {
    int[][] dp = new int[2][C+1];
    
    // 先预处理「第一件物品」
    for (int c = 0; c <= C; c++) {
      // 显然当只有一件物品，在容量允许的情况下，能选多少件就选多少件
      int maxK = j / v[0];
      dp[0][c] = maxK * w[0];
    }
    
    // 再处理「剩余物品」
    for (int i = 1; i < N; i++) {
      for (int c = 0; c <= C; c++) {
        // 「不选」：选择 0 件物品 i
        int ns = dp[(i-1)&1][c];
        // 「选」：
        int s = 0;
        for (int k = 0; k * v[i] <= c; k++) { // 当v[i] = 1时，k可取到c
          s = Math.max(s, dp[(i-1)&1][c-k*v[i]] + k*w[i]); 
        }
        dp[i&1][c] = Math.max(ns, s);
      }
    }
    
    return dp[(N-1)&1][C];
  }
  ```
  
  + *Time Complexity:* $\mathcal{O}(N*C*C)$​​​
  
  + *Space Complexity:* $\mathcal{O}(C)$​​​
  
  ```java
  public int maxValue(int N, int C, int[] v, int[] w) {
    int[][] dp = new int[2][C+1];
    
    // 先预处理「第一件物品」
    for (int c = 0; c <= C; c++) {
      // 显然当只有一件物品，在容量允许的情况下，能选多少件就选多少件
      int maxK = j / v[0];
      dp[0][c] = maxK * w[0];
    }
    
    // 再处理「剩余物品」
    for (int i = 1; i < N; i++) {
      for (int c = 0; c <= C; c++) {
        // 「不选」：选择 0 件物品 i
        int ns = dp[(i-1)&1][c];
        // 「选」：
        int s = c >= v[i] ? dp[i&1][c-v[i]] + w[i] : 0;
        dp[i&1][c] = Math.max(ns, s);
      }
    }
    
    return dp[(N-1)&1][C];
  }
  ```
  
  + *Time Complexity:* $\mathcal{O}(N*C)$​​​
  
  + *Space Complexity:* $\mathcal{O}(C)$​​
  
+ 「一维空间优化」：**==重点==**

  虽然「完全背包」问题有两个版本的「状态转移方程」，但是对于「一维空间优化」解法，选择第二个化简后的「状态转移方程」进行 $i$ 维度的消除：
  $$
  dp[c] = max(dp[c], dp[c-v[i]]+w[i])
  $$

  ```java
  public int maxValue(int N, int C, int[] v, int[] w) {
    int[] dp = new int[C+1];
    
    for (int i = 0; i < N; i++) {
      for (int c = v[i]; c <= C; c++) {  // 与「01背包」不同，依旧是从小到大遍历
        // 「不选」：选择 0 件物品 i
        int ns = dp[c];
        // 「选」：
        int s = dp[c-v[i]] + w[i];
        dp[c] = Math.max(ns, s);
      }
    }
   
    return dp[C];
  }
  ```


  + *Time Complexity:* $\mathcal{O}(N*C)$​​
  + *Space Complexity:* $\mathcal{O}(C)$​​​

==**总结：**==

- 0-1 背包问题的状态转换方程是：

$$
dp[i][c] = max(dp[i-1][c],dp[i-1][c-v[i]]+w[i])
$$

**由于计算 $dp[i][c]$ 的时候，依赖于 $dp[i-1][c-v[i]]$。**

**因此，在改为「一维空间优化」时，需要确保 $dp[c-v[i]]$ 存储的是上一行的值，即确保 $dp[c-v[i]]$ 还没有被更新，所以遍历方向是从大到小。**

- 完全背包问题的状态转移方程是：

$$
dp[i][c]=max(dp[i-1][c],dp[i][c-v[i]]+w[i])
$$

**由于计算 $dp[i][c]$ 的时候，依赖于 $dp[i][c-v[i]]$。**

**因此，在改为「一维空间优化」时，需要确保 $dp[i][c-v[i]]$ 存储的是当前行的值，即确保 $dp[c-v[i]]$​ 已经被更新，所以遍历方向是从小到大。**

**形式上，只需要将「 01 背包」问题的「一维空间优化」解法中的「容量维度」遍历方向从「从大到小」改为「从小到大」就可以解决完全背包问题。**

**但本质是因为两者进行状态转移时依赖了不同的格子：**

- **「01 背包」依赖的是「上一行正上方的格子」和「上一行左边的格子」。**
- **「完全背包」依赖的是「上一行正上方的格子」和「本行左边的格子」。**

********

###### 多重背包

在 0-1 背包问题的基础上，增加了每件物品可以选择「有限次数」的特点（在容量允许的情况下）。

> 有 $N$ 种物品和一个容量为 $C$ 的背包，每种物品**「数量有限」**。第 $i$ 件物品的体积是 $v[i]$，价值是 $w[i]$，数量为 $s[i]$。
>
> 问选择哪些物品，每件物品选择多少件，可使得总价值最大。

*e.g.*

```java
Input: N = 2, C = 5, v = [1, 2], w = [1, 2], s = [2, 1]
Output: 4
Explanation: 选两件物品 1，再选一件物品 2，可使价值最大
```

**几乎所有的「背包问题」都是基于「01 背包」演变而来。**套用「01 背包」的「状态定义」来进行分析：**$dp[i][c]$ 代表考虑前 $i$ 件物品，且所选物品总体积不超过 $c$ 时获得的最大价值。**

由于每件物品可以被选择「有限次」，因此对于某个 $i$ 而言，其值应该为以下所有可能方案中的最大值：

- 选择 $0$ 件物品 $i$ 的最大价值，即 $dp[i][c]=dp[i-1][c]$

- 选择 $1$ 件物品 $i$ 的最大价值，即 $dp[i][c]=dp[i-1][c-v[i]]+w[i]$

- 选择 $2$ 件物品 $i$ 的最大价值，即 $dp[i][c]=dp[i-1][c-2*v[i]]+2*w[i]$

  $\dots$

- 选择 $s[i]$ 件物品 $i$ 的最大价值，即 $dp[i][c]=dp[i-1][c-s[i]*v[i]]+s[i]*w[i]$

由此可以得出「状态转移方程」为：
$$
dp[i][c]=max(dp[i-1][c], dp[i-1][c-k*v[i]]+k*w[i]),\ 0< k\le s[i],\ 0< k*v[i]\le c
$$
可以发现其状态转移方程与「完全背包」完全一致，只是多了 $0<k\le s[i]$ 的条件。毕竟「完全背包」不限制物品数量，「多重背包」限制物品数量。

+ $dp[N][C+1]$ 解法：==和「完全背包」一样，除了最内层循环 $k$ 多了个判断条件：$k\le s[i]$==

  ```java
  public int maxValue(int N, int C, int[] s, int[] v, int[] w) {
    int[][] dp = new int[N][C+1];
    
    // 先处理第一件物品：
    for (int c = 0; c <= C; c++) {
      int maxK = Math.min(s[0], c / v[0]);
      dp[0][c] = maxK * w[0];
    }
    
    // 处理剩余物品
    for (int i = 1; i < N; i++) {
      for (int c = 0; c <= C; c++) {
        // 「不选」：
        int ns = dp[i-1][c];
        // 「选」：
        int s = 0;
        for (int k = 1; k <= s[i] && k * v[i] <= c; k++) 
          s = Math.max(s, dp[i-1][c-k*v[i]]+k*w[i]);
        dp[i][c] = Math.max(ns, s);
      }
    }
    
    return dp[N-1][C];
  }
  ```

  + *Time Complexity:* 共有三层循环的运算量，即 $N*C*S$，其中 $N*S=\sum_{i=0}^{N-1}s[i]$。故整体复杂度为 $\mathcal{O}(\sum_{i=0}^{N-1}s[i]*C)$
  + *Space Complexity:* $\mathcal{O}(N*C)$

+ 「滚动数组」解法：

  通过观察「状态转移方程」可以发现，在更新某个 $dp[i][c]$ 时，只依赖于 $dp[i-1][]$。因此可以像「01背包」那样使用「滚动数组」的方式将空间优化到 $\mathcal{O}(C)$。

  ```java
  public int maxValue(int N, int C, int[] s, int[] v, int[] w) {
    int[][] dp = new int[2][C+1];
    
    // 先处理第一件物品：
    for (int c = 0; c <= C; c++) {
      int maxK = Math.min(s[0], c / v[0]);
      dp[0][c] = maxK * w[0];
    }
    
    // 处理剩余物品
    for (int i = 1; i < N; i++) {
      for (int c = 0; c <= C; c++) {
        // 「不选」：
        int ns = dp[(i-1)&1][c];
        // 「选」：
        int s = 0;
        for (int k = 1; k <= s[i] && k * v[i] <= c; k++) 
          s = Math.max(s, dp[(i-1)&1][c-k*v[i]]+k*w[i]);
        dp[i&1][c] = Math.max(ns, s);
      }
    }
    
    return dp[(N-1)&1][C];
  }
  ```

  + *Time Complexity:* $\mathcal{O}(\sum_{i=0}^{N-1}s[i]*C)$
  + *Space Complexity:* $\mathcal{O}(N*C)$

+ 「一维空间优化」解法：

  **「多重背包」可以通过「一维空间优化」优化空间，但是不能降低时间复杂度。**因为当像「完全背包」那样只保留「容量维度」，并且「从小到大」遍历容量的话，在转移 $f[c]$ 时是无法直接知道所依赖的 $f[c-v[i]]$ 到底使用了多少件物品 $i$ 的。

  这个问题在「完全背包」里面无须关心，因为每件物品可以被选择无限次，而在「多重背包」则是不能忽略，否则可能会违背物品件数有限的条件。

  因此，「多重背包」问题的「一维空间优化」并不能像「完全背包」那样使复杂度降低。

  ```java
  public int maxValue(int N, int C, int[] s, int[] v, int[] w) {
    int[] dp = new int[C+1];
    
    for (int i = 0; i < N; i++) {
      for (int c = C; c >= v[i]; c--) {  // c「从大到小」
        for (int k = 1; k <= s[i] && k * v[i] <= c; k++) 
          dp[c] = Math.max(dp[c, dp[c-k*v[i]]+k*w[i]);
      }
    }
    
    return dp[(N-1)&1][C];
  }
  ```

  + *Time Complexity:* $\mathcal{O}(\sum_{i=0}^{N-1}s[i]*C)$
  + *Space Complexity:* $\mathcal{O}(C)$

==**与其他背包的内在关系：**==

+ **只有「完全背包」的「一维空间优化」是存在数学意义上的优化（能够有效降低算法时间复杂度）。**

  **「01 背包」和「多重背包」的「一维空间优化」其实只是基于「朴素二维」解法做单纯的「滚动」操作而已（利用状态之间的依赖关系，配合遍历顺序，使得不再需要参与转移的空间能够被重新利用）。**

  因此，一定程度上，可以将「多重背包」看做是一种特殊的「01 背包」。

+ 转换为「01背包」：

  对「01 背包」中具有相同的价值 & 成本的物品进行计数，就成了对应物品的「限制件数」，「01 背包」也就转换成了「多重背包」。

  同理，将「多重背包」的多件物品进行「扁平化展开」，就转换成了「01 背包」。

  扁平化需要遍历所有物品，枚举每件物品的数量，将其添加到一个新的物品列表里。再套用「01 背包」的解决方案。

  ```java
  public int maxValue(int N, int C, int[] s, int[] v, int[] w) {
    // 将多件数量的同一物品进行「扁平化」展开，以 [v, w] 形式存储
    List<int[]> arr = new ArrayList<>(); 
    for (int i = 0; i < N; i++) {
      int cnt = s[i];
      while (cnt-- > 0) 
        arr.add(new int[]{v[i], w[i]});
    }
  
    // 使用「01背包」进行求解
    int[] dp = new int[C + 1];
    for (int i = 0; i < arr.size(); i++) {
      int vi = arr.get(i)[0], wi = arr.get(i)[1];
      for (int j = C; j >= vi; j--) {
        dp[j] = Math.max(dp[j], dp[j - vi] + wi);   
      }
    }
    return dp[C];
  }
  ```

  - *Time Complexity:* 扁平化的计算量取决于最终能展开成多少个物品（$\sum_{i=0}^{N-1}s[i]$）。共有 $\mathcal{O}(\sum_{i=0}^{N-1}s[i]*C)$ 个状态需要被转移，因此整体复杂度为 $\mathcal{O}(\sum_{i=0}^{N-1}s[i]*C)$
  - *Space Complexity:* $\mathcal{O}(\sum_{i=0}^{N-1}s[i]+C)$

  **可以发现，转换成「01 背包」之后，时间复杂度并没有发生变化。因此将「多重背包」简单的转换成「01 背包」并不能带来效率的提升。甚至说转换成「01 背包」之后效率上还要稍微差一点，因为额外增大了“常数”。**

  扁平化操作并没有使得物品“变少”，仍然需要枚举所有的“组合”，从中选择最优，组合的数量没有发生变化，还额外增加了扁平化的操作。—— 这是将「多重背包」转换成「01 背包」进行求解没有“实际意义”（但可以帮助理解两者之间的联系）的原因。

==**总结：**== 无论是「朴素二维」、「滚动数组」、「一维优化」还是「扁平化」都不能优化「多重背包」问题的时间复杂度。在各维度数量级同阶的情况下，时间复杂度是 $\mathcal{O}(n^3)$ 的。这意味着只能解决 $10^2$ 数量级的「多重背包」问题。

==**「多重背包」的优化方法：**==

+ 「二进制优化」：使得能解决的多重背包问题数量级从 $n^2$ 上升为 $n^3$。$\color{red}\star\star\star$

  **所谓的「二进制优化」其实是指，如何将一个「多重背包」问题彻底转化为「01背包」问题，同时降低其复杂度。**

  在「扁平化展开」的基础上，如果能将重复物品的数量变小，那么这样的「扁平化」就是有意义的。

  Linux 的文件权限最高是 7，代表拥有读、写、执行的权限，但其实是采用了 3 个数来对应 8 种情况的“压缩”方法 —— 7 是对应了 1、2、4 三个数字的，也就是 `r:1`、`w:2`、`x:4` ，三种权限的组合共有 8 种可能性。

  「二进制优化」就是借鉴这样的思路：**将原本数量为 $s[i]$ 的物品拆分成 $\lceil{log(s[i])}\rceil$ 件，从而降低算法复杂度。**

  7 可以用 `1、2、4` 来代替，10 可以使用 `1、2、4、3` 来代替。不用 `1、2、4、6` 或者 `1、2、4、8`，是因为 `1、2、4、6` 可以表达的范围是 0~13，而 `1、2、4、8` 可以表达的范围是 0~15，而要求的是表达 10，大于 10 的范围是不能被选择的。所以可以在 `1、2、4` （表达的范围是 0~7）的基础上，增加一个数 3（由 10 - 7 而来），这样就能满足需要表达的范围 0~10。

  *e.g.* 

  + 原输入：
    $$
    \begin{aligned}
    v&=[1,2,\color{red}{3}\color{black},4,5] \\
    w&=[1,3,\color{red}2\color{black},5,4] \\
    s&=[1,1,\color{red}10\color{black},1,1]
    \end{aligned}
    $$

  + 「扁平化展开」$\rightarrow$ 「二进制优化」：
    $$
    \begin{aligned}
    v_{扁平化}&=[1,2,\color{orange}\underline{3},\color{blue}\underline{3,3},\color{green}\underline{3,3,3,3},\color{pink}\underline{3,3,3}\color{black},4,5] \\
    w_{扁平化}&=[1,3,\color{orange}\underline2,\color{blue}\underline{2,2},\color{green}\underline{2,2,2,2},\color{pink}\underline{2,2,2}\color{black},5,4] \\
    s_{扁平化}&=[1,1,\color{orange}\underline{1},\color{blue}\underline{1,1},\color{green}\underline{1,1,1,1},\color{pink}\underline{1,1,1}\color{black},1,1] \\
    \implies v_{二进制}&=[1,2,\color{orange}3,\ \ \color{blue}6,\quad\ \ \color{green}12,\quad\quad\ \color{pink}9\quad\color{black},4,5] \\
    w_{二进制}&=[1,3,\color{orange}2,\ \ \color{blue}4,\quad\quad\color{green}8,\quad\quad\ \color{pink}6\quad\color{black},5,4] \\
    s_{二进制}&=[1,1,\color{orange}1,\ \ \color{blue}1,\quad\quad\color{green}1,\quad\quad\ \color{pink}1\quad\color{black},1,1] 
    \end{aligned}
    $$

  *解释：* 

  |        $s$        |                         $s_{二进制}$                         |
  | :---------------: | :----------------------------------------------------------: |
  | 选 $0$ 件 $s[2]$  |                             不选                             |
  | 选 $1$ 件 $s[2]$  |                      选 $s_{二进制}[2]$                      |
  | 选 $2$ 件 $s[2]$  |                      选 $s_{二进制}[3]$                      |
  | 选 $3$ 件 $s[2]$  |                      选 $s_{二进制}[5]$                      |
  | 选 $4$ 件 $s[2]$  |                      选 $s_{二进制}[4]$                      |
  | 选 $5$ 件 $s[2]$  |               选 $s_{二进制}[2],s_{二进制}[4]$               |
  | 选 $6$ 件 $s[2]$  |               选 $s_{二进制}[3],s_{二进制}[4]$               |
  | 选 $7$ 件 $s[2]$  |        选 $s_{二进制}[2],s_{二进制}[3],s_{二进制}[4]$        |
  | 选 $8$ 件 $s[2]$  |        选 $s_{二进制}[2],s_{二进制}[4],s_{二进制}[5]$        |
  | 选 $9$ 件 $s[2]$  |        选 $s_{二进制}[3],s_{二进制}[4],s_{二进制}[5]$        |
  | 选 $10$ 件 $s[2]$ | 选 $s_{二进制}[2],s_{二进制}[3],s_{二进制}[4],s_{二进制}[5]$ |

  ```java
  public int maxValue(int N, int C, int[] s, int[] v, int[] w) {
    List<Integer> worth = new ArrayList<>();
    List<Integer> volume = new ArrayList<>();
  
    // 希望每件(重复的)物品都进行扁平化，所以遍历所有的物品
    for (int i = 0; i < N; i++) {
      // 获取每件物品的出现次数
      int val = s[i];
      // 进行扁平化：如果一件物品规定的使用次数为 7 次，我们将其扁平化为三件物品：1*重量&1*价值、2*重量&2*价值、4*重量&4*价值
      for (int k = 1; k <= val; k *= 2) { 
        val -= k;
        worth.add(w[i] * k);
        volume.add(v[i] * k);
      }
      if (val > 0) {
        worth.add(w[i] * val);
        volume.add(v[i] * val);
      }
    }
  
    // 使用「01背包」解决：
    int[] dp = new int[C + 1];
    for (int i = 0; i < worth.size(); i++) {
      for (int j = C; j >= volume.get(i); j--) {
        dp[j] = Math.max(dp[j], dp[j - volume.get(i)] + worth.get(i));
      }
    }
    return dp[C];
  }
  ```

  + *Time Complexity:* $\mathcal{O}(\sum_{i=0}^{n-1}\lceil{logs}[i]\rceil*C)$
  + *Space Complexity:* $\mathcal{O}(\sum_{i=0}^{n-1}\lceil{logs}[i]\rceil+C)$

+ 「单调队列优化」$\color{red}\star\star\star\star\star$

  **与对「物品」做拆分的「二进制优化」不同，「单调队列优化」是对「状态」做拆分操作。**

  首先，还是使用一维空间优化的定义：**$f[c]$ 代表容量不超过 $c$ 时的最大价值。**当遍历完所有的物品后，$f[C]$ 就是答案。

  在朴素的「多重背包」解决方案中，当在处理某一个物品从 $f[0]$ 到 $f[C]$ 的状态时，每次都是通过遍历当前容量 $x$ 能够装多少件该物品，然后从所有遍历结果中取最优（根据题目，取最大或最小）。==**但事实上，转移只会发生在「对当前物品体积取余相同」的状态之间。**==

  *e.g.* 假设当前处理到的物品**体积为 $2$** ，**数量为 $3$** ，而**背包容量为 $10$**，那么从 $f[0]$ 转移到 $f[10]$ 时，存在如下规律：

  - $f[10]$ 只能由 $f[8],f[6],f[4]$ 转移而来

  - $f[9]$ 只能由 $f[7],f[5],f[3]$ 转移而来

    $\dots$

  - $f[5]$ 只能由 $f[3],f[1]$ 转移而来

  - $f[4]$ 只能由 $f[2],f[0]$ 转移而来

  - $f[3]$ 只能由 $f[1]$ 转移而来

  - $f[2]$ 只能由 $f[0]$ 转移而来

  ==**即某个状态 $f[c]$ 只能由对 $v$ 取余和 $c\bmod v$（$v$ 为当前物品体积，$c$ 为当前背包容量）相同，比 $c$ 小，数量不超过「物品个数」的状态值所更新。**== *（10/8/6/4 对 2 取余结果相同，都为0，8/6/4 均比 10 小，8/6/4 共三个，不超过物品个数；5/3 对 2 取余结果相同，都为1，3 比 5 小，3 共一个，不超过物品个数...）*

  因此这其实是一个「滑动窗口求最值」问题（窗口的大小最多不超过「物品个数」；最值指根据题意取窗口内元素的最大/小值）。

  **如果能够在转移 $f[c]$ 时，以 $\mathcal{O}(1)$ 或者均摊 $\mathcal{O}(1)$ 的复杂度从「能够参与转移的状态」（即窗口）中找到最大值，就能省掉朴素「多重背包」解决方案中最内层的“决策”循环，从而将整体复杂度降低到 $\mathcal{O}(N*C)$。**

  具体地，定义一个数组 $g$ ，用来记录上一个物品转移后的结果（即 $f[]$）；定义一个数组 $q$ 来作为滑动窗口，用来存放本次物品转移时所用到的所有 $c$ 。

  由于希望在 $\mathcal{O}(1)$ 复杂度内取得「能够参与转移的状态」中的最大值，自然期望能够在滑动窗口的头部或者尾部直接取得目标值来更新 。为此，需要维持「窗口内元素单调」和「特定的窗口大小」。==这里滑动窗口内元素单调并不是指 $c$ 按照大小排序，而是按照每个 $c$ 所对应的 $f[c]$ 的大小来排序 $c$ 。==例如：$f[1]=4,f[2]=1,f[3]=5$，则窗口的内容为 $[3,1,2]$。

  ```java
  public int maxValue(int N, int C, int[] s, int[] v, int[] w) {
    int[] f = new int[C + 1];
    int[] g = new int[C + 1]; // 辅助数组，记录上一个物品转移完dp数组f的结果
    int[] q = new int[C + 1]; // 滑动窗口，记录c，c的排序由其对应的f[c]的值决定，单调（递减）
  
    // 枚举物品
    for (int i = 0; i < N; i++) {
      int vi = v[i];
      int wi = w[i];
      int si = s[i];
  
      // 将上次算的结果存入辅助数组中
      g = f.clone();
  
      // 枚举余数rem：c mod vi < vi ==>> rem的取值范围为[0, vi)
      for (int rem = 0; rem < vi; rem++) {
        // 初始化窗口，head 和 tail 分别指向窗口队列的头部和尾部
        int head = 0, tail = -1;
        // 枚举同一余数情况下，c有多少种方案。
        // 例如余数为 1 的情况下，c可以为1、vi + 1、2 * vi + 1、3 * vi + 1 ...
        // 结合上述例子（vi=2, si=3, wi=3, C=10），rem为1时，c可为1，3，5，7，9
        for (int c = rem; c <= C; c += vi) {
          // 如果不选当前物品的话，则对于此时的容量c，其最优值为上一个物品该容量时的转移结果，即g[c]
          f[c] = g[c];
          // 如果选择当前物品，则对于此时的容量c，其更新的依据为滑动窗口内的元素
          // 首先，需要将不在窗口范围内的c的取值弹出
          if (head <= tail && q[head] < c - si * vi) head++;
          // 接着，如果队列中仍存在元素，直接使用队首元素来更新
          if (head <= tail) f[c] = Math.max(f[c], g[q[head]] + (c - q[head]) / vi * wi);
          // 增加判断：如果当前值比队尾值更优时，队尾元素没有存在必要，队尾出队
          while (head <= tail && g[q[tail]] - (q[tail] - rem) / vi * wi <= g[c] - (c - rem) / vi * wi) tail--;
          // 最后，将新下标c入队尾，从而保证窗口内的元素一直被更新且保持单减（队首元素最优） 
          q[++tail] = c;
        }
      }
    }
    return f[C];
  }
  ```
  
  + *Time Complexity:* $\mathcal{O}(N*C)$
  + *Space Complexity:* $\mathcal{O}(C)$
  
  *解释：* 
  
  首先，对于任一 $rem$ 都会有一个滑动窗口；接着，同一余数情况下，具体到某一个 $c$ 时，窗口的内容都需要更新。
  结合上述例子（$vi=2,si=3,wi=3,C=10$），$rem$ 为 $1$ 时：
  
  + $c = 1$：此时窗口内无值，即 $head=0,tail=-1$
  + $c = 3$：此时 $head=tail=0$，窗口 $q$ 内 $q[head]=q[0]=1 > c-si*vi=3-2*3=-3$，故不需要更新 $head$，$f[3]$ 的转移会用到 $f[1]$：$f[3]=max(f[3], g[1]+(3-1)/2*3)$；假设 $g[1]-(1-1)/2*3<g[3]-(3-1)/2*3$，故窗口内 $f[1]$ 没必要继续保留，$tail$ 更新为 $-1$；最终，将 $c=3$ 入窗口 $q$，$tail$ 更新为 $0$
  + $c = 5$：此时$head=tail=0$，窗口 $q$ 内 $q[head] = q[0] = 3$，故 $f[5]$ 的转移会用到 $f[3]$：$f[5]=max(f[5], g[3]+(5-3)/2*3)$；假设 $g[5]-(5-1)/2*3<g[3]-(3-1)/2*3$，故窗口内 $f[3]$ 要继续保留，将 $c=5$ 入窗口 $q$ 队尾，$tail$ 更新为 $1$
  + $c = 7$：此时$head=0,tail=1$，窗口 $q=[3,5]$，即 $q[head] = q[0] = 3$，故 $f[7]$ 的转移会用到 $f[3]$：$f[7]=max(f[7], g[3]+(7-3)/2*3)$；假设 $g[5]-(5-1)/2*3<g[3]-(3-1)/2*3<g[7]-(7-1)/2*3$，故窗口内 $f[3],f[5]$ 都不需要继续保留，$tail$ 更新为 $-1$；最终，将 $c=7$ 入窗口 $q$ 队尾，$tail$ 更新为 $0$
  + $c = 9$：此时$head=tail=0$，窗口 $q$ 内 $q[head] = q[0] = 7$，故 $f[9]$ 的转移会用到 $f[7]$：$f[9]=max(f[9], g[7]+(9-7)/2*3)$；假设 $g[9]-(9-1)/2*3<g[7]-(7-1)/2*3$，故窗口内 $f[7]$ 要继续保留，将 $c=9$ 入窗口 $q$ 队尾，$tail$ 更新为 $1$
  
  ==疑惑：== $c=5$ 时，$f[3]$ 继续保留，就没必要将 $c=5$ 入窗口 $q$ 队尾，根本不会用到。
  
  ==**补充：**== 这些年，这种根据“取余”对状态做划分，然后转换为「滑动窗口」问题，配合某种数据结构（单调队列/哈希表）来实现优化的方式，早就出现在各种题目中了。
  
  例如 30. 串联所有单词的子串、1787. 使所有区间的异或结果为零 ... 

*****

###### 混合背包

「混合背包」是前三种传统「背包问题」的结合。先回顾一下三种传统「背包问题」：

- 「01 背包」：强调每件物品**「只能选择一次」**。对其进行「一维空间优化」并不能降低时间复杂度，进行「一维空间优化」时要求**「容量维度“从大到小”进行遍历」**。
- 「完全背包」：强调每件物品**「可以选择无限次」**。对其进行「一维空间优化」具有数学意义，可以将时间复杂度从 $\mathcal{O}(N*C*C)$ 降低到 $\mathcal{O}(N*C)$，进行「一维空间优化」时要求**「容量维度“从小到大”进行遍历」**。
- 「多重背包」：强调每件物品**「只能选择有限次」**。对其无论是进行「一维空间优化」还是「普通扁平化」都不能降低时间复杂度，要应用额外的优化手段：「二进制优化」或「单调队列优化」。

三种背包问题的**「难易程度」**是**「递增」**，但**「重要程度」**则是**「递减」** 的。

虽然「多重背包」的 「二进制优化」和「单调队列优化」都比较 trick。但其实「多重背包」并没有这么常见，以至于在 LeetCode 上都没找到与「多重背包」相关的题目。

==同时三种背包问题都有**「不超过」**和**「恰好」**两种状态定义。**这两种状态定义只在「初始化」上有区别。**==

至于该如何初始化则要抓住 **什么样的状态是合法的** ：

- 对于「不超过」的状态定义：

  $f[0][x]$ 均为合法值 $0$：代表不考虑任何物品，背包容量「不超过 $c$」所取得的最大价值为 $0$。

- 对于「恰好」的状态定义：

  $f[0][x]$ 中只有 $f[0][0]$ 为合法值 ，其他值均为“无效值”：代表不考虑任何物品，只有背包容量「恰好为 $0$」时所取得的最大价值为 $0$；其他容量「恰好为非 $0$」是无法取得有效价值的（因为不考虑任何物品）。

总的来说，三种背包问题都很经典（本质上都是组合优化问题），以至于「背包问题」直接成为了一类的动态规划模型。

混合背包其实就是综合了「01 背包」、「完全背包」和「多重背包」三种传统背包问题。

> 给定物品数量 $N$ 和背包容量 $C$。第 $i$ 件物品的体积是 $v[i]$，价值是 $w[i]$，可用数量为 $s[i]$：
>
> - 当 $s[i]$ 为 $-1$ 代表是该物品只能用一次
> - 当 $s[i]$ 为 $0$ 代表该物品可以使用无限次
> - 当 $s[i]$ 为任意正整数则代表可用 $s[i]$ 次
>
> 求解将哪些物品装入背包可使这些物品的费用总和不超过背包容量，且价值总和最大。

在一维空间优化方式中「01 背包」将当前容量 $j$ 按照“从大到小”进行遍历，而「完全背包」则是将当前容量 $j$ 按照“从小到大”进行遍历。同时「多重背包」可以通过「二进制优化」彻底转移成「01 背包」。所以，只需要根据第 $i$ 个物品是「01 背包」物品还是「完全背包」物品，选择不同的遍历顺序即可。

```java
public int maxValue(int N, int C, int[] w, int[] v, int[] s) {
  // 构造出物品的「价值」和「体积」列表
  List<Integer> worth = new ArrayList<>();
  List<Integer> volume = new ArrayList<>();
  for (int i = 0; i < N; i++) {
    int type = s[i];

    // 多重背包：应用「二进制优化」转换为 0-1 背包问题
    if (type > 0) { 
      for (int k = 1; k <= type; k *= 2) {
        type -= k;
        worth.add(w[i] * k);
        volume.add(v[i] * k);
      }
      if (type > 0) {
        worth.add(w[i] * type);
        volume.add(v[i] * type);
      }

      // 01 背包：直接添加
    } else if (type == -1) {
      worth.add(w[i]);
      volume.add(v[i]);

      // 完全背包：对 worth 做翻转进行标记
    } else {
      worth.add(-w[i]);
      volume.add(v[i]);
    }
  }

  // 使用「一维空间优化」方式求解三种背包问题
  int[] dp = new int[C + 1];
  for (int i = 0; i < worth.size(); i++) {
    int wor = worth.get(i);
    int vol = volume.get(i);

    // 完全背包：容量「从小到大」进行遍历
    if (wor < 0) { 
      for (int j = vol; j <= C; j++) 
        // 同时记得将 worth 重新翻转为正整数
        dp[j] = Math.max(dp[j], dp[j - vol] - wor); 

      // 01 背包：包括「原本的 01 背包」和「经过二进制优化的完全背包」
      // 容量「从大到小」进行遍历
    } else { 
      for (int j = C; j >= vol; j--) 
        dp[j] = Math.max(dp[j], dp[j - vol] + wor);
    }
  }
  return dp[C];
}
```

********

###### 分组背包

> 给定 $N$ 个物品组，和容量为 $C$ 的背包。第 $i$ 个物品组共有 $S[i]$ 件物品，其中第 $i$ 组的第 $j$ 件物品的成本为 $v[i][j]$，价值为 $w[i][j]$。**每组有若干个物品，同一组内的物品最多只能选一个。**
>
> 求解将哪些物品装入背包可使这些物品的费用总和不超过背包容量，且价值总和最大。



###### 多维背包

###### 树形背包

###### 背包求方案数

###### 背包求具体方案

###### 泛化背包

******

##### 回文串

******

******

### Iteration & Recursion

#### ***关于Recursion的Top-down和Bottom-up***

+ If recursive calls before conditional check, then it's bottom up. 
+ If recursive calls after conditional check, then it's top down.

******

******

### String

#### Built-in Method

+ `trim()` : Returns a string whose value is this string, with any leading and trailing whitespace removed.
+ `split(String reg)` : Splits this string around matches of the given [regular expression](https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html#sum).
+ `String.join(CharSequence delimiter, CharSequence... elements)` : Returns a new String composed of copies of the `CharSequence elements` joined together with a copy of the specified `delimiter`.

*****

#### Regular Expression

+ 常用：

    元字符是一个预定义的字符。

    | 正则表达式 |                             描述                             |
    | :--------: | :----------------------------------------------------------: |
    |    `\d`    |               匹配一个数字，是 `[0-9]` 的简写                |
    |   `\d+`    |                         匹配多个数字                         |
    |    `\D`    |              匹配一个非数字，是 `[^0-9]` 的简写              |
    |    `\s`    |          匹配一个空格，是 `[ \t\n\x0b\r\f]` 的简写           |
    |   `\s+`    |                         匹配多个空格                         |
    |    `\S`    |                        匹配一个非空格                        |
    |    `\w`    | 匹配一个单词字符（大小写字母、数字、下划线），是 `[a-zA-Z_0-9]` 的简写 |
    |    `\W`    | 匹配一个非单词字符（除了大小写字母、数字、下划线之外的字符），等同于 `[^\w]` |

+ Java中正则表达式字符串：

    Java 中的正则表达式字符串有两层含义，首先 Java 字符串转义出符合正则表达式语法的字符串，然后再由转义后的正则表达式进行模式匹配。

    因为反斜杠本就在Java中表示转义字符，所以==上述过程中尤其需要注意反斜杠==：

    + 对于匹配 `.` / `{` / `[` / `(` / `?` / `$` / `^` / `*` 这些特殊字符时，正则表达式即为 `\.`。但这与Java中转义 `.` 的写法重合，因此在Java中匹配 `.` 的正则表达式字符串要写为 `\\.`，第一步转义为正则表达式 `\.`，第二步在匹配。
    + 在匹配 `\` 时，对于正则表达式即为 \\\，但Java中要写为 `\\\\`。
    + 匹配多个空格的正则表达式为 `\s+`，但在Java中写为字符串则需写为 `\\s+`。

+ Java中内置的字符串正则处理方法：

    在 Java 中有四个内置的运行正则表达式的方法，分别是 `matches()`、`split())`、`replaceFirst()`、`replaceAll()`。注意 `replace()` 方法不支持正则表达式。

    |                   方法                   |                  描述                   |
    | :--------------------------------------: | :-------------------------------------: |
    |           `s.matches("regex")`           | 当仅且当正则匹配整个字符串时返回 `true` |
    |            `s.split("regex")`            |      按匹配的正则表达式切片字符串       |
    | `s.replaceFirst("regex", "replacement")` |        替换首次匹配的字符串片段         |
    |  `s.replaceAll("regex", "replacement")`  |           替换所有匹配的字符            |

+ 参考：

    + [Java 正则表达式详解](https://segmentfault.com/a/1190000009162306)
    + [Java Doc](https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html#sum)

*****

#### StringBuilder

+ StringBuilder的三种清空方式：==方法一最慢，方法二和三差不多==
    + 重新 `new StringBuilder()`；
    + 使用 `stringBuilder.delete(0, stringBuilder.length())`；
    + 使用 `setLength(0)`
+ Built-in Method:
    + 不仅同样有 `charAt(int index)` 还有 `setCharAt(int index, char c)`
    + `insert(int index, content)` : 在指定位置处插入内容，内容可为任一基本类型或者对象，原本的内容向后移动  ==可以用来reverse== [[0557]](#[0557] Reverse Words in a String III) Solution 4

******

****

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

### Tree

#### ***Terminology used in trees***

- Depth of node - the number of edges from the tree's root node to the node （从root到自己）
- Height of node - the number of edges on the longest path between that node and a leaf （从自己到leaf）
- Height of Tree - the height of its root node （从root到leaf）==决定了各operations的Time Complexity== 

********

#### ***Binary Search Tree的三个基本operations***

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

#### ***Height-balanced (or self-balancing) BST***

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

### Misc.

#### 判断char是否为空字符或者空格

+ 空字符：`char == '\0'`
+ 空格: `Character.isSpace(char)` 或者 `char == ' '`

********

******







******

**********

## *Problems*

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

### Binary Tree Traversal

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

### Design Data Structure

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

*******

### Dynamic Programming

#### Pascal's Triangle

##### [0118] Pascal's Triangle

> 给定行数 `numRows`，填帕斯卡三角

一开始就是按照Iteration做的，看了Solution发现其实可以理解为DP。

![](/Users/shanyonghao/IdeaProjects/LeetCodeProblems/Notes_img/[0118]_3.jpeg)

*性质：*

+ 每行第一个和最后一个元素一定为1；
+ $f(i,j) = f(i-1,j-1)+f(i-1,j)$, where $f(i,j)$ is the element in the $i^{th}$ row and $j^{th}$ column of Pascal's triangle.

```java
public List<List<Integer>> generate(int numRows) {
  List<List<Integer>> res = new ArrayList<>();

  for (int i = 0; i < numRows; i++) {
    List<Integer> row = new ArrayList<>();
    row.add(1);  // the first digit is always 1

    for (int j = 1; j < i; j++) 
      row.add(res.get(i-1).get(j-1) + res.get(i-1).get(j));

    if (i > 0) row.add(1); // Once the length of this row is bigger than 1, then the last digit is always 1
    res.add(row);
  }
}
```

*Time Complexity Analysis:*

很明显outer loop runs numRows times；而每一次inner loop循环的次数等于rowIndex (因为帕斯卡三角第一行有一个元素，第二行有两个元素，...)
$\implies$ 总的次数为$1+2+3+\dots+numRows = \frac{numRows(numRows+1)}{2} = \frac{numRows^2}{2} + \frac{numRows}{2} \implies \mathcal{O}(numRows^2)$

******

##### [0119] Pascal's Triangle II

> 给定 `rowIndex`，返回帕斯卡三角（0-indexed）该行的内容。

+ Solution 1: Recursion

  ```java
  public List<Integer> getRow(int rowIndex) {
    if (rowIndex > 1) prev = getRow(rowIndex-1);  // 提前取好getRow(rowIndex-1)，不然重复取getRow(rowIndex-1)会导致TLE
  
    List<Integer> res = new ArrayList<>();
    res.add(1);
    for (int i = 1; i < rowIndex; i++) {
      res.add(prev.get(i-1) + prev.get(i));
    }
    if (rowIndex > 0) res.add(1);
    return res;
  }
  ```

  *Time Complexity:* $\mathcal{O}(rowIndex^2)$   ?

  *Space Complexity:* $\mathcal{O}(rowIndex^2)$ for stack

+ Solution 2: DP with 2D array

  ```java
  public List<Integer> getRow(int rowIndex) {
      // 这里dp并没有选择Integer[]，而为了保持初始值为0，选择int[]
      int[][] dp = new int[rowIndex+1][rowIndex+1]; // 是从第0行开始的，所以宽度为rowIndex+1
      // 将每行行首赋为1
      for (int i = 0; i < rowIndex+1; i++) dp[i][0] = 1;
      // 更新：
      for (int i = 1; i < dp.length; i++) // 从第二行开始更新
          for (int j = 1; j < rowIndex+1; j++)
              dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
      // 将int[]转为List
      List<Integer> res = Arrays.stream(dp[rowIndex]).boxed().collect(Collectors.toList()); // 也可以在上面更新时当j=rowIndex时，将dp[i][j]存入List
      return res;
  }
  ```

  *Time Complexity:* $\mathcal{O}(rowIndex^2)$  

  *Space Complexity:* $\mathcal{O}(rowIndex^2)$ for 2D DP array

+ Solution 3: DP with 1D array （滚动数组）

  ```java
  public List<Integer> getRow(int rowIndex) {
      Integer[] dp = new Integer[rowIndex + 1]; // 这里dp是Integer[]，方便之后转为list！
    	// 但因为Integer[]的初始值为null，所以需要重新初始化
      Arrays.fill(dp,1);
      for(int i = 2;i < dp.length;i++) // 从第三行开始更新
          for(int j = i - 1;j > 0;j--) // 需要依据前面的值来更新自己，所以要倒着更新
              dp[j] = dp[j] + dp[j - 1];
    	// 将Integer[]转为List
      List<Integer> res = Arrays.asList(dp);
      return res;
  }
  ```

  *Time Complexity:* $\mathcal{O}(rowIndex^2)$  

  *Space Complexity:* $\mathcal{O}(rowIndex)$ for 1D DP array

+ Solution 4: Maths

  The entry in the $n^{th}$ row and $k^{th}$ column of Pascal's triangle is denoted $\dbinom{n}{r}$ and $\dbinom{n}{r}=\dbinom{n-1}{r-1}+\dbinom{n-1}{r}$.

  已知 $\dbinom{n}{r}=\dfrac{n!}{r!(n-r)!}$ ，则
  $$
  \begin{gathered}
  \frac{\dbinom{n}{r}}{\dbinom{n}{r-1}} = \frac{\dfrac{n!}{r!(n-r)!}}{\dfrac{n!}{(r-1)!(n-r+1)!}} = \frac{n-r+1}{r} \\
  \implies \dbinom{n}{r} = \frac{n-r+1}{r}\dbinom{n}{r-1}
  \end{gathered}
  $$
  $\implies$ 并不一定需要上一行的元素才能计算出本行的元素，==同一行也可以根据前一个元素计算出后一位元素==。

  ```java
  public List<Integer> getRow(int rowIndex) {
      int i = rowIndex;
      List<Integer> res = new ArrayList<>();;
      res.add(1);
      for (int j = 0; j < rowIndex; j++) {
          res.add((int) ((res.get(j) * (long) (rowIndex-(j+1)+1)) / (j+1)));
      }
  
      return res;
  }
  ```

  *Time Complexity:* $\mathcal{O}(rowIndex)$  

  *Space Complexity:* $\mathcal{O}(rowIndex)$​ 

********

#### 路径问题

##### 类型一

###### [0062] Unique Paths

> A robot is located at the top-left corner of a $m\times n$ ==$(1\le m,n\le 100)$== grid (marked 'Start' in the diagram below). The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below). How many possible unique paths are there?
>

![](/Users/shanyonghao/IdeaProjects/LeetCodeProblems/Notes_img/[0062].png)

*思路：* ==数据范围为 $10^2～10^3$，二维数组DP肯定可以==

定义 `f[i][j]` 为到达位置 `(i,j)` 的不同路径数量。

那么 `f[n-1][m-1]` 就是我们最终的答案，而 `f[0][0] = 1` 是一个显而易见的起始条件。

由于题目限定了我们只能 **往下** 或者 **往右** 移动，因此我们按照**「当前可选方向」**进行分析：

1. 当前位置只能由上一位置 **「往下」** 移动到达，即有 `f[i][j] = f[i-1][j] `  *i.e. 第一纵列 (j=0)*
2. 当前位置只能由上一位置 **「往右」** 移动到达，即有 `f[i][j] = f[i][j-1]`  *i.e. 第一横行 (i=0)*
3. 当前位置既能由上一位置 **「往下」** 也能 **「往右」** 移动到达，即有 `f[i][j] = f[i][j-1] + f[i-1][j]`

```java
public int uniquePaths(int m, int n) {
  int[][] f = new int[m][n];
  f[0][0] = 1;
  for (int i = 0; i < m; i++) {
    for (int j = 0; j < n; j++) {
      if (i > 0 && j > 0) {
        f[i][j] = f[i][j-1] + f[i-1][j];
      } else if (j > 0) {
        f[i][j] = f[i][j-1];
      } else if (i > 0) {
        f[i][j] = f[i-1][j];
      }
    }
  }
  return f[m-1][n-1];
}
```

也可以首先把第一横行和第一纵列先置为1，循环从 `i=1, j=1` 开始：

```java
public int uniquePaths(int m, int n) {
  int[][] f = new int[m][n];
  for(int[] arr : f) Arrays.fill(arr, 1);
  for (int i = 1; i < m; i++) {
    for (int j = 1; j < n; j++) {
      f[i][j] = f[i][j-1] + f[i-1][j];
    }
  }
  return f[m-1][n-1];
}
```

采用滚动数组的技巧只使用一维数组：

```java
public int uniquePaths(int m, int n) {
  int[] f = new int[n];
  Arrays.fill(f, 1);
  for (int i = 1; i < m; i++) {
    for (int j = 1; j < n; j++) {
      f[j] = f[j-1] + f[j];
    }
  }
  return f[n-1];
}
```

*Time Complexity:* $\mathcal{O}(m \times n)$

*Space Complexity:* $\mathcal{O}(m \times n)$​ or $\mathcal{O}(n)$​

*补充：* 如果题目==不限制移动方向==，就不可以用DP了，而是变成了图论问题。同时问题也要作修改，如果不限制每个格子的访问次数，路径必然为无数条。

******

###### [0063] Unique Paths II

和[0062]相比，区别在于机器人行进的格子中可能有障碍物。An obstacle and space is marked as `1` and `0` respectively in the grid.

*思路：* 和上一题一模一样的思路，只需要：==由于某些格子上有障碍物，对于 `obstacleGrid[i][j]==1` 的格子，则有 `f[i][j] = 0`。==

```java
public int uniquePathsWithObstacles(int[][] obstacleGrid) {
  int m = obstacleGrid.length, n = obstacleGrid[0].length;
  int[][] f = new int[m][n];
  f[0][0] = obstacleGrid[0][0] == 1 ? 0 : 1; // 题目说了机器人初始位于top-left conner，test case还把obstacleGrid[0][0]有可能是障碍物，无语。。。
  for (int i = 0; i < m; i++) {
    for (int j = 0; j < n; j++) {
      // 对于obstacleGrid[i][j]为1的f[i][j]，不需要更新，初始即为0
      if (obstacleGrid[i][j] != 1) { 
        if (i > 0 && j > 0) {
          f[i][j] = f[i][j-1] + f[i-1][j];
        } else if (j > 0) {
          f[i][j] = f[i][j-1];
        } else if (i > 0) {
          f[i][j] = f[i-1][j];
        }
      }
    }
  }
  return f[m-1][n-1];
}
```

*Time Complexity:* $\mathcal{O}(m \times n)$

*Space Complexity:* $\mathcal{O}(m \times n)$​​ or ==$\mathcal{O}(1)$​​ when using `obstacleGrid` as DP array​==

************

###### [0064] Minimum Path Sum

> Given a `m x n` `grid` filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path. Note: You can only move either down or right at any point in time.
>

在 [[0062]](#[0062] Unique Paths) 的基础上，增加了路径成本概念。

*思路：*根据问题来调整「状态定义」：定义 `f[i][j]` 为从 `(0,0)` 开始到达位置 `(i,j)` 的最小总和。

那么 `f[m-1][n-1]` 就是我们最终的答案，`f[0][0] = grid[0][0]` 是一个显而易见的起始状态。

由于题目限定了只能 **往下** 或者 **往右** 移动，因此按照**「当前位置可由哪些位置转移过来」**进行分析：

1. 当前位置只能通过 **「往下」** 移动而来，即有 `f[i][j] = f[i-1][j] + grid[i][j]`  *i.e. 第一纵列 (j=0)*
2. 当前位置只能通过 **「往右」** 移动而来，即有 `f[i][j] = f[i][j-1] + grid[i][j]`  *i.e. 第一横行 (i=0)*
3. 当前位置既能通过 **「往下」** 也能 **「往右」** 移动而来，即有 `f[i][j] = min(f[i][j-1],f[i-1][j]) + grid[i][j] = min(f[i][j-1] + grid[i][j], f[i-1][j] + grid[i][j])`

```java
public int minPathSum(int[][] grid) {        
  int m = grid.length, n = grid[0].length;
  int[][] f = new int[m][n];
  for (int i = 0; i < m; i++) {
    for (int j = 0; j < n; j++) {
      if (i == 0 && j == 0) {
        f[i][j] = grid[i][j];
      } else {
        int top  = i - 1 >= 0 ? f[i - 1][j] + grid[i][j] : Integer.MAX_VALUE;
        int left = j - 1 >= 0 ? f[i][j - 1] + grid[i][j] : Integer.MAX_VALUE;
        f[i][j] = Math.min(top, left);
      }
    }
  }
  return f[m - 1][n - 1];
}
```

*Time Complexity:* $\mathcal{O}(m \times n)$

*Space Complexity:* $\mathcal{O}(m \times n)$​​ or ==$\mathcal{O}(1)$​​​ when using `grid` as DP array​==

==*进阶 I：*== **如果要输出总和最低的路径呢？**

从原问题我们知道，需要从 (0,0) 一步步转移到 (m-1,n-1)，也就是需要扫描完整个方块（转移完所有的状态），才能得到答案。那么显然，可以使用额外的数据结构来记录，是如何一步步转移到 `f[m-1][n-1]` 的。

当整个 dp 过程结束后，再用辅助记录的数据结构来回推我们的路径。

同时，由于原有的 dp 部分已经创建了一个二维数组来存储状态值，这次用于记录「上一步」的 g 数组我们改用一维数组来记录。==**Trick I**==

```java
int m, n;
public int minPathSum(int[][] grid) {        
  m = grid.length;
  n = grid[0].length;
  int[][] f = new int[m][n];
  int[] g = new int[m * n];
  for (int i = 0; i < m; i++) {
    for (int j = 0; j < n; j++) {
      if (i == 0 && j == 0) {
        f[i][j] = grid[i][j];
      } else {
        int top  = i - 1 >= 0 ? f[i - 1][j] + grid[i][j] : Integer.MAX_VALUE;
        int left = j - 1 >= 0 ? f[i][j - 1] + grid[i][j] : Integer.MAX_VALUE;
        f[i][j] = Math.min(top, left);
        g[getIdx(i, j)] = top < left ? getIdx(i - 1, j) : getIdx(i, j - 1);
      }
    }
  }

  // 从「结尾」开始，在 g[] 数组中找「上一步」
  int idx = getIdx(m - 1, n - 1);
  // 逆序将路径点添加到 path 数组中
  int[][] path = new int[m + n][2];
  path[m + n - 1] = new int[]{m - 1, n - 1};
  for (int i = 1; i < m + n; i++) {
    path[m + n - 1 - i] = parseIdx(g[idx]);
    idx = g[idx];
  }
  // 顺序输出位置
  for (int i = 1; i < m + n; i++) {
    int x = path[i][0], y = path[i][1];
    System.out.print("(" + x + "," + y + ") ");
  }
  System.out.println(" ");

  return f[m - 1][n - 1];
}

int[] parseIdx(int idx) {
  return new int[]{idx / n, idx % n};
}

int getIdx(int x, int y) {
  return x * n + y;
}
```

上述「输出」方案的代码麻烦的原因是因为找路径的过程是「倒着」找，而输出方案的时候则是「顺着」输出。

==**Trick II**==  如果希望简化找路径的过程，需要对原问题进行 **等价转换**：

**将 「(0,0) 到 (m-1,n-1) 的最短路径」转换为「从 (m-1,n-1) 到 (0,0) 的最短路径」，同时移动方向从「向下 & 向右」转换为「向上 & 向左」。**

这样我们就能实现「找路径」的顺序和「输出」顺序同向。

调整「状态定义」 `f[i][j]` 为从 `(m-1,n-1)` 开始到达位置 `(i,j)` 的最小总和。

```java
int m, n;
public int minPathSum(int[][] grid) {        
  m = grid.length;
  n = grid[0].length;
  int[][] f = new int[m][n];
  int[] g = new int[m * n];
  for (int i = m - 1; i >= 0; i--) {
    for (int j = n - 1; j >= 0; j--) {
      if (i == m - 1 && j == n - 1) {
        f[i][j] = grid[i][j];
      } else {
        int bottom = i + 1 < m ? f[i + 1][j] + grid[i][j] : Integer.MAX_VALUE;
        int right  = j + 1 < n ? f[i][j + 1] + grid[i][j] : Integer.MAX_VALUE; 
        f[i][j] = Math.min(bottom, right);
        g[getIdx(i, j)] = bottom < right ? getIdx(i + 1, j) : getIdx(i, j + 1);
      }
    }
  }

  int idx = getIdx(0,0);
  for (int i = 1; i <= m + n; i++) {
    if (i == m + n) continue;
    int x = parseIdx(idx)[0], y = parseIdx(idx)[1];
    System.out.print("(" + x + "," + y + ") ");
    idx = g[idx];
  }
  System.out.println(" ");

  return f[0][0];
}

int[] parseIdx(int idx) {
  return new int[]{idx / n, idx % n};
}

int getIdx(int x, int y) {
  return x * n + y;
}
```

*==进阶 II：==* **如果方块中存在负权值，如何求解？**

如果只是增加负权值的条件，走动规则不变（只能往下或往右），那么 DP 仍然有效。仍然能够得到「总成本最小」的路径，但不确保成本必然为负权，也不确保必然会经过负权位置。

==*进阶 III：*== **如果走动规则调整为「可以往任意方向」且「每个位置最多只能访问一次」？**

这时候问题就转换为「图论最短路」问题，而且是从「特定源点」到「特定汇点」的「单源最短路」问题。 

需要根据是否存在「负权边」来分情况讨论：

1. 不存在负权边：使用 Dijkstra 算法求解 
2. 存在负权边：使用 Bellman Ford 或 SPFA 求解

*******

###### [0120] Triangle

> Given a `triangle` array, return *the minimum path sum from top to bottom*.
>
> For each step, you may move to an adjacent number of the row below. More formally, if you are on index `i` on the current row, you may move to either index `i` or index `i + 1` on the next row.
>

==还是DP在Pascal's Triangle的应用==

*分析：*

与[0064]相比，发生以下变化：

+ 矩阵形状发生改变（其实只影响列数 $j$​ 的范围）；
+ 移动方向由「向下」和「向右」改为「向下」和「向右下」；
+ 终点不再限制为最后一行最后一列（多了一个遍历矩阵 `f` 最后一行的循环）。

$$
\begin{gathered}
\underline{2} \\
\underline{3}\ \ 4\\
6\ \ \underline{5}\ \ 7\\
4\ \ \underline{1}\ \ 8\ \ 3\\
\end{gathered}
\rightarrow\ 
\begin{matrix}
\underline{2} &   &   & \\
\underline{3} & 4 &   & \\
6 & \underline{5} & 7 & \\
4 & \underline{1} & 8 & 3 
\end{matrix}
$$
令 $i$ 为行坐标，$j$ 为列坐标，有以下性质：

+ 第 $i$ 行有 $i+1$ 个元素；
+ 第一列 *(i.e. $j=0$)* 的元素都只能通过其「上方」转移过来 $\implies$​ 只要不是第一列的元素，都可通过「左上方」转移过来；
+ 最后一列 *(i.e. $j=i$)* 的元素都只能通过其「左上方」转移过来 $\implies$ 只要不是最后一列的元素，都可通过「上方」转移过来；

对于本题，「状态定义」：`f[i][j]` 代表到达点 $(i,j)$ 的最小路径和。

那么 $min(f[n-1][i]),i\in [0,n]$​（最后一行的每列的路径和的最小值）就是答案。

```java
public int minimumTotal(List<List<Integer>> tri) {
  int n = tri.size();
  int ans = Integer.MAX_VALUE;
  int[][] f = new int[n][n];
  f[0][0] = tri.get(0).get(0);
  for (int i = 1; i < n; i++) { // 第0行只有一个元素
    for (int j = 0; j < i + 1; j++) {
      // 内部循环体写法一：
      int val = tri.get(i).get(j);
      f[i][j] = Integer.MAX_VALUE;
      if (j != 0) f[i][j] = f[i - 1][j - 1] + val;
      if (j != i) f[i][j] = Math.min(f[i][j], f[i - 1][j] + val); // 只要j在(0,i)之间，则上一步会执行，f[i][j]不再为MAX_VALUE，故这一步的min判断不可省
    }
  }
  for (int i = 0; i < n; i++) ans = Math.min(ans, f[n - 1][i]);
  return ans;
}
```

最内层的循环体也可写为：

```java
// 内部循环体写法二：
int val = tri.get(i).get(j);
if (j == 0) // 只能由上方元素转移得来
    f[i][j] = f[i - 1][j] + val;
else if (j == i) // 只能由左上方元素转移得来
    f[i][j] = f[i-1][j-1] + val;
else
    f[i][j] = Math.min(f[i-1][j], f[i-1][j-1]) + val;
```

或者参考[0064]的写法：

```java
// 内部循环体写法三：
int val = tri.get(i).get(j);
int top  = j != i ? f[i - 1][j] + val : Integer.MAX_VALUE;
int leftTop = j != 0 ? f[i - 1][j - 1] + val : Integer.MAX_VALUE;
f[i][j] = Math.min(top, leftTop);
```

内部循环体三种写法performance相同。

*Time Complexity:* $\mathcal{O}(n^2)$​​​

*Space Complexity:* $\mathcal{O}(n^2)$

采用技巧改用一维数组或将二维中的任一维降为常数：==为了不覆盖仍需要的值，其中一个循环的遍历方向改为倒序==

+ $j$​ 的循环改为倒序：

  ```java
  public int minimumTotal(List<List<Integer>> tri) {
    int n = tri.size();
    int ans = Integer.MAX_VALUE;
    int[] f = new int[n];
    f[0] = tri.get(0).get(0);
    for (int i = 1; i < n; i++) { 
      for (int j = i; j >= 0; j--) {
        int val = tri.get(i).get(j);
        if (j == i) f[j] = f[j-1] + val;
        if (j < i && j > 0) f[j] = Math.min(f[j-1], f[j]) + val;
        if (j == 0) f[j] += val;
      }
    }
    for (int i = 0; i < n; i++) ans = Math.min(ans, f[i]);
    return ans;
  }
  ```

+ $i$​​ 的循环改为倒序：==**代码最简单，表现也好**==

  ```java
  public int minimumTotal(List<List<Integer>> tri) {
    int n = tri.size();
    int[] f = new int[n];
    for (int i = 0; i < n; i++) f[i] = tri.get(tri.size()-1).get(i); // 即f初始化为tri的最后一行
    for (int i = n-2; i >= 0; i--) { 
      for (int j = 0; j <= i; j++) {
        int val = tri.get(i).get(j);
        f[j] = Math.min(f[j], f[j+1]) + val;
      }
    }
    return f[0];
  }
  ```

+ 在原代码上直接将其中一维改为 $2$，任何在该维的 `f[i]` 改成 `f[i&1]` 或者 `f[i%2]` 即可（推荐前者，在不同架构的机器上，运算效率更加稳定）

  ```java
  public int minimumTotal(List<List<Integer>> tri) {
    int n = tri.size();
    int ans = Integer.MAX_VALUE;
    int[][] f = new int[2][n];
    f[0][0] = tri.get(0).get(0);
    for (int i = 1; i < n; i++) {
      for (int j = 0; j < i + 1; j++) {
        int val = tri.get(i).get(j);
        f[i & 1][j] = Integer.MAX_VALUE;
        if (j != 0) f[i & 1][j] = Math.min(f[i & 1][j], f[(i - 1) & 1][j - 1] + val);
        if (j != i) f[i & 1][j] = Math.min(f[i & 1][j], f[(i - 1) & 1][j] + val);
      }
    }
    for (int i = 0; i < n; i++) ans = Math.min(ans, f[(n - 1) & 1][i]);
    return ans;
  }
  ```

******

###### [0931] Minimum Falling Path Sum

> Given an `n x n` array of integers `matrix`, return *the **minimum sum** of any **falling path** through* `matrix`.
>
> A **falling path** starts at any element in the first row and chooses the element in the next row that is either directly below or diagonally left/right. Specifically, the next element from position `(row, col)` will be `(row + 1, col - 1)`, `(row + 1, col)`, or `(row + 1, col + 1)`.
>

*分析：*

与[[0120]](#[0120] Triangle)相比，放松了两个限制：

+ 不必只能从 [0,0] 出发，而是第一行任一元素；
+ 可以向「左下方」 / 「正下方」 / 「右下方」移动。

因此，问题分为两部分：

+ 枚举第一行元素作为起点；  $\implies \mathcal{O}(n)$
+ 模仿[0120]的代码，定义函数 `find()`，传入「矩阵」和「起点在第一行中的下标」，返回以该起点得到的最小路径和。$\implies \mathcal{O}(n^2)$

最终答案为所有返回的最小路径和的最小值。此时，TC为 $\mathcal{O}(n^3)$​。本题数据只有 $10^2$​，因此计算量是 $10^6$​，是可以过的。<u>(即使是最严格的 OJ 中最简单的题目，也会提供 1s 的运行时间，超过这个时间才算超时。计算器 1s 内极限的处理速度是 $10^8$​，但为了尽可能不出现错误提交，使用技巧时尽量和 $10^7$​​​ 进行比较)</u>

```java
int MAX = Integer.MAX_VALUE;
public int minFallingPathSum(int[][] matrix) {
  int n = matrix.length;
  int ans = MAX;
  for (int i = 0; i < n; i++) ans = Math.min(ans, find(matrix, i));
  return ans;
}

public int find(int[][] matrix, int idx) {
  int n = matrix.length;
  int ans = MAX;
  int[][] f = new int[n][n];
  // f矩阵的第一行除了本次寻找的起点为原矩阵matrix中的对应值，其余全置为MAX
  for (int i = 0; i < n; i++) f[0][i] = i == idx ? matrix[0][i] : MAX;
  for (int i = 1; i < n; i++) { // 第0行只有一个元素
    for (int j = 0; j < n; j++) {
      int val = matrix[i][j];
      f[i][j] = MAX;
      // 下面if判断中，与MAX比较，是防止其值为MAX的情况下再加val导致overflow
      if (f[i - 1][j] != MAX) f[i][j] = f[i - 1][j] + val; // 由上方移动得来
      if (j - 1 >= 0 && f[i - 1][j - 1] != MAX) f[i][j] = Math.min(f[i][j], f[i - 1][j - 1] + val); // 由左上方移动得来
      if (j + 1 < n && f[i - 1][j + 1] != MAX) f[i][j] = Math.min(f[i][j], f[i - 1][j + 1] + val); // 由右上方移动得来
    }
  }
  // 到此，f矩阵已填完所有值。遍历f矩阵的最后一行找到最小值即为答案
  for (int i = 0; i < n; i++) ans = Math.min(ans, f[n - 1][i]);
  return ans;
}
```

因为最内层循环涉及到三个值取最小，不再使用[0120]中的写法二和写法三。

```java
if (j == 0 && f[i - 1][j] != MAX && f[i - 1][j + 1] != MAX) // 只能从上方和右上方得来
  f[i][j] = Math.min(f[i - 1][j], f[i - 1][j + 1]) + val;
else if (j == n-1 && f[i - 1][j] != MAX && f[i - 1][j - 1] != MAX) // 只能从上方和左上方得来
  f[i][j] = Math.min(f[i - 1][j], f[i - 1][j - 1]) + val;
else if (f[i - 1][j] != MAX && f[i - 1][j - 1] != MAX && f[i - 1][j + 1] != MAX)
  f[i][j] = Math.min(f[i-1][j], Math.max(f[i-1][j-1], f[i-1][j+1])) + val;
```

*Time Complexity:* $\mathcal{O}(n^3)$​​​​

*Space Complexity:* $\mathcal{O}(n^2)$​

==在刚做完[0064]和[0120]的情况下，都会因为起点的改变而开始纠结起点，但反而**让算法复杂度会上升一个级别。**==

其实，这题也就是常规的路径题，==对于起点的不确定性，只需改动矩阵 `f` 的初始化即可，从而省去枚举起点的复杂度 $\mathcal{O}(n)$​​。== *DP状态转移部分 $\mathcal{O}(n^2)$ 是无法再优化的。*

```java
public int minFallingPathSum(int[][] matrix) {
  int n = matrix.length;
  int[][] f = new int[n][n];
  // 初始化：对于首行而言，每个位置的「最小成本」就是其「矩阵值」
  for (int i = 0; i < n; i++) f[0][i] = matrix[0][i];
  // 从第二行开始，根据题目给定的条件进行转移
  for (int i = 1; i < n; i++) {
    for (int j = 0; j < n; j++) {
      int val = matrix[i][j];
      f[i][j] = f[i - 1][j] + val;
      if (j - 1 >= 0) f[i][j] = Math.min(f[i][j], f[i-1][j-1] + val);
      if (j + 1 <  n) f[i][j] = Math.min(f[i][j], f[i-1][j+1] + val);
    }
  }
  int ans = Integer.MAX_VALUE;
  for (int i = 0; i < n; i++) ans = Math.min(ans, f[n-1][i]);
  return ans;
}
```

*Time Complexity:* $\mathcal{O}(n^2)$​​​​​

*Space Complexity:* $\mathcal{O}(n^2)$​​

******

###### [1289] Minimum Falling Path Sum II

> Given a square grid of integers `arr`, a *falling path with non-zero shifts* is a choice of exactly one element from each row of `arr`, such that no two elements chosen in adjacent rows are in the same column.
>
> Return the minimum sum of a falling path with non-zero shifts.
>

*分析：*与[[0931]](#[0931] Minimum Falling Path Sum)相比，这道题的“非零偏移下降路径”其实就是规定了路径不能朝「正下方」移动，其余所有方向都可以。==**因此在更新DP数组当前行的值时，需要枚举上一行除同一列以外的所有列（for循环）**==。仍可以使用[Template](#Template)求解。

```java
public int minFallingPathSum(int[][] arr) {
  // Step 1: 定义dp array
  int n = arr.length;
  int[][] f = new int[n][n];

  // Step 2: 初始化dp array：起点为第一行任一元素
  for (int i = 0; i < n; i++) f[0][i] = arr[0][i];

  // Step 3: 从第二行进行状态转移
  for (int i = 1; i < n; i++) { // 从f的第二行开始更新
    for (int j = 0; j < n; j++) {
      f[i][j] = Integer.MAX_VALUE;
      int val = arr[i][j];
      // 具体根据移动方向的限制而定：非同列（需要枚举上一行除同一列以外的所有列）
      for (int k = 0; k < n; k++) {
        if (k != j) f[i][j] = Math.min(f[i][j], f[i-1][k] + val);
      }
    }
  }

  // Step 4: 取结果：终点为f array最后一行中的最小值
  int ans = Integer.MAX_VALUE;
  for (int i = 0; i < n; i++) ans = Math.min(ans, f[n-1][i]);
  return ans;
}
```

*Time Complexity:* $\mathcal{O}(n^3)$     题目范围为 $10^2$​，因此计算量为 $10^6$，可以通过。​​

*Space Complexity:* $\mathcal{O}(n^2)$​​​

*优化：*和[0931]一样，DP状态转移部分 $\mathcal{O}(n^2)$​​ 是无法再优化的。因此，只能优化<u>每次转移时，枚举上一行所有列（除同列）</u>。==**最佳**==  ==**Clever Trick**==

其实细想就可以发现，当我们在计算某行的状态值的时候，只会用到「上一行」的两个值:**「最小值」**和**「次小值」**。

如，当我们已经处理完第 $i-1$ 行的状态值:

假设第 $i-1$ 行状态中的最小值对应的列下标是 $i_1$，次小值对应的列下标是 $i_2$ 。

那么当我们处理第 $i$ 行时，显然有：

- 处理第 $i$ 行中列下标为 $i_1$ 的状态值时，由于不能选择「正上方」的数字，用到的是**次小值**。转移方程为：
  $$
  f[i][j] = f[i-1][i_2] + arr[i][j]
  $$

- 处理第 $i$ 行其他列下标的状态值时，这时候用到的是**最小值**。转移方程为：
  $$
  f[i][j] = f[i-1][i_1] + arr[i][j]
  $$

<img src="/Users/shanyonghao/IdeaProjects/LeetCodeProblems/Notes_img/[1289].png" style="zoom:50%;" />

```java
int MAX = Integer.MAX_VALUE;
public int minFallingPathSum(int[][] arr) {
  // Step 1: 定义dp array
  int n = arr.length;
  int[][] f = new int[n][n];

  // i1代表最小值的列下标；i2代表次小值的列下标
  int i1 = -1, i2 = -1;

  // Step 2: 初始化dp array：起点为第一行任一元素；找到第一行的i1和i2。
  for (int i = 0; i < n; i++) {
    // 初始化dp array
    int val = arr[0][i];
    f[0][i] = val;
    // 更新 i1 和 i2
    if (val < (i1 == -1 ? MAX : f[0][i1])) {
      i2 = i1;
      i1 = i;
    } else if (val < (i2 == -1 ? MAX : f[0][i2])) {
      i2 = i;
    }
  } 

  // Step 3: 从第二行进行状态转移
  for (int i = 1; i < n; i++) { // 从f的第二行开始更新
    // 每到新的一行，也需要找该行的最小值 ti1 和次小值 ti2
    int ti1 = -1, ti2 = -1;

    for (int j = 0; j < n; j++) {
      f[i][j] = MAX;
      int val = arr[i][j];
      // 更新动规值
      // 可以选择上一行「最小值」的列选择「最小值」i1
      if (j != i1)
        f[i][j] = f[i - 1][i1] + val;
      // 不能选择上一行「最小值」的列选择「次小值」i2
      else
        f[i][j] = f[i - 1][i2] + val;

      // 更新本行的最小值ti1和次小值ti2
      if (f[i][j] < (ti1 == -1 ? MAX : f[i][ti1])) {
        ti2 = ti1;
        ti1 = j;
      } else if (f[i][j] < (ti2 == -1 ? MAX : f[i][ti2])) {
        ti2 = j;
      }
    }
    // 本行遍历结束，利用当前行最小值ti1和次小值ti2更新i1和i2
    i1 = ti1;
    i2 = ti2;
  }

  // Step 4: 取结果：终点为f array最后一行中的最小值，即f[n-1][i1]
  return f[n-1][i1];
}
```

*Time Complexity:* $\mathcal{O}(n^2)$​​​​​

*Space Complexity:* $\mathcal{O}(n^2)$​​​

******

###### [1301] Number of Paths with Max Score

> You are given a square `board` of characters. You can move on the board starting at the bottom right square marked with the character `'S'`.
>
> You need to reach the top left square marked with the character `'E'`. The rest of the squares are labeled either with a numeric character `1, 2, ..., 9` or with an obstacle `'X'`. In one move you can go up, left or up-left (diagonally) only if there is no obstacle there.
>
> Return a list of two integers: the first integer is the maximum sum of numeric characters you can collect, and the second is the number of such paths that you can take to get that maximum sum, **taken modulo** `10^9 + 7`.
>
> In case there is no path, return `[0, 0]`.

*分析：*==**[0062] / [0063] / [0064]的综合题**==  规定了「起点」、「终点」以及「移动方向」，设置了「路径成本」和「障碍」，最终结果要求包含最大路径成本以及获得该最大成本的路径数 $\implies$ 两个单独的DP数组记录信息*（没有必要用一个DP数组去完全两个任务，只会因为增加维度而增加难度）*

+ 最大路径成本问题：

  「状态定义」：$f[(x,y)]=f[index]$​​​​：从起点 $(n-1,n-1)$​​​​ 到下标为 $index=x*n+y$​​​​​ 的grid的最大路径成本；

  「状态转移方程」：$f[(x,y)]=max(f[(x+1,y)],f[(x,y+1)],f[(x+1,y+1)])+board[(x,y)]$​

  ==与[0063]中的技巧类似，将「障碍」所对应的动规值设为 INF。==

  $\implies f[0]$ 即为最终答案 

+ 最大路径成本路径数问题：

  「状态定义」：$g[(x,y)]=g[index]$​​：使得从起点 $(n-1,n-1)$​​ 到下标为 $index=x*n+y$​​​ 的grid的路径成本最大的路径数量；

  「状态转移方程」：稍麻烦，分析见下

  由于某个位置可以由「下方」、「右方」和「右下方」三个位置转移而来。**同时**  $f[(x,y)]$ **是由三个位置的最大值转移过来，那么相应的** $g[(x,y)]$ **应该取到最大得分的转移位置的方案数。**

  ==**需要注意，最大值不一定是由一个位置得出。**==**如果有多个位置同时能取到最大得分，那么方案数应该是多个位置的方案数之和。**

  *e.g.* 如果可到达 $(x,y)$ 的三个位置 $((x+1,y),(x,y+1),(x+1,y+1))$ 的最大得分为 $3,4,5$，到达三个位置的方案数为 $1,2,2$ ，

  那么可得：
  $$
  \left\{
  \begin{array}{ll}
        f[(x,y)]=5+board[(x,y)]\\
        g[(x,y)]=2\\
  \end{array} 
  \right.
  $$
  但如果三个位置的最大得分为 $3，5，5$，到达三个位置的方案数为 $1，2，2$ 的话。由于同时取得最大值的位置有两个，那么方案数也应该是两个位置方案数之和。

  即有：
  $$
  \left\{
  \begin{array}{ll}
        f[(x,y)]=5+board[(x,y)]\\
        g[(x,y)]=2+2\\
  \end{array} 
  \right.
  $$

==**注意：**== 不同于Template中，将DP数组初始化和状态转移分开由两个循环完成。本题中DP数组的初始化和状态转移所需要的for循环结构完全一致，且状态转移只会用到已经初始化的元素，故将两者写在同一个for循环结构中，只需注意初始化部分写在状态转移部分之前。

```java
int n;
int INF = Integer.MIN_VALUE;
int mod = (int)1e9 + 7;
public int[] pathsWithMaxScore(List<String> board) {
  n = board.size();

  // 将 board 转为二维数组
  char[][] c = new char[n][n];
  for (int i = 0; i < n; i++) c[i] = board.get(i).toCharArray();

  // f(i) 代表从右下角起点 (n-1, n-1) 到位置 index 的最大得分
  int[] f = new int[n * n];
  // g(i) 代表从右下角起点 (n-1, n-1) 到位置 index 并取到最大得分的方案数量
  int[] g = new int[n * n];

  // 开始从起点位置 (n - 1, n - 1) 开始初始化和状态转移
  for (int x = n - 1; x >= 0; x--) {
    for (int y = n - 1; y >= 0; y--) {
      // 1.初始化部分：
      int idx = getIndex(x, y);

      // 1.1 如果为起点： 
      // g[idx] = 1 : 从起点(n-1, n-1)到起点的路径肯定存在一条，这样我们就有了一个「有效值」可以滚动下去
      // f[idx] = 0 : 代表在起点得分为 0
      if (c[x][y] == 'S') {
        g[idx] = 1; 
        continue;
      }

      // 1.2 如果为障碍点：
      // g[idx] = 0   : 「障碍点」不可访问，路径为 0
      // f[idx] = INF : 「障碍点」不可访问，得分为无效值
      if (c[x][y] == 'X') {
        f[idx] = INF;
        continue;
      }

      // 2. 状态转移：
      // 如果是第一个格子 (0, 0)（即终点），这时候位置得分为 0
      int val = c[x][y] == 'E' ? 0 : c[x][y] - '0';
      
      // u 代表当前位置的「最大得分」；t 代表取得最大得分的「方案数」
      int u = INF, t = 0;

      // “合法”定义为「不出界」
      // 即使将“合法”定义为「不出界且不为障碍物」（即if中多个判断：c[][] != 'X'），初始化中仍需要1.2
      // 2.1 「如果「下方格子」合法，尝试从「下方格子」进行转移：
      if (x + 1 < n) {
        int cur = f[getIndex(x + 1, y)] + val;
        int cnt = g[getIndex(x + 1, y)];
        int[] res = update(cur, cnt, u, t);
        u = res[0];
        t = res[1];
      }

      // 2.2 如果「右边格子」合法，尝试从「右边格子」进行转移：
      if (y + 1 < n) {
        int cur = f[getIndex(x, y + 1)] + val;
        int cnt = g[getIndex(x, y + 1)];
        int[] res = update(cur, cnt, u, t);
        u = res[0];
        t = res[1];
      }

      // 2.3 如果「右下角格子」合法，尝试从「右下角格子」进行转移：
      if (x + 1 < n && y + 1 < n) {
        int cur = f[getIndex(x + 1, y + 1)] + val;
        int cnt = g[getIndex(x + 1, y + 1)];
        int[] res = update(cur, cnt, u, t);
        u = res[0];
        t = res[1];
      }

      // 更新 dp 值
      f[idx] = u < 0 ? INF : u; // u 会小于0是因为当前格子只能由「障碍点」转移而来
      g[idx] = t;
    }
  }

  // System.out.println(Arrays.toString(f));

  // 构造答案：
  int[] ans = new int[2];
  if (f[getIndex(0, 0)] == INF) { // 如果终点不可达（动规值为 INF）时，写入 (0, 0)
    ans[0] = 0;
    ans[1] = 0;
  } else {
    ans[0] = f[getIndex(0, 0)];
    ans[1] = g[getIndex(0, 0)];
  }
  return ans;
}

int getIndex(int x, int y) {
  return x * n + y;
}

int[] parseIndex(int index) {
  return new int[]{index / n, index % n};
}

// 更新 dp 值: 单独写出来主要是因为g数组「状态转移方程」的特殊性
int[] update(int cur, int cnt, int u, int t) {
  // 起始答案为 [u, t] : u 为「最大得分」，t 为最大得分的「方案数」
  int[] ans = new int[]{u, t};

  // 如果当前值大于 u，更新「最大得分」和「方案数」
  if (cur > u) {
    ans[0] = cur;
    ans[1] = cnt;
  // 如果当前值等于 u，增加「方案数」
  } else if (cur == u && cur != INF) { // 重要！！！
    ans[1] += cnt;
  }

  ans[1] %= mod;
  return ans;
}
```

*Time Complexity:* $\mathcal{O}(n^2)$

*Space Complexity:* $\mathcal{O}(n^2)$​

**********

##### 类型二

###### [1575] Count All Possible Routes

> You are given an array of **distinct** positive integers locations where `locations[i]` represents the position of city `i`. You are also given integers `start`, `finish` and `fuel` representing the starting city, ending city, and the initial amount of fuel you have, respectively.
>
> At each step, if you are at city `i`, you can pick any city `j` such that `j != i` and `0 <= j < locations.length` and move to city `j`. Moving from city `i` to city `j` reduces the amount of fuel you have by `|locations[i] - locations[j]|`. Please notice that `|x|` denotes the absolute value of `x`.
>
> Notice that `fuel` **cannot** become negative at any point in time, and that you are **allowed** to visit any city more than once (including `start` and `finish`).
>
> Return *the count of all possible routes from* `start` *to* `finish`. Since the answer may be too large, return it modulo `10^9 + 7`.
>

![](/Users/shanyonghao/IdeaProjects/LeetCodeProblems/Notes_img/[1575].png)

*分析：*首先这道题不是传统的DP第一类路径问题。在还没有接触第二类路径问题时，很容易想到利用DFS解决。本题的数据范围虽然也只有 $10^2$​，但是单纯的DFS由于是指数级别的复杂度，通常数据范围不超过30。因此，如果使用DFS，要引入记忆化搜索（Memoization）。

+ Solution 1: DFS + Memoization

  + **DFS的实现步骤（重点是如何找Base case）**

    如果要实现 DFS 的话，通常有以下几个步骤：

    1. 设计好递归函数的「入参」和「出参」
    2. 设置好递归函数的出口（Base Case）==最难的一步==
    3. 编写「最小单元」处理逻辑

    **首先要明确，所谓的找 Base Case，其实是在确定什么样的情况下，算一次有效/无效。**

    **对于本题，找 Base Case 其实就是在确定：什么样的情况下，不能算作一条路径；什么样的情况下，可以算作 1 条路径。然后再在 DFS 过程中，不断的累加有效情况（算作路径数量为 1）的个数作为答案。**

    这是 DFS 的本质，也是找 Base Case 的思考过程。

    回到本题，对于 **有效情况** 的确立，十分简单直接，如果我们当前所在的位置 $i$ 就是目的地 $finish$ 的话，那就算成是一条有效路径，我们可以对路径数量进行 +1。

    那么如何确立 **无效情况** 呢？

    一个直观的感觉是当油量消耗完，所在位置又不在 $finish$，那么就算走到头了，算是一次「无效情况」，可以终止递归。

    逻辑上这没有错，但是存在油量始终无法为零的情况：
    $$
    \begin{aligned}
    &locations = [1,2,3,5] \\
    &start = 0 \\ 
    &finish = 3 \\
    &fuel = 1
    \end{aligned}
    $$
    我们当前位置在 0，想要到达 3，但是油量为 1，无法移动到任何位置。

    也就是如果我们只考虑 $fuel = 0$ 作为 Base Case 的话，递归可能无法终止。

    因此还要增加一个限制条件：**油量不为 0，但无法再移动到任何位置，也算是一次「无效情况」，可以终止递归。**

  + **Memoization所需要的缓存器的设计（即什么数据需要被memo）**

    我们用 $cache[i][fuel]$ 代表从位置 $i$ 出发，当前剩余的油量为 $fuel$​ 的前提下，到达目标位置的「路径数量」。

    之所以能采取「缓存中间结果」这样的做法，是因为「在 $i$ 和 $fuel$ 确定的情况下，其到达目的地的路径数量是唯一确定的」。

  ```java
  int mod = 1000000007;
  
  // 缓存器：用于记录「特定状态」下的中间结果
  // cache[i][fuel] 代表从位置 i 出发，当前剩余的油量为 fuel 的前提下，到达目标位置的「路径数量」
  int[][] cache;
  
  public int countRoutes(int[] ls, int start, int end, int fuel) {
    int n = ls.length;
  
    // 初始化缓存器
    // 之所以要初始化为 -1，是为了区分「某个状态下路径数量为 0」和「某个状态尚未没计算过」两种情况
    cache = new int[n][fuel + 1];
    for (int i = 0; i < n; i++) Arrays.fill(cache[i], -1);
  
    return dfs(ls, start, end, fuel);
  }
  
  /**
   * 计算「路径数量」
   * @param ls   入参 locations数组
   * @param u    当前所在位置（用ls中的下标表示）
   * @param end  目标位置（用ls中的下标表示）
   * @param fuel 剩余油量
   * @return     在位置 u 出发，油量为 fuel 的前提下，到达 end 的「路径数量」
   */
  int dfs(int[] ls, int u, int end, int fuel) {
    // 如果缓存器中已经有答案，直接返回
    if (cache[u][fuel] != -1) return cache[u][fuel];
  
    int n = ls.length;
    // base case 1：如果油量为 0，且不在目标位置 ==>> 将结果 0 写入缓存器并返回
    if (fuel == 0 && u != end) {
      cache[u][fuel] = 0;
      return 0;
    } 
  
    // base case 2：油量不为 0，且无法到达任何位置 ==>> 将结果 0 写入缓存器并返回
    boolean hasNext = false;
    for (int i = 0; i < n; i++) { // 遍历locations中的n-1个城市
      if (i != u) { // 非当前所在城市
        int need = Math.abs(ls[u] - ls[i]); // 计算从当前城市过去所需的fuel
        if (fuel >= need) {
          hasNext = true;
          break;
        }
      }
    }
    // 如果 u = end，那么本身就算一条路径 ---- 非常无语，但testcase就是这么设置的
    if (fuel != 0 && !hasNext) return cache[u][fuel] = u == end ? 1 : 0;
  
    // 计算油量为 fuel，从位置 u 到 end 的路径数量
    // 由于每个点都可以经过多次，如果 u = end，那么本身就算一条路径 ---- 非常无语，但testcase就是这么设置的
    int sum = u == end ? 1 : 0;
    for (int i = 0; i < n; i++) {
      if (i != u) {
        int need = Math.abs(ls[i] - ls[u]);
        if (fuel >= need) {
          sum += dfs(ls, i, end, fuel - need);
          sum %= mod; // 此处就开始mod是防止此时sum就overflow
        }
      }
    }
    cache[u][fuel] = sum;
    return sum;
  }
  ```

  *Time Complexity:* 最坏情况下共有 $n\times fuel$ 个状态需要计算（填满整个 $cache$ 数组）。每计算一个状态需要遍历一次 $locations$ 数组，复杂度为 $\mathcal{O}(n)$ $\implies$ 整体复杂度为 $\mathcal{O}(n^2\times fuel)$

  *Space Complexity:* $\mathcal{O}(n^2\times fuel)$​

  *优化：***简化「无效情况」**

  考虑一个问题：**如果我们从某个位置 出发，不能一步到达目标位置的话，有可能使用多步到达目标位置吗？**也就是一步不行的话，多步可以吗？答案是不可以。

  假设当前位置的 $locations[i]$ 为 $a$，目标位置的 $locations[finish]$ 为 $b$，两者差值的绝对值为 $need$，而当前油量是 $fuel$。不能一步到达，说明 $need > fuel$。

  而我们每次移动到新的位置，消耗的油量 $cost$ 都是两个位置的差值绝对值。正因为 $cost \ge 0$，因此移动到新位置后的油量 $fuel^{'} \le fuel$。换句话说，即使从位置 $i$ 移动到新位置，也无法改变 $need \ge fuel$ 的性质。

  $\implies$ 如果在某个位置 $u$ 出发，不能一步到达目的地 $finish$​，将永远无法到达目的地。

  ```java
  int mod = 1000000007;
  
  // 缓存器：用于记录「特定状态」下的中间结果
  // cache[i][fuel] 代表从位置 i 出发，当前剩余的油量为 fuel 的前提下，到达目标位置的「路径数量」
  int[][] cache;
  
  public int countRoutes(int[] ls, int start, int end, int fuel) {
    int n = ls.length;
  
    // 初始化缓存器
    // 之所以要初始化为 -1，是为了区分「某个状态下路径数量为 0」和「某个状态尚未没计算过」两种情况
    cache = new int[n][fuel + 1];
    for (int i = 0; i < n; i++) Arrays.fill(cache[i], -1);
  
    return dfs(ls, start, end, fuel);
  }
  
  /**
   * 计算「路径数量」
   * @param ls   入参 locations数组
   * @param u    当前所在位置（用ls中的下标表示）
   * @param end  目标位置（用ls中的下标表示）
   * @param fuel 剩余油量
   * @return     在位置 u 出发，油量为 fuel 的前提下，到达 end 的「路径数量」
   */
  int dfs(int[] ls, int u, int end, int fuel) {
    // 如果缓存器中已经有答案，直接返回
    if (cache[u][fuel] != -1) return cache[u][fuel];
  
    // base case：如果一步到达不了，说明从位置 u 不能到达 end 位置 ==>> 将结果 0 写入缓存器并返回
    int need = Math.abs(ls[u] - ls[end]);
    if (need > fuel) {
      cache[u][fuel] = 0;
      return 0;
    } 
  
    int n = ls.length;
    // 计算油量为 fuel，从位置 u 到 end 的路径数量
    // 由于每个点都可以经过多次，如果 u = end，那么本身就算一条路径 ---- 非常无语，但testcase就是这么设置的
    int sum = u == end ? 1 : 0;
    for (int i = 0; i < n; i++) {
      if (i != u) {
        need = Math.abs(ls[i] - ls[u]);
        if (fuel >= need) {
          sum += dfs(ls, i, end, fuel - need);
          sum %= mod; // 此处就开始mod是防止此时sum就overflow
        }
      }
    }
    cache[u][fuel] = sum;
    return sum;
  }
  ```

  *Time Complexity:* 最坏情况下共有 $n\times fuel$ 个状态需要计算（填满整个 $cache$ 数组）。每计算一个状态需要遍历一次 $locations$ 数组，复杂度为 $\mathcal{O}(n)$ $\implies$ 整体复杂度为 $\mathcal{O}(n^2\times fuel)$

  *Space Complexity:* $\mathcal{O}(n^2\times fuel)$​

+ Solution 2: ==**将「记忆化搜索」改成「动态规划」**==

  我们重点关注下我们的 DFS 方法签名设计：

  ```java
  int dfs(int[] ls, int u, int end, int fuel) {}
  ```

  其中，参数 `ls` 和参数 `end` 分别代表源输入的 `locations` 和 `finish`，在整个 DFS 过程都不会变化，属于不变参数。而参数 `u` 和参数 `fuel` 则是代表了 DFS 过程中的当前位置和当前油量，属于变化参数。

  因此我们可以定一个二维数组 $f[][]$，来分别表示两个可变参数。第一维代表当前位置（对应 `locations` 数组的下标），第二维代表当前剩余油量。二维数组中存储的就是我们的 DFS 方法的返回值（从 `u` 到 `end` 在油量为 `fuel` 的情况下的路径数量）。

  同时结合题意，不难得知维度的取值范围：

  - 第一维的取值范围为 $[0,locations.length)$
  - 第二维的取值范围为 $[0,fuel]$

  **做完这一步的”翻译“工作，我们就得到了「动态规划」的「状态定义」:** $f[i][j]$ 代表从位置 $i$ 出发，当前剩余油量为 $j$ 的前提下，到达目的地的路径数量。**不知道你是否发现，这个「状态定义」和我们「记忆化搜索」中的缓存器的定义是一致的。**

  接下来我们要从 DFS 中”翻译“出「状态转移方程」。

  所谓的「状态转移方程」其实就是指如何从一个状态转移到另外一个状态。而我们的 DFS 主逻辑就是完成这个转移的：**DFS 中的主逻辑很简单：枚举所有的位置，看从当前位置 出发，可以到达的位置有哪些。**

  **于是我们很容易就可以得出状态转移方程：**
  $$
  f[i][fuel]=\sum_{k=0,k\neq i}^{n-1}{f[k][fuel-need]}
  $$
  其中，$k$ 代表当在位置 $i$ 且油量为 $fuel$ 的状态时枚举的「下一位置」，$need$ 代表从 $i$ 到达 $k$ 需要的油量。

  对应的代码为

  ```java
  // 此时我们要求从位置i到位置end在油量为fuel的情况下的路径数，初始定为0
  f[i][fuel] = 0;
  // 枚举所有的城市，取得它们到位置end在油量为fuel-need的情况下的路径数，累加即为f[i][fuel]
  for (int k = 0; k < n; k++) {
    if (k != i) {
      int need = Math.abs(ls[i] - ls[k]);
      if (fuel >= need) f[i][fuel] += f[k][fuel - need];
    }
  }
  ```

  从状态转移方程可以发现，在计算 $f[i][fuel]$ 的时候依赖于 $f[k][fuel-need]$（即 $f[k][fuel-need]$ 的值已经得到）。虽然 $i$ 和 $k$ 并无严格的大小关系，但是 $fuel$ 和 $fuel-need$ 具有严格的大小关系（$fuel \ge fuel-need$​）。因此我们需要先从小到大枚举油量这一维。==**外层循环fuel（从小到大），内层循环城市（无所谓顺序），内外层顺序不可变。**==

  ```java
  int mod = 1000000007;
  public int countRoutes(int[] ls, int start, int end, int fuel) {
    int n = ls.length;
  
    // f[i][j] 代表从位置 i 出发，当前油量为 j 时，到达目的地的路径数
    int[][] f = new int[n][fuel + 1];
  
    // 对于本身位置就在目的地的状态，路径数为 1
    for (int i = 0; i <= fuel; i++) f[end][i] = 1;
  
    // 从状态转移方程可以发现 f[i][fuel]=f[i][fuel]+f[k][fuel-need]
    // 在计算 f[i][fuel] 的时候依赖于 f[k][fuel-need]
    // 其中 i 和 k 并无严格的大小关系
    // 而 fuel 和 fuel-need 具有严格大小关系：fuel >= fuel-need
    // 因此外层循环fuel（从小到大），内层循环城市（无所谓顺序），内外层顺序不可变
    for (int cur = 0; cur <= fuel; cur++) {
      for (int i = 0; i < n; i++) {
        for (int k = 0; k < n; k++) {
          if (i != k) {
            int need = Math.abs(ls[i] - ls[k]);
            if (cur >= need) {
              f[i][cur] += f[k][cur-need];
              f[i][cur] %= mod;
            }
          }
        }
      }
    }
    return f[start][fuel];
  }
  ```

  *Time Complexity:* 最坏情况下共有 $n\times fuel$ 个状态需要计算（填满整个 $cache$ 数组）。每计算一个状态需要遍历一次 $locations$ 数组，复杂度为 $\mathcal{O}(n)$ $\implies$ 整体复杂度为 $\mathcal{O}(n^2\times fuel)$

  *Space Complexity:* $\mathcal{O}(n^2\times fuel)$​​

*******

###### [0576] Out of Boundary Paths

> There is an `m x n` grid with a ball. The ball is initially at the position `[startRow, startColumn]`. You are allowed to move the ball to one of the four adjacent cells in the grid (possibly out of the grid crossing the grid boundary). You can apply **at most** `maxMove` moves to the ball.
>
> Given the five integers `m`, `n`, `maxMove`, `startRow`, `startColumn`, return the number of paths to move the ball out of the grid boundary. Since the answer can be very large, return it **modulo** `10^9 + 7`.

```java
Input: m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
Output: 12
```

<img src="/Users/shanyonghao/IdeaProjects/LeetCodeProblems/Notes_img/[0576].png" style="zoom:75%;" />

*分析：*已知有两种「动态规划」求解方法，直接猜「状态定义」或者通过「记忆化搜索」改写。因为这题不是DP路径问题类型一，不好直接猜。因此，先尝试Memoization（并不是完整地写出来，而是考虑用到的DFS函数如何设计）。

单纯的Recursion解法为: $\mathcal{O}(4^N)\ \&\ \mathcal{O}(N)$​​

```java
public int findPaths(int m, int n, int N, int x, int y) {
  if (x == m || y == n || x < 0 || y < 0) return 1;
  if (N == 0) return 0;
  return findPaths(m, n, N - 1, x - 1, y)
    + findPaths(m, n, N - 1, x + 1, y)
    + findPaths(m, n, N - 1, x, y - 1)
    + findPaths(m, n, N - 1, x, y + 1);
}
```

结合题目，Memoization的DFS函数可以设计为：

```java
int dfs(int m, int n, int N, int x, int y) {}
```

其中，`m` 和 `n` 是题目原输入，用来表示矩阵是几行几列的，属于「不变参数」；`(x, y)` 代表当前所在的位置； `N` 代表最多的移动次数；返回值代表从起始位置 `(x, y)` 在最多步数 `k` 的条件下将球移出界的路径数量。

为了方便，将 `(x, y)` 根据以下规则映射为 `index` :

```java
index = x * n + y;
(x, y) = (index / n, index % n);
```

根据我们的「技巧解法」，我们可以设计一个二维数组 $f[][]$ 作为我们的 dp 数组：

- 第一维代表 DFS 可变参数中的 $(x, y)$​​ 所对应的 $index$​。取值范围为 $[0, m*n)$​

  ==$\implies f[][]$​ 中每一横行对应原grid中的一格==

- 第二维代表 DFS 可变参数中的 $k$​​。取值范围为 $[0,N]$​

  ==$\implies f[][]$ 中每一竖列表示的是步数，步数可能为0，且为了方便表示，从 $step =0$ 开始==

**dp 数组中存储的就是我们 DFS 的返回值：路径数量。**

根据 dp 数组中的维度设计和存储目标值，我们可以得知「状态定义」为：==$f[i][j]$ 代表从位置 $i$ 出发，可用步数不超过 $k$ 时将球移出界的路径数量==。

至此，我们没有实现「记忆化搜索」，只是设计了 DFS 函数的签名，就已经得出我们的「状态定义」了，接下来需要考虑「转移方程」。

当有了「状态定义」之后，我们需要从「最后一步」来推导出「转移方程」：

由于题目允许往四个方向进行移动。因此我们的最后一步也要统计四个相邻的方向。假设我们当前位置为 $(x,y)$，而 $(x,y)$ 四个方向的相邻格子均不超出矩形。即有：

 $(x, y)$ **出发的路径数量** = **上方** $(x-1, y)$ **的路径数量** + **下方** $(x+1,y)$ **的路径数量** + **左方** $(x,y-1)$ **的路径数量** + **右方** $(x,y+1)$ **的路径数量**

由此可得我们的状态转移方程：
$$
f[(x,y)][step]=f[(x-1,y)][step-1]+f[(x+1,y)][step-1]+f[(x,y-1)][step-1]+f[(x,y+1)][step-1]
$$
PS. 转移方程中 dp 数组的第一维存储的是 $(x,y)$ 对应的 $index$。

从转移方程中我们发现，更新 $f[i][j]$ 依赖于 $f[][j-1]$，因此我们转移过程中需要将最大移动步数进行**从小到大**枚举。

至此，我们已经完成求解「路径规划」问题的两大步骤：「状态定义」&「转移方程」。

但这还不是所有，我们还需要一些**「有效值」**来滚动下去（DP数组的初始化方式）。因为观察我们的「转移方程」可以发现，整个转移过程是一个累加过程，如果没有一些有效的状态（非零值）进行初始化的话，整个递推过程并没有意义。

那么哪些值可以作为成为初始化状态呢？

显然，当我们已经位于矩阵边缘的时候，我们可以一步跨出矩阵，这算作一条路径。同时，由于我们能够往四个方向进行移动，因此不同的边缘格子会有不同数量的路径。==对于非边缘的格子，一步是出不了界的。而步数多于1步时，则可以利用相邻的边缘格子的值来获得路径数量。所以并不需要初始化。==

<img src="/Users/shanyonghao/IdeaProjects/LeetCodeProblems/Notes_img/[0576]_2.png" style="zoom:25%;" />

换句话说，我们需要先对边缘格子进行初始化操作，预处理每个边缘格子直接走出矩阵的路径数量。目的是为了我们整个 DP 过程可以有效的递推下去。

*e.g.* $1\times 3$ grid, maxMove = 3 所对应的初始化后的 $f[][]$​ 为：
$$
\begin{gathered}
\small\color{blue}
\begin{matrix}
\ \ \ 0\ &1\ &2\ &3\ &\quad\quad0\ &1\ &2\ &3
\end{matrix} \\
\begin{matrix}
\small\color{blue}0 \\
\small\color{blue}1 \\ 
\small\color{blue}2 \\
\end{matrix}
\begin{bmatrix}
[0 & 3 & 3 & 3] \\
[0 & 2 & 2 & 2] \\
[0 & 3 & 3 & 3] \\
\end{bmatrix}
\ \ 
\begin{bmatrix}
[0 & 3 & 0 & 0] \\
[0 & 2 & 0 & 0] \\
[0 & 3 & 0 & 0] \\
\end{bmatrix} \\ 
\quad\quad\quad\quad\quad\color{red}{\sqrt{}}\quad\quad\quad\quad\quad\quad\quad \times
\end{gathered}
$$
对于 $f[][]$​​ 的第一行，其对应的值为grid的第一格 $(0,0) \rightarrow index=0\times 3+0=0$​ 在maxMove分别为 $0,1,2,3$​ 时的初始值。对于 $f[][]$​ 的第二行，其对应的值为grid的第二格 $(0,1)\rightarrow index=0\times3+1=1$ 在maxMove分别为 $0,1,2,3$ 时的初始值。对于 $f[][]$ 的第三行，其对应的值为grid的第三格 $(0,2)\rightarrow index=0\times3+2=2$ 在maxMove分别为 $0,1,2,3$​​ 时的初始值。

不可以只初始化 $maxMove=1$ 的情况（即上面右边）。==对于 $maxMove > 1$ 的元素也要初始化的原因是，题目要求的是移动步数不超过 $maxMove$，而不是一定要移动完 $maxMove$。==

```java
int mod = (int)1e9+7;
int n, N;
public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
  this.n = n;
  this.N = maxMove;
  
  // f[i][j]代表从index = i(对应的grid坐标为(x, y) = (index / n, index % n))的起始位置在步数不超过j的条件下，移出界的路径数量
  int[][] f = new int[m * n][N + 1]; 
  
  // 初始化边缘格子在maxMove=[1,N]的条件下移出界的路径数量
  for (int x = 0; x < m; x++) {
    for (int y = 0; y < n; y++) {
      // 位于grid边缘的格子满足以下四个特征:
      if (x == 0) add(x, y, f);
      if (x == m - 1) add(x, y, f);
      if (y == 0) add(x, y, f);
      if (y == n - 1) add(x, y, f);
    }
  }
  
  // 定义可移动的四个方向
  int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
  
  // 从小到大枚举「可移动步数」
  for (int step = 2; step <= N; step++) {
    // 枚举所有格子
    for (int idx = 0; idx < m * n; idx++) {
      int x = parseIndex(idx)[0], y = parseIndex(idx)[1];
      for (int[] dir : dirs) {
        int newX = x + dir[0], newY = y + dir[1];
        // 如果位置有「相邻格子」，则「相邻格子」参与状态转移
        if (newX >= 0 && newX <= m - 1 && newY >= 0 && newY <= n - 1) {
          f[idx][step] += f[getIndex(newX, newY)][step - 1];
          f[idx][step] %= mod;
        }
      }
    }
  }
  
  // 最终结果为从起始点触发，最大移动步数不超 N 的路径数量
  return f[getIndex(startRow, startColumn)][N];
}

// 为每个边缘格子添加一条路径(初始值+1)
void add(int x, int y, int[][] f) {
  int idx = getIndex(x, y);
  for (int step = 1; step <= N; step++) f[idx][step]++;
}

// 将(x, y)转换为index
int getIndex(int x, int y) {
  return x * n + y;
}

// 将index解析回(x, y)
int[] parseIndex(int idx) {
  return new int[]{idx / n, idx % n};
}
```

*Time Complexity:* $\mathcal{O}(m * n * maxMove)$​

*Space Complexity:* $\mathcal{O}(m * n * maxMove)$

*******

#### 背包问题

##### 01背包

###### [0416] Partition Equal Subset Sum

> Given a **non-empty** array `nums` containing **only positive integers**, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

*e.g.*

```java
Input: nums = [1, 5, 11, 5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].
```

*思路：*

**通常「背包问题」相关的题，都是在考察「建模」能力，也就是将问题转换为「背包问题」的能力**。

由于本题是问能否将一个数组分成两个「等和」子集，问题等效于**「能否从数组中挑选若干个元素，使得元素总和等于所有元素总和的一半」**。

这道题如果抽象成「背包问题」的话，应该是：**背包容量为 $target = sum/2$，每个数组元素的「价值」与「成本」相同（都是其数值大小），求能否刚好装满背包。**

*转换为 「01背包」 问题：*

由于每个数字（数组元素）只能被选一次，而且每个数字选择与否都会影响「成本」和「价值」，求解的问题也与「最大价值」相关。因此，可以使用「01 背包」的模型来做。

当确定一个问题可以转化为「01 背包」之后，就可以直接套用「01 背包」的状态定义进行求解了。

**注意：**<u>积累 DP 模型的意义，就是在于可以快速得到可靠的「状态定义」。</u>通用的 DP 技巧解法是基于完全没见过那样的题型才会选用的，而对于一些见过题型的 DP 题目，应该直接套用（或微调）该模型「状态定义」来做。

因此，直接套用「01 背包」的状态定义：$f[i][j]$ 代表当仅考虑前 $i$ 个数值，且选择的数字的总和不可超过 $j$ 时，所能取到的最大价值（最大和）。

当有了「状态定义」之后，结合最后一步，每个数字都有「选」和「不选」两种选择。因此，不难得出状态转移方程：
$$
f[i][j] = max(f[i-1][j],f[i-1][j-nums[i]]+nums[i])
$$
*解释：*
$$
\because f[i-1][j-nums[i]]<j-nums[i] \\
\therefore f[i-1][j-nums[i]]+nums[i] < j-nums[i]+nums[i]=j
$$

+ $dp[N][C+1]$ 解法：

  ```java
  public boolean canPartition(int[] nums) {
    int n = nums.length;
    
    // 「等和子集」的和必然是总和的一半
  	int sum = 0;
    for (int num : nums) sum += num;
    // 总和为奇数，则必然不能被分成两个「等和子集」
    if (sum % 2 != 0) return false;
    int target = sum / 2;
    
    int[][] f = new int[n][target+1];
    // 初始化：先处理「考虑第一件物品」的情况（即dp数组的第一横行的初始值）
    for (int c = 0; c <= target; c++) f[0][c] = c >= nums[0] ? nums[0] : 0;
    // 状态转移：再处理「考虑其他物品」的情况
    for (int i = 0; i < n; i++) {
      for (int c = 0; c <= target; c++) {
        // 不选：
        int ns = f[i-1][c];
        // 选：
        int s = c >= nums[i] ? f[i-1][c-nums[i]] + nums[i] : 0;
        f[i][c] = Math.max(ns, s);
      }
    }
    
    return f[n-1][target] == target;
  }
  ```

  + *Time Complexity:* $\mathcal{O}(n*target)$
  + *Space Complexity:* $\mathcal{O}(n*target)$

+ 「滚动数组」：

  ```java
  public boolean canPartition(int[] nums) {
    int n = nums.length;
    
    // 「等和子集」的和必然是总和的一半
  	int sum = 0;
    for (int num : nums) sum += num;
    // 总和为奇数，则必然不能被分成两个「等和子集」
    if (sum % 2 != 0) return false;
    int target = sum / 2;
    
    int[][] f = new int[2][target+1];
    // 初始化：先处理「考虑第一件物品」的情况（即dp数组的第一横行的初始值）
    for (int c = 0; c <= target; c++) f[0][c] = c >= nums[0] ? nums[0] : 0;
    // 状态转移：再处理「考虑其他物品」的情况
    for (int i = 1; i < n; i++) {
      for (int c = 0; c <= target; c++) {
        // 不选：
        int ns = f[(i-1)&1][c];
        // 选：
        int s = c >= nums[i] ? f[(i-1)&1][c-nums[i]] + nums[i] : 0;
        f[i&1][c] = Math.max(ns, s);
      }
    }
    
    return f[(n-1)&1][target] == target;
  }
  ```

  + *Time Complexity:* $\mathcal{O}(n*target)$
  + *Space Complexity:* $\mathcal{O}(target)$

+ 「一维空间优化」：

  ```java
  public boolean canPartition(int[] nums) {
    int n = nums.length;
    
    // 「等和子集」的和必然是总和的一半
  	int sum = 0;
    for (int num : nums) sum += num;
    // 总和为奇数，则必然不能被分成两个「等和子集」
    if (sum % 2 != 0) return false;
    int target = sum / 2;
    
    int[] f = new int[target+1];
    // 初始化：先处理「考虑第一件物品」的情况（即dp数组的第一横行的初始值）
    for (int c = 0; c <= target; c++) f[c] = c >= nums[0] ? nums[0] : 0;
    // 状态转移：再处理「考虑其他物品」的情况
    for (int i = 1; i < n; i++) {
      for (int c = target; c >= 0; c--) {
        // 不选：
        int ns = f[c];
        // 选：
        int s = c > nums[i] ? f[c-nums[i]] + nums[i] : 0;
        f[c] = Math.max(ns, s);
      }
    }
    
    return f[target] == target;
  }
  ```

  + *Time Complexity:* $\mathcal{O}(n*target)$
  + *Space Complexity:* $\mathcal{O}(target)$​

上述的状态定义求得的是「能凑出来的最大价值」，而题目直观要求的其实是「能凑出来的最大价值是否刚好为 $target$」。

因此，可以将「状态定义」进行修改，使其直接与所需答案相关联：$f[i][j]$ 代表考虑前 $i$ 个数值，选择的数字的总和是否刚好等于 $j$，即数组中存储的是「布尔类型」的动规值。

相应的「状态转移方程」调整为：（两部分分别对应「选」第 $i$ 件物品和「不选」第 $i$ 件物品）
$$
f[i][j] = f[i-1][j] \or f[i-1][j-nums[i]]
$$
**当某个模型的「状态定义」进行了修改之后，除了考虑调整「转移方程」以外，还需要考虑修改「初始化」状态。**

试考虑，创建的 $dp$ 数组存储的是布尔类型，初始值都是 $false$，这意味着无论怎么转移下去，都不可能产生一个 $true$，最终所有的状态都仍然是 $false$。换句话说，还需要一个有效值 来帮助整个过程能递推下去。

**通常使用「首行」来初始化「有效值」：**对于本题，显然可以通过「先处理第一个物品」来得到「有效值」，即令 $f[0][nums[0]] = true$，代表当仅考虑第 $1$ 件物品时，只有容量为 $nums[0]$ 的背包才符合「恰好」的要求，故置为 $true$。

但因为无法确保背包的容量一定大于 $nums[0]$ （也就是第一个物品过大，永远无法装入背包的情况），因此增加一个**「不考虑任何物品」**的情况讨论，也就是**将「物品编号」从 0 开始调整为从 1 开始**。

原本 $f[0][]$ 代表只考虑第一件物品、$f[1][]$ 代表考虑第一件和第二件物品；调整后 $f[0][]$ 代表不考虑任何物品、$f[1][]$​ 代表只考虑第一件物品......这种技巧本质上还是利用了「哨兵」的思想。

+ $dp[N][C+1]$​ 解法

  ```java
  public boolean canPartition(int[] nums) {
    int n = nums.length;
  
    // 「等和子集」的和必然是总和的一半
    int sum = 0;
    for (int num : nums) sum += num;
    // 总和为奇数，则必然不能被分成两个「等和子集」
    if (sum % 2 != 0) return false;
    int target = sum / 2;
  
    boolean[][] f = new boolean[n+1][target+1];
    // 初始化：先处理「不考虑任何物品」的情况（即dp数组的第一横行的初始值）
    f[0][0] = true;
    // 状态转移：再处理「考虑其他物品」的情况
    for (int i = 1; i < n; i++) {
      for (int c = 0; c <= target; c++) {
        // 不选：
        boolean ns = f[i-1][c];
        // 选：
        boolean s = c >= nums[i] ? f[i-1][c-nums[i]] : false;
        f[i][c] = ns | s;
      }
    }
  
    return f[n-1][target];
  }
  ```

  + *Time Complexity:* $\mathcal{n*target}$
  + *Space Complexity:* $\mathcal{O}(n*target)$

+ 「滚动数组」

  ```java
  public boolean canPartition(int[] nums) {
    int n = nums.length;
  
    // 「等和子集」的和必然是总和的一半
    int sum = 0;
    for (int num : nums) sum += num;
    // 总和为奇数，则必然不能被分成两个「等和子集」
    if (sum % 2 != 0) return false;
    int target = sum / 2;
  
    boolean[][] f = new boolean[2][target+1];
    // 初始化：先处理「不考虑任何物品」的情况（即dp数组的第一横行的初始值）
    f[0][0] = true;
    // 状态转移：再处理「考虑其他物品」的情况
    for (int i = 1; i < n; i++) {
      for (int c = 0; c <= target; c++) {
        // 不选：
        boolean ns = f[(i-1)&1][c];
        // 选：
        boolean s = c >= nums[i] ? f[(i-1)&1][c-nums[i]] : false;
        f[i&1][c] = ns | s;
      }
    }
  
    return f[(n-1)&1][target];
  }
  ```

  + *Time Complexity:* $\mathcal{n*target}$
  + *Space Complexity:* $\mathcal{O}(target)$

+ 「一维空间优化」

  ```java
  public boolean canPartition(int[] nums) {
    int n = nums.length;
  
    // 「等和子集」的和必然是总和的一半
    int sum = 0;
    for (int num : nums) sum += num;
    // 总和为奇数，则必然不能被分成两个「等和子集」
    if (sum % 2 != 0) return false;
    int target = sum / 2;
  
    boolean[] f = new boolean[target+1];
    // 初始化：先处理「不考虑任何物品」的情况（即dp数组的第一横行的初始值）
    f[0] = true;
    // 状态转移：再处理「考虑其他物品」的情况
    for (int i = 1; i < n; i++) {
      for (int c = target; c >= 0; c--) {
        // 不选：
        boolean ns = f[c];
        // 选：
        boolean s = c >= nums[i] ? f[c-nums[i]] : false;
        f[c] = ns | s;
      }
    }
  
    return f[target];
  }
  ```

  + *Time Complexity:* $\mathcal{n*target}$
  + *Space Complexity:* $\mathcal{O}(target)$​

通过将一个背包问题的「状态定义」从**「最多不超过 XX 容量」**修改为**「背包容量恰好为 XX」**，同时再把「有效值构造」出来，也即是将**「物品下标调整为从 1 开始，设置 为初始值」**。这其实是另外一类「背包问题」，它不对应「价值最大化」，对应的是「能否取得最大/特定价值」。这样的「背包问题」同样具有普遍性。

*******

##### 完全背包

###### [0279] Perfect Squares

> Given an integer `n`, return *the least number of perfect square numbers that sum to* `n`.
>
> A **perfect square** is an integer that is the square of an integer; in other words, it is the product of some integer with itself. For example, `1`, `4`, `9`, and `16` are perfect squares while `3` and `11` are not.

*思路：* 由于题目并没有限制相同的「完全平方数」只能使用一次，所以问题可以转换为：**给定了若干数字，每个数字可以被使用无限次，求凑出目标值 $n$ 所需要用到的最少数字个数是多少**。

显示符合「完全背包」模型，将「状态定义」调整为：**$f[i][c]$ 为考虑前 $i$ 个数字，凑出数字总和 $c$ 所需要用到的最少数字数量**。

不失一般性的分析 ，对于第 $i$ 个数字（假设数值为 $t$），有如下选择：

- 选 0 个数字 $i$，此时有 $f[i][c]=f[i-1][c]$

- 选 1 个数字 $i$，此时有 $f[i][c]=f[i-1][c-t]+1$ 

- 选 2 个数字 $i$，此时有 $f[i][c]=f[i-1][c-2*t]+2$ 

  $\dots$

- 选 k 个数字 $i$，此时有 $f[i][c]=f[i-1][c-k*t]+k$

因此，「状态转移方程」为：
$$
f[i][c]=min(f[i-1][c-k*t]+k),0\le k*t \le c
$$
当然，能够选择 $k$ 个数字 $i$ 的前提是，剩余的数字 $c-k*t$ 也能够被其他「完全平方数」凑出，即 $f[i-1][c-k*t]$ 为有意义的值。<u>*本题只要将无法凑成的情况保留为数组初始的0，就不用担心数组内会有非法值*</u> 

==本题不需要从「不考虑任何完全平方数」开始，而且不需要引入无效值的原因是因为，「第一个完全平方数」一定为1，故从「考虑第一个完全平方数」开始初始化。—— *与[0322]的区别*==

+ $dp[N][C+1]$ 解法

  ```java
  public int numSquares(int n) {
    // 预处理出所有可能用到的「完全平方数」
    int len = (int) Math.sqrt(n); // 转为int是为了向下取整     O(sqrt(n))
    int[] perfectSquares = new int[len];
    for (int i = 0; i < len; i++) perfectSquares[i] = (i + 1) * (i + 1);
  
    // f[i][c] 代表考虑前 i 个物品，凑出 c 所使用到的最小元素个数
    int[][] f = new int[len][n + 1]; 
    
    // 处理「第一个完全平方数」的情况
    for (int c = 0; c <= n; c++) f[0][c] = c;    
    
    // 处理「剩余数」的情况
    for (int i = 1; i < len; i++) { // O(sqrt(n))
      int t = perfectSquares[i]; 
      for (int c = 0; c <= n; c++) { // O(n)
        // 「不选」：
        f[i][c] = f[i-1][c];
        // 「选」：
        for (int k = 1; k * t <= c; k++)  // O(n)
          f[i][c] = Math.min(f[i][c], f[i-1][c- k*t]+k);
      }
    }
    
    return f[len-1][n];
  }
  ```

  + *Time Complexity:* $\mathcal{O}(n^2\sqrt{n})$      *Java `Math.sqrt()` TC为 $\mathcal{O}(logn)(<\mathcal{O}(\sqrt{n}))$，这里取 $\mathcal{O}(\sqrt{n})$*  
  + *Space Complexity:* $\mathcal{O}(n\sqrt{n})$ 

+ 「一维空间优化」

  依旧是利用 $f[i][c]$ 的部分和 $f[i][c-t]$ (假设第 $i$ 个数字为 $t$) 之间的[等差特性](#「完全背包」一维方程推导)（**总是相差1**），将原「状态转移方程」修改为
  $$
  f[i][c] = min(f[i-1][c],f[i][c-t]+1)
  $$
  再进行 $i$ 的维度消除，可得
  $$
  f[c]=min(f[c],f[c-t]+1)
  $$

  ```java
  public int numSquares(int n) {
    // 不预处理出所有可能用到的「完全平方数」，而是之后再利用i枚举
    int len = (int) Math.sqrt(n); // 转为int是为了向下取整     O(sqrt(n))
  
    // f[i][c] 代表考虑前 i 个物品，凑出 c 所使用到的最小元素个数
    int[] f = new int[n + 1]; 
  
    // 处理「第一个完全平方数」的情况
    for (int c = 0; c <= n; c++) f[c] = c;    
    
    // 处理「剩余数」的情况
    for (int i = 1; i < len; i++) { // O(sqrt(n))
      int t = (i+1)*(i+1);
      // 「选」的前提是c>=t，而「不选」时不需要更改f数组，故c从t开始  O(n)
      for (int c = t; c <= n; c++) 
        f[c] = Math.min(f[c], f[c-t]+1);
    }
  
    return f[n];
  }
  ```
  
  + *Time Complexity:* $\mathcal{O}(n\sqrt{n})$      *Java `Math.sqrt()` TC为 $\mathcal{O}(logn)(<\mathcal{O}(\sqrt{n}))$，这里取 $\mathcal{O}(\sqrt{n})$*  
  
  + *Space Complexity:* $\mathcal{O}(n)$
+ 「一维空间优化」+ 状态转移时内外层维度交换

  ```java
  public int numSquares(int n) {
    int[] dp = new int[n+1];
  
    for (int c = 1; c < n+1; c++) {      // O(n)
      int iLen = (int) Math.sqrt(c);
      int min = n; // 每计算一个新的c的dp，要重新置min，不然min还是上一个数的min，导致dp[]更新不了
      for (int i = 0; i < iLen; i++) {   // O(sqrt(n))
        int t = (i+1)*(i+1);
        min = Math.min(min, dp[c-t]);
      }
      dp[c] = min+1;
    }
  
    return dp[n];
  }
  ```
  
  + *Time Complexity:* $\mathcal{O}(n\sqrt{n})$      *Java `Math.sqrt()` TC为 $\mathcal{O}(logn)(<\mathcal{O}(\sqrt{n}))$，这里取 $\mathcal{O}(\sqrt{n})$*  
  + *Space Complexity:* $\mathcal{O}(n)$

==**比较：**== 均以 $n=12$ 和「二维」的角度进行分析 <u>*（橙色的数字表示未更新，为数字初始值）*</u>

+ 常规的「一维空间优化」解法：

  按「横行」填充dp数组，整个过程如下：
  $$
  \begin{gathered}
  \small\color{blue}
  \begin{matrix}
  \quad\quad\quad\ 0\ &1\ &2\ &3\ &4\ &5\ &6\ &7\ &8\ &9\ &10\ &11\ &12\
  \end{matrix}\\
  \begin{matrix}
  \small\color{blue}\rightarrow0\color{red}(1) \\ 
  \small\color{blue}\rightarrow1\color{red}(4) \\
  \small\color{blue}\rightarrow2\color{red}(9) \\
  \end{matrix}
  \begin{bmatrix}
  [0&1&2&3&4&5&6&7&8&9&10&11&12] \\
  [\color{orange}0&\color{orange}0&\color{orange}0&\color{orange}0&1&2&3&4&2&3&4&5&3] \\
  [\color{orange}0&\color{orange}0&\color{orange}0&\color{orange}0&\color{orange}0&\color{orange}0&\color{orange}0&\color{orange}0&\color{orange}0&1&2&3&3] \\
  \end{bmatrix} \\
  \implies dp=[0,1,2,3,1,2,3,4,2,1,2,3,3]
  \end{gathered}
  $$

+ 内外维度交换的「一维空间优化」解法：

  按「竖列」填充dp数组，整个过程如下：
  $$
  \begin{gathered}
  \small\color{blue}
  \begin{matrix}
  \quad\downarrow&\downarrow&\downarrow&\downarrow&\downarrow&\downarrow&\downarrow&\downarrow&\downarrow&\downarrow&\downarrow&\downarrow&\downarrow\\
  \quad\ \ 0\ &1\ &2\ &3\ &4\ &5\ &6\ &7\ &8\ &9\ &10\ &11\ &12
  \end{matrix}\\
  \begin{matrix}
  \small\color{blue}0\color{red}(1) \\ 
  \small\color{blue}1\color{red}(4) \\
  \small\color{blue}2\color{red}(9) \\
  \end{matrix}
  \begin{bmatrix}
  [\color{orange}0&1&2&3&4&5&6&7&8&9&10&11&12] \\
  [\color{orange}0&\color{orange}0&\color{orange}0&\color{orange}0&1&2&3&4&2&3&4&5&3] \\
  [\color{orange}0&\color{orange}0&\color{orange}0&\color{orange}0&\color{orange}0&\color{orange}0&\color{orange}0&\color{orange}0&\color{orange}0&1&2&3&3] \\
  \end{bmatrix} \\
  \implies dp=[0,1,2,3,1,2,3,4,2,1,2,3,3]
  \end{gathered}
  $$

==**非DP的做法：**==

有一点可以肯定的是，能凑出 $n$ 的完全平方数的个数 $count$ 一定小于等于 $n$（至少可以保证由 $n$ 个 $1$ 凑出），且对于 $n$，需要考虑的完全平方数的范围为 $[1,\lfloor \sqrt{n} \rfloor]$。故从 $1$ 开始考虑 $count$，找到使 $is\_divided\_by(n,count)$ 为 $true$ 的最小的 $count$。 
$$
\begin{aligned}
numSquares(n) &=
\begin{equation}
\mathop{\arg\min}_{count\in[1,2,\dots,n]}(is\_divided\_by(n,count))
\end{equation} \\
is\_divided\_by(n,count) &= is\_divided\_by(n-k,count-1)\ \exists\ k\in [1,\lfloor \sqrt{n} \rfloor]
\end{aligned}
$$
*e.g.* 以 $n = 12$为例，展示 $is\_divided\_by(n,count)$ 的推导过程：
$$
\begin{aligned}
\bold{count =1:}&\\
&
\begin{gathered}
(12,1)\\\small\color{red}{false} 
\end{gathered}\\ \\
\bold{count = 2:}&\\
&
\begin{gathered}
(12,2)\\
\swarrow\quad\downarrow\quad\searrow\\
(12-1,1)(12-4,1)(12-9,1)\\
\small\color{red}{false}\quad\quad\quad\small\color{red}{false}\quad\quad\quad\small\color{red}{false}
\end{gathered}\\ \\
\bold{count = 3:}&\\
&
\begin{gathered}
(12,3)\\
\swarrow\quad\quad\quad\quad\quad\quad\quad\quad\quad\quad
\downarrow\quad\quad\quad\quad\quad\quad\quad\quad\quad\quad
\searrow\\
(12-1,2)\quad\quad\quad\quad\quad\quad\quad\quad\quad
(12-4,2)\quad\quad\quad\quad\quad\quad\quad\quad\quad
(12-9,2)\\
\swarrow\quad\quad\downarrow\quad\quad\searrow \quad\quad\quad\quad\quad\quad\quad
\swarrow\quad\quad\downarrow\quad\quad\searrow \quad\quad\quad\quad\quad\quad\quad
\swarrow\quad\quad\downarrow\quad\quad\searrow\\
(11-1,1)(11-4,1)(11-9,1)\quad
(8-1,1)(8-4,1)\xcancel{(8-9,1)}\quad
\xcancel{(3-1,1)}\xcancel{(3-4,1)}\xcancel{(3-9,1)}\\
\small\color{red}{false}\quad\quad\quad
\small\color{red}{false}\quad\quad\quad
\small\color{red}{false}
\quad\quad\quad\quad
\small\color{red}{false}\quad\quad\quad
\small\color{red}{true}\quad\quad\quad
\quad\quad\quad\quad\quad\quad\quad\quad\quad\quad\quad\quad\quad\quad\quad\quad\quad\quad\quad
\end{gathered}
\end{aligned}
$$
 由上述推导过程可知：当 $count$ 枚举到 $3$ ，$(8-4,1)=true$ 时，即找到答案，不必再向后继续。

```java
private Set<Integer> perfectSquares = new HashSet<>(); 

public int numSquares(int n) {
  this.perfectSquares.clear();

  for (int i = 1; i * i <= n; i++) perfectSquares.add(i * i);

  int count = 1;
  for (; count <= n; count++) {
    if (is_divided_by(n, count)) return count;
  }

  return count;
}

public boolean is_divided_by(int n, int count) {
  if (count == 1) return perfectSquares.contains(n);

  for (Integer i : perfectSquares) {
    if (is_divided_by(n-i, count-1)) return true;
  }
  return false;
}
```

==总结：== 这种方法其实是在一个高度不断加深（$count$ 不断增加）的 $m$ - ary tree $(m=\lfloor \sqrt{n} \rfloor)$ 上进行 **DFS**，*i.e.* **Iterative Deepening DFS on a complete m-ary tree**.

+ *Time Complexity:* $\mathcal{O}(\frac{\sqrt{n}^{h+1}-1}{\sqrt{n}-1})=\mathcal{O}(n^{\frac{h}{2}})$ where `h` is the maximal number of recursion that could happen. As one might notice, the above formula actually resembles the formula to calculate the number of nodes in a complete $m$-ary tree. Indeed, the trace of recursive calls in the algorithm form a $m$-ary tree, where $m$ is the number of squares in `square_nums`, *i.e.* $\sqrt{n}$. In the worst case, we might have to traverse the entire tree to find the solution.
+ *Space Complexity:* $\mathcal{O}(\sqrt{n})$. We keep a list of `square_nums`, which is of $\sqrt{n}$ size. In addition, we would need additional space for the recursive call stack. But as we will learn later (==Lagrange's four-square theorem==), the size of the call track would not exceed 4. 

==**数学做法：**==

+ 依据

  + Lagrange's four-square theorem

    also known as Bachet's conjecture, which states that every natural number can be represented as the sum of four integer squares: $p=a_0^2+a_1^2+a_2^2+a_3^2$  where the four numbers $a_0,a_1,a_2,a_3$ are integers.

  + Adrien-Marie Legendre's three-square theorem

    a positive integer can be expressed as the sum of three squares: $n \neq 4^k(8m+7) \iff n = a_0^2+a_1^2+a_2^2$ where $k$ and $m$ are integers.

+ 步骤：

  + First, we check if the number `n` is of the form $n = 4^{k}(8m+7)$, if so we return 4 directly.

    *解释：* 如果 $n$ 可以写成 $4^k(8m+7)$ 的形式，那么根据依据二，$n$ 一定不可分解为3个完全平方数相加。假如此时 $n$ 为2个完全平方数相加，即 $n=a_0^2+a_1^2$，那么 $n=a_0^2+a_1^2+0^2$ 也成立，而这与依据二相矛盾。故此时 $n$ 一定不可分解为2个完全平方数相加。同理，此时 $n$ 本身也不是完全平方数。结合依据一，此时的 $n$ 只可能是由4个完全平方数相加构成的。

  + Otherwise, we further check if the number is of a square number itself or the number can be decomposed the sum of two squares.

  + In the bottom case, the number can be decomposed into the sum of 3 squares, though we can also consider it decomposable by 4 squares by adding zero according to the four-square theorem. But we are asked to find the least number of squares.

```java
protected boolean isSquare(int n) {
  int sq = (int) Math.sqrt(n);
  return n == sq * sq;
}

public int numSquares(int n) {
  // 判断n是否满足4^k(8m+7)的形式
  while (n % 4 == 0)
    n /= 4;
  if (n % 8 == 7)
    return 4;
  // 判断n是否自身即为perfect square
  if (this.isSquare(n))
    return 1;
  // 判断n是否为两个perfect square之和
  for (int i = 1; i * i <= n; ++i) {      // 这一步用到了O(sqrt(n))个iteration
    if (this.isSquare(n - i * i))
      return 2;
  }
  // bottom case of three-square theorem.
  return 3;
}
```

+ *Time Complexity:* $\mathcal{O}(\sqrt{n})$
+ *Space Complexity:* $\mathcal{O}(1)$

******

###### [0322] Coin Change

> You are given an integer array `coins` representing coins of different denominations and an integer `amount` representing a total amount of money.
>
> Return *the fewest number of coins that you need to make up that amount*. If that amount of money cannot be made up by any combination of the coins, return `-1`.
>
> You may assume that you have an infinite number of each kind of coin.

*思路：*与[0279]一模一样，唯一改动在于初始化dp数组。

**当看到题目是给定一些「物品」，让从中进行选择，以达到「最大价值」或者「特定价值」时，应该联想到「背包问题」。**这本质上其实是一个组合问题：被选物品之间不需要满足特定关系，只需要选择物品，以达到「全局最优」或者「特定状态」即可。

再根据物品的「选择次数限制」来判断是何种背包问题。本题每种硬币可以被选择「无限次」，直接套用「完全背包」的状态定义进行微调：

**定义 $f[i][c]$ 为考虑前 $i$ 件物品，凑成总和为 $c$ 所需要的最少硬币数量。**

==为了方便初始化（「第一个硬币」的值不确定，不像[0279]中「第一个完全平方数」一定为1），一般让 $i$ 代表不考虑任何物品的情况 —— *与[0279]的区别*。==因此有显而易见的初始化条件：$f[0][0]=0$，其余 $f[0][x]=INF$ —— 代表当没有任何硬币的时候，存在凑成总和为 0 的方案，方案所使用的硬币为 0；凑成其他总和的方案不存在。

由于要求的是「最少」硬币数量，因此不希望「无效值」参与转移，可设 $INF=INT\_MAX$。

当「状态定义」与「基本初始化」有了之后，不失一般性的考虑 $f[i][c]$ 该如何转移。

对于第 $i$ 个硬币，有两种决策方案：

- 不使用该硬币：$f[i][c]=f[i-1][c]$
- 使用该硬币，由于每种硬币可以被选择多次（容量允许的情况下），因此最优解应当是所有方案中的最小值。即 $f[i][c]=min(f[i-1][c-k*coin]+k)$

+ $dp[N][C+1]$ 解法

  ```java
  int INF = Integer.MAX_VALUE;
  public int coinChange(int[] coins, int amount) {
    int n = coins.length;
    int[][] f = new int[n+1][amount+1];
  
    // 初始化（没有任何硬币的情况）：只有 f[0][0] = 0；其余情况均为无效值。
    // 这是由「状态定义」决定的，当不考虑任何硬币的时候，只能凑出总和为 0 的方案，所使用的硬币数量为 0 
    for (int c = 1; c <= amount; c++) f[0][c] = INF;
  
    // 从「第一枚硬币」开始讨论
    for (int i = 1; i <= n; i++) {
      int t = coins[i-1];
      for (int c = 0; c <= amount; c++) {
        // 「不选」：
        f[i][c] = f[i-1][c];
        // 「选」：
        for (int k = 1; k * t <= c; k++) 
          if (f[i-1][c-k*t] != INF) // 要想省略这个判断，可以将INF设为比INT_MAX小的较大数(e.g. 0x3f3f3f3f)
            f[i][c] = Math.min(f[i][c], f[i-1][c-k*t]+k);
      }
    }
  
    return f[n][amount] != INF ? f[n][amount] : -1;
  }
  ```

  + *Time Complexity:* $\mathcal{O}(n*amount^2)$
  + *Space Complexity:* $\mathcal{O}(n*amount)$

  ```java
  int INF = Integer.MAX_VALUE;
  public int coinChange(int[] coins, int amount) {
    int n = coins.length;
    int[][] f = new int[n+1][amount+1];
  
    // 初始化（没有任何硬币的情况）：只有 f[0][0] = 0；其余情况均为无效值。
    // 这是由「状态定义」决定的，当不考虑任何硬币的时候，只能凑出总和为 0 的方案，所使用的硬币数量为 0 
    for (int c = 1; c <= amount; c++) f[0][c] = INF;
  
    // 从「第一枚硬币」开始讨论
    for (int i = 1; i <= n; i++) {
      int t = coins[i-1];
      for (int c = 0; c <= amount; c++) {
        // 「不选」：
        int ns = f[i-1][c];
        // 「选」：因为引入了无效值，所以也要增加f[i][c-t]的无效值判断
        int s = c >= t && f[i][c-t] != INF ? f[i][c-t]+1: INF;
        f[i][c] = Math.min(ns, s);
      }
    }
  
    return f[n][amount] != INF ? f[n][amount] : -1;
  }
  ```

  + *Time Complexity:* $\mathcal{O}(n*amount)$
  + *Space Complexity:* $\mathcal{O}(n*amount)$

+ 「一维空间优化」解法

  依旧是利用 $f[i][c]$ 的部分和 $f[i][c-t]$ (假设第 $i$ 个数字为 $t$) 之间的[等差特性](#「完全背包」一维方程推导)（**总是相差1**），将原「状态转移方程」修改为
  $$
  f[i][c] = min(f[i-1][c],f[i][c-t]+1)
  $$
  再进行 $i$ 的维度消除，可得
  $$
  f[c]=min(f[c],f[c-t]+1)
  $$

  ```java
  int INF = 0x3f3f3f3f;
  public int coinChange(int[] coins, int amount) {
    int n = coins.length;
    int[] f = new int[amount+1];
  
    // 初始化（没有任何硬币的情况）：只有 f[0] = 0；其余情况均为无效值。
    // 这是由「状态定义」决定的，当不考虑任何硬币的时候，只能凑出总和为 0 的方案，所使用的硬币数量为 0 
    for (int c = 1; c <= amount; c++) f[c] = INF;
  
    // 从「第一枚硬币」开始讨论
    for (int i = 1; i <= n; i++) {
      int t = coins[i-1];
      // 「选」的前提是c>=t，而「不选」时不需要更改f数组，故c从t开始
      for (int c = t; c <= amount; c++) {
        f[c] = Math.min(f[c], f[c-t]+1);
      }
    }
  
    return f[amount] != INF ? f[amount] : -1;
  }
  ```

  + *Time Complexity:* $\mathcal{O}(n*amount)$
  + *Space Complexity:* $\mathcal{O}(amount)$

*******

###### [0518] Coin Change 2

> You are given an integer array `coins` representing coins of different denominations and an integer `amount` representing a total amount of money.
>
> Return *the number of combinations that make up that amount*. If that amount of money cannot be made up by any combination of the coins, return `0`.
>
> You may assume that you have an infinite number of each kind of coin.
>
> The answer is **guaranteed** to fit into a signed **32-bit** integer.

*思路：* [0322] 求「取得特定价值所需要的最小物品个数」，本题是「取得特定价值的方案数量」。

将「完全背包」的状态定义搬过来进行“微调”：**定义 $f[i][c]$ 为考虑前 $i$ 件物品，凑成总和为 $c$ 的方案数量。**

为了方便初始化，一般让 $f[0][x]$ 代表不考虑任何物品的情况。因此，有显而易见的初始化条件：$f[0][0]=1$，其余 $f[0][x]=0$ —— 代表当没有任何硬币的时候，存在凑成总和为 0 的方案数量为 1；凑成其他总和的方案不存在。

当「状态定义」与「基本初始化」有了之后，不失一般性的考虑 $f[i][c]$ 该如何转移。

对于第 $i$ 个硬币，有两种决策方案：

- 不使用该硬币：$f[i][c]=f[i-1][c]$

- 使用该硬币：由于每个硬币可以被选择多次（容量允许的情况下），因此方案数量应当是选择「任意个」该硬币的方案总和：$f[i][c]=\sum_{k=1}^{\lfloor c/val\rfloor}f[i-1][c-k*val],\ val=coins[i-1]$

- $dp[N][C+1]$ 解法：

  ```java
  public int change(int amount, int[] coins) {
    int n = coins.length;
    int[][] f = new int[n+1][amount+1];
    f[0][0] = 1;
    for (int i = 1; i <= n; i++) {
      int val = coins[i-1];
      for (int c = 0; c <= amount; c++) {
        // 「不选」：
        f[i][c] = f[i-1][c];
        // 「选」：
        for (int k = 1; k * val <= c; k++)
          f[i][c] += f[i-1][c-k*val];
      }
    }
    return f[n][amount];
  }
  ```

  + *Time Complexity:* $\mathcal{O}(n*amount^2)$
  + *Space Complexity:* $\mathcal{O}(n*amount)$

- 「一维空间优化」解法：

  ```java
  public int change(int amount, int[] coins) {
    int n = coins.length;
    int[] f = new int[amount+1];
    f[0] = 1;
    for (int i = 1; i <= n; i++) {
      int val = coins[i-1];
      for (int c = val; c <= amount; c++) 
        f[c] += f[c-val];
    }
    return f[amount];
  }
  ```

  + *Time Complexity:* $\mathcal{O}(n*amount)$
  + *Space Complexity:* $\mathcal{O}(amount)$

******

******

### Floyd's Algorithm

##### [0142] Linked List Cycle II

> Given a linked list, return the node where the cycle begins. If there is no cycle, return `null`. **Notice** that you **should not modify** the linked list.    

分别引自[[0141]](#[0141] Linked List Cycle)的两个方法

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
        return head;
      }
    }
  
    return false;
  }
  ```

+ Solution 2: Two-Pointer Scenario II / Floyd's Algorithm  $\implies \mathcal{O}(n)\ \&\ \mathcal{O}(1)$    ==**写得好**==

  ```java
  public boolean hasCycle(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;
    
    while (fast != null && fast.next != null) { // Phase I: Find the intersection
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) { // Phase II: Find the entrance
        slow = head; // 题目要求不能修改原list，不然这里可以直接让fast和head比即可
        while (fast != slow) {
          slow = slow.next;
          fast = fast.next;
        }
        return slow;
      }
    }
    
    return false;
  }
  ```

*****

##### [0287] Find the Duplicate Number

参考[[0142]](#[0142] Linked List Cycle II)

+ 如何将array转化为linked list：f(x) = nums[x]
+ Floyd's Algorithm: 
  + Phase 1: tortoise 一步一node；hare 一步二nodes    $\implies$    两者在intersection相遇
  + Phase 2: 因为intersection并不一定是the entrance of the cycle，所以还需要Phase 2: tortoise 回到起点一步一node；hare 待在intersection一步一node    $\implies$    两者在entrance相遇    

****

******

### Greedy Algorithm

##### [0421] Maximum XOR of Two Numbers in an Array

+ 思路1：数组中两两相异或，保留最大值 $\implies$ $\mathcal{O}(n^2)$ for nested for loop.

+ 思路2：对于异或结果的大小，越高位的权重越大。且已知数组中的元素对应的二进制最大位数为 $L$ 位，则异或的最大可能值为 $\begin{matrix} \underbrace{ 111\ldots111 } \\ L\end{matrix}$ 。

  *e.g.*  nums = [3, 10, 5, 25, 2, 8]

  ​		In binary form: $3=(11)_2 \quad 10=(1010)_2 \quad 5=(101)_2 \quad 25=(11001)_2 \quad 2=(10)_2 \quad 8=(1000)_2$

  ​	   $\implies$ the length of the max number in the binary representation is $5$ 

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

### Hash

##### [0217] Contains Duplicate

+ Solution 1 & 2: 最intuitive的思路：用HashSet $\mathcal{O}(n)$，两种判断方法：

  + `set.contains()`
  + `set.size() == 原数组的长度 ?`

+ Solution 3: 先sort $\mathcal{O}(nlogn)$，然后`nums[i] == nums[i+1] ?`

  当待判定的数组的长度不是很大(not sufficiently large)时，Solution 3会比Solution 1快

  ==The Big-O notation only tells us that for sufficiently large input, one will be faster than the other.==

*****

******

### Rabin-Karp Algorithm

##### [0028] Implement strStr()

C++: [strStr()](http://www.cplusplus.com/reference/cstring/strstr/) / Java: [indexOf(String s)](https://docs.oracle.com/javase/7/docs/api/java/lang/String.html#indexOf(java.lang.String))  返回字符串needle在字符串haystack中第一次出现的位置

+ Solution 1: 最intuitive的方法，`haystack` 中取substring，与 `needle` 进行匹配 

  $\implies \mathcal{O}(haystack.length()*needle.length())$

+ Solution 2: Rabin-Karp Algorithm: 完全参考[[1044]](#[1044] Longest Duplicate Substring)

  参数如下：

  + base = 26
  + $L$ = needle.length()
  + modulus = $2^{32}$：因为 needle.length() 最大可为 $5*10^{4}$，hash可能会overflow

  $\implies \mathcal{O}(max(haystack.length(),needle.length()))$

+ 好像KMP更快

********

##### [0187] Repeated DNA Sequences

+ Solution 1: 最intuitive的方法，肯定是两层for循环嵌套，两两子字符串进行匹配。利用***[Trick](#hashsetTrick)***改进为one pass。

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
2. Check if there is a duplicate substring of a given length $L$ $\implies \mathcal{O}(n-L)$.

子问题2就是[0187] Rabin-Karp + Rolling Hash，不多考虑。子问题1第一反应是从n-1开始，依次循环递减至1。但假如length最大值为0 (无重复的substring)，则复杂度仍需 $\mathcal{O}(n)$。故借助Binary Search，可将复杂度降至 $\mathcal{O}(logn)$。因此该问题总的复杂度为 $\mathcal{O}((n-L)logn)=\mathcal{O}(nlogn)$。

*具体实现：*

+ 子问题1：虽然在检查是否存在duplicate substring时，substring的长度 $L \in [0,n-1]$，但最终的结果却可为0，意味着该字符串中并没有重复的子字符串。以及，考虑到当长度为 $L$ 时，发现重复子串存在，则 `left` 要更新为 $L$ ，因为 $L$ 仍有可能为最终结果（即并不存在长度大于 $L$ 的重复子串）；而未发现重复子串存在时，则 `right` 要更新为 $L-1$ ，因为 $L$ 已经不可能为最终结果了。故选择Binary Search Template II，==但是要加一个改动，保证 $L$ 不会取到0==。

  ```java
  int left = 0, right = n-1; 
  while (left < right) {
      int L = left + (right - left) / 2;
      if (L == left) L++; // 1.为了防止当String S的长度为2，left=0，right=1，此时L取0而取不到1 2. 防止String S的长度为3，此时left=0，right=2，L=1；如果满足条件，则left=L=1，right=2，陷入循环
      if (searchDuplicate(S, L) != -1) left = L;
      else right = L - 1;
  }
  ```

+ 子问题2：因为本题中 $base=26$，且 $L_{max}=3 \times 10^4$，故在[0187]的代码基础上要注意以下几点：

  + 任一sequence的首位字符的权重 $base^{L-1}$ 很大可能会overflow，需依据[线性同余方法](https://en.wikipedia.org/wiki/Linear_congruential_generator#Parameters_in_common_use)引入 $modulus=2^{32}$，故

    + the first sequence $h_0$ 的公式变为
      $$
      h_0 =\sum_{i=0}^{L-1}c_ibase^{L-1-i}\ \% \ modulus
      $$
      对应的 Java 代码为

      ```java
      for(int i = 0; i < L; ++i) h = (h * base + nums[i]) % modulus;
      ```

      *解释：*假设 $L=3$，此时

      按照公式：
      $$
      h_0=c_0\times base^2\ \%\ modulus+c_1\times base\ \%\ modulus+c_2\ \%\ modulus
      $$
      按照代码：
      $$
      \begin{gathered}
      \begin{aligned}
      h_0&=((c_0\ \%\ modulus\times base+c_1)\ \%\ modulus \times base + c_2)\ \%\ modulus \\ 
      &=(c_0\ \%\ modulus\ \%\ modulus\times base^2+c_1\ \%\ modulus\times base + c_2)\ \%\ modulus \\
      &= c_0\ \%\ modulus\ \%\ modulus\ \%\ modulus\times base^2+c_1\ \%\ modulus\ \%\ modulus\times base + c_2\ \%\ modulus
      \end{aligned} \\
      \because \boldsymbol{\color{red}{(a\ \%\ n)\ \%\ n=a\ \%\ n}} \\
      \therefore h_0=c_0\times base^2\ \%\ modulus+c_1\times base\ \%\ modulus+c_2\ \%\ modulus
      \end{gathered}
      $$
      两者结果相同 $\implies$ $Q.E.T.$

      *关于任一sequence的首位字符的权重：*

      任一sequence的首位字符的权重变为 $base^{L-1}\ \%\ modulus$

      对应的 Java 代码为
      
      ```java
      long adjustedWeight = 1;
      for (int i = 1; i <= L; ++i) adjustedWeight = (adjustedWeight * base) % modulus;
      ```

      注意不要为了方便理解写成
      
      ```java
      long adjustedWeight = (long) (Math.pow(base, L) % modulus);  // L = 15时，计算错误
      // or
      long adjustedWeight = (long) (Math.pow(base, L)) % modulus;  // L = 14时，计算错误
      ```
      
    + Rolling Hash的公式变为
      $$
      h_i = (h_{i-1}\times base -c_{i-1}base^L +c_{L-1+i})\  \% \ modulus \ (i\ge1)
      $$
      对应的 Java 代码为

      ```java
      h = (h * base - nums[start - 1] * adjustedWeight + nums[start + L - 1]) % modulus
      ```

  + 又因为一个[purely Java-related overflow issue](https://leetcode.com/problems/longest-duplicate-substring/discuss/292982/Java-version-with-comment)，上述Rolling Hash的计算代码要rewrite如下：

    ```java
    h = (h * base - nums[start - 1] * adjustedWeight % modulus + modulus) % modulus;
    h = (h + nums[start + L - 1]) % modulus;
    ```

    但其实这么改写就是因为原代码写法可能会因为overflow $h_i$ 为negative，所以下面写法==更好理解且速度更快==：

    ```java
    h = (h * base - nums[start - 1] * adjustedWeight + nums[start + L - 1]) % modulus;
    while (h < 0) h += modulus;
    ```

  + LeetCode提供的答案（both [0187] & [1044]）并没有避免hash collision，导致有些sequences仅仅因为hash值相同（实际内容并不相同）而导致选择了错误的 $L$。故还需要==在hash值相同时，增加验证实际内容是否也相同==的方法：

    ```java
    private boolean verify(int x, int y, String s, int L) {
        for (int i = 0; i < L; i++) {
          	if (s.charAt(x++) != s.charAt(y++)) return false;
        }
        return true;
    }
    ```

+ 子问题2如果通过[[1062]](#[1062] Longest Repeating Substring) Solution 2实现会Memory Limit Exceeded；Solution 3实现会hash collision。

******

##### [1062] Longest Repeating Substring

同[[1044]](#[1044] Longest Duplicate Substring)，返回值不同而已

+ Solution 1：Binary Search + HashMap of <u>*Hashes*</u> of already seen strings （==by Rabin-Karp + Rolling Hash==，有检测Hash Collision）
+ Solution 2：Binary Search + Hashset of Already Seen Strings : 利用***[Trick](#hashsetTrick)***改进为one pass
+ Solution 3：Binary Search + Hashset of <u>*Hashes*</u> of Already Seen Strings: 利用***[Trick](#hashsetTrick)***改进为one pass （==by `substring.hashCode()`==，没有检测Hash Collision）

Summary : 

|                           Solution                           |                       Time Complexity                        |                       Space Complexity                       |
| :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
| **Rabin-Karp : constant-time slice + hashset of <u>*hashes*</u> of already seen strings** |     $\mathcal{O}((N−L)logN) \implies \mathcal{O}(NlogN)$     | Moderate space consumption even in the case of large strings  $\implies \mathcal{O}(N)$ to keep the hashmap |
|   **Linear-time slice + hashset of already seen strings**    | $\mathcal{O}(((N−L)L)logN) \implies \mathcal{O}(NlogN)$ in average case and $\mathcal{O}(N^2)$ in the worst case of $L$ close to $N/2$ | Huge space consumption in the case of large strings $\implies \mathcal{O}(N^2)$ to keep the hashset |
| **Linear-time slice + hashset of <u>*hashes*</u> of already seen strings** | $\mathcal{O}(((N−L)L)logN) \implies \mathcal{O}(NlogN)$ in average case and $\mathcal{O}(N^2)$ in the worst case of $L$ close to $N/2$ | Moderate space consumption even in the case of large strings  $\implies \mathcal{O}(N)$ to keep the hashset |

******

******

### Recursion

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

### Reverse Polish Notation

##### [0682] Baseball Game

+ String —> Integer: `Integer.valueOf(String) // new Integer(String): deprecated`

*****

******

### Sliding Window

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

### String

##### [0151] Reverse Words in a String

Given an input string `s`, reverse the order of the **words**. A **word** is defined as a sequence of non-space characters. The **words** in `s` will be separated by at least one space. Return *a string of the words in reverse order concatenated by a single space.*

**Note** that `s` may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.

+ Solution 1: Use ==**built-in method**==: `trim()` , `split()` , `reverse()` and `join()` . $\implies \mathcal{O}(n)\ \&\ \mathcal{O}(n)$

    <img src="/Users/shanyonghao/IdeaProjects/LeetCodeProblems/Notes_img/[0151]_1.png" style="zoom:50%;" />

    ```java
    public String reverseWords(String s) {
      s = s.trim();
      List<String> wordList = Arrays.asList(s.split("\\s+"));
      Collections.reverse(wordList);
      return String.join(" ", wordList);
    }
    ```

    *解释：*

    + `trim()` : 将原字符串中首尾的空格删去，*TC和SC均为 $\mathcal{O}(n)$​*
    + `split("\\s+")` : 将原字符串按照其内一个或多个空格为界，分割为String[]   正则表达式见[Regular Expression](#Regular Expression)
    + `String.join(" ", wordList)` : 以空格作为delimiter（定界符），将 `wordList` 中的内容合并起来。

+ Solution 2: Reverse the Whole String and Then Reverse Each Word $\implies \mathcal{O}(n)\ \&\ \mathcal{O}(n)$​  ==**表现最好**==

    <img src="/Users/shanyonghao/IdeaProjects/LeetCodeProblems/Notes_img/[0151]_2.png" style="zoom:50%;" />

    ==下面的代码全程借用StringBuilder是因为Java中的String是immutable==

    ```java
    public StringBuilder trimSpaces(String s) {
      int left = 0, right = s.length() - 1;
      // remove leading spaces
      while (left <= right && s.charAt(left) == ' ') ++left;
      // remove trailing spaces
      while (left <= right && s.charAt(right) == ' ') --right;
    
      // reduce multiple spaces to single one
      StringBuilder sb = new StringBuilder();
      while (left <= right) {
        char c = s.charAt(left);
        if (c != ' ' || sb.charAt(sb.length()-1) != ' ') sb.append(c); // word后紧跟的第一个空格也保留
        ++left;
      }
      return sb;
    }
    
    public void reverse(StringBuilder sb, int left, int right) {
      while (left < right) {
        char tmp = sb.charAt(left);
        sb.setCharAt(left++, sb.charAt(right));
        sb.setCharAt(right--, tmp);
      }
    }
    
    public void reverseEachWord(StringBuilder sb) {
      int n = sb.length();
      int start = 0, end = 0;
    
      while (start < n) {
        // go to the end of the word
        while (end < n && sb.charAt(end) != ' ') ++end;
        // reverse the word
        reverse(sb, start, end - 1);
        // move to the next word
        start = ++end;
      }
    }
    
    public String reverseWords(String s) {
      // converst string to string builder 
      // and trim spaces at the same time
      StringBuilder sb = trimSpaces(s);
    
      // reverse the whole string
      reverse(sb, 0, sb.length() - 1);
    
      // reverse each word
      reverseEachWord(sb);
    
      return sb.toString();
    }
    ```

+ Solution 3: Stack of Words $\implies \mathcal{O}(n)\ \& \ \mathcal{O}(n)$

    <img src="/Users/shanyonghao/IdeaProjects/LeetCodeProblems/Notes_img/[0151]_3.png" style="zoom:50%;" />

    ```java
    public String reverseWords(String s) {
      int left = 0, right = s.length()-1;
    
      // remove leading and trailing spaces
      while (left <= right && s.charAt(left) == ' ') left++;
      while (left <= right && s.charAt(right) == ' ') right--;
    
      Deque<String> stack = new ArrayDeque<>();
      StringBuilder sb = new StringBuilder();
      // push word by word in front of stack
      while (left <= right) {
        char c = s.charAt(left);
    
        if (c == ' ' && sb.length() != 0) { // 说明找到了一个word
          stack.addFirst(sb.toString());
          sb.setLength(0);
        } else if (c != ' ') {
          sb.append(c);
        }
        left++;
      }
      stack.addFirst(sb.toString());
    
      return String.join(" ", stack);
    }
    ```

****

#####  [0557] Reverse Words in a String III

Given a string `s`, reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.      [[0151]](#[0151] Reverse Words in a String)的简化版

+ Solution 1: [0151] Solution 2 ==完全没用Built-in Method== $\implies \mathcal{O}(n)\ \&\ \mathcal{O}(n)$​​    ==**要求不用Built-in Method选这个**==

    ```java
    public String reverseWords(String s) {
      StringBuilder sb = new StringBuilder(s);
      reverseEachWord(sb);
      return sb.toString();
    }
    
    public void reverseEachWord(StringBuilder sb) {
      int n = sb.length();
      int start = 0, end = 0;
    
      while (start < n) {
        while (end < n && sb.charAt(end) != ' ') end++;
        reverse(sb, start, end-1);
        start = ++ end;
      }
    }
    
    public void reverse(StringBuilder sb, int left, int right) {
      while (left < right) {
        char tmp = sb.charAt(left);
        sb.setCharAt(left, sb.charAt(right));
        sb.setCharAt(right, tmp);
        left++;
        right--;
      }
    }
    ```

+ Solution 2: [0151] Solution 3 有改动：将Stack换为StringBuilder，且不用考虑words之间有多于1个的空格，对应LeetCode Approach #3 ==思路和下面Solution 3几乎一致，既没有做到完全不用Built-in Method `reverse()`，表现也没有Solution 3好== $\implies \mathcal{O}(n)\ \&\ \mathcal{O}(n)$​​

    ```java
    public String reverseWords(String s) {
      StringBuilder res = new StringBuilder();
      StringBuilder word = new StringBuilder();
    
      for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if (c != ' ') {
          word.append(c);
        } else {
          res.append(word.reverse());
          res.append(" "); // 应该是比res.append(word.reverse() + " ");要快些
          word.setLength(0);
        }
      }
      res.append(word.reverse());
    
      return res.toString();
    }
    ```

+ Solution 3: Use Built-in Method $\implies \mathcal{O}(n)\ \&\ \mathcal{O}(n)$​   ==**表现最好**==

    ```java
    public String reverseWords(String s) {
      String words[] = s.split(" ");
      StringBuilder res = new StringBuilder();
      for (String word: words)
        res.append(new StringBuilder(word).reverse().toString() + " ");
      return res.toString().trim();
    }
    ```

+ Solution 4: 在Solution 3的基础上，全部自己实现用到的Built-in Method（除了 `trim()` ），对应LeetCode Approach #3 $\implies \mathcal{O}(n)\ \&\ \mathcal{O}(n)$​​  ==**表现最差**==

    ```java
    public String reverseWords(String s) {
      String words[] = split(s);
      StringBuilder res = new StringBuilder();
      for (String word: words)
        res.append(reverse(word) + " ");
      return res.toString().trim();
    }
    
    public String[] split(String s) { // 和Solution 2相似
      ArrayList<String> words = new ArrayList<>(); // 无法直接用String[]，因为不知道长度
      StringBuilder word = new StringBuilder();
      for (int i = 0; i < s.length(); i++) {
        if (s.charAt(i) == ' ') {
          words.add(word.toString());
          word.setLength(0);
        } else
          word.append(s.charAt(i));
      }
      words.add(word.toString());
      return words.toArray(new String[words.size()]);
    }
    
    public String reverse(String s) { // 利用insert()实现reverse！！
      StringBuilder res = new StringBuilder();
      for (int i = 0; i < s.length(); i++)
        res.insert(0,s.charAt(i));
      return res.toString();
    }
    ```

********

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

### Two-Pointer Technique

##### [0019] Remove Nth Node From End of List



********

##### [0026] Remove Duplicates from Sorted Array

> Given an integer array `nums` sorted in **non-decreasing order**, remove the duplicates [**in-place**](https://en.wikipedia.org/wiki/In-place_algorithm) such that each unique element appears only **once**. The **relative order** of the elements should be kept the **same**. Return `k` *after placing the final result in the first* `k` *slots of* `nums`.
>

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

