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

+ 第一种方式：
  $$
  \begin{matrix} 
  \text{① remove the first digit}\ \ \text{③ add the last digit}\\
  h_i = \underbrace{\overbrace{(h_{i-1}-c_{i-1}base^{L-1})} \times base} \overbrace{+c_{L-1+i}} \ (i\ge1)  \\
  \text{② shift left one digit}\quad\quad\quad\quad\quad\ \
  \end{matrix}
  $$
  

+ 第二种方式：（第一种方式内部项乘开 / In a generalised way）<a name="第二种方式"></a>
  $$
  \begin{matrix} 
  \text{① shift left one digit}\ \ \text{③ add the last digit}\\
  h_i = \overbrace{h_{i-1}\times base} \underbrace{-c_{i-1}base^L} \overbrace{+c_{L-1+i}} \ (i\ge1)  \\
  \text{② remove the first digit}
  \end{matrix}
  $$

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

### 