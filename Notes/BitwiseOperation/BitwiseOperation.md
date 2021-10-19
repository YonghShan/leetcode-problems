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

### 