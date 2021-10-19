#### Rabin-Karp Algorithm

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

### 