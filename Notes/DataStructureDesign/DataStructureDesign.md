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

