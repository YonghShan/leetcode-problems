#### Greedy Algorithm

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

### 