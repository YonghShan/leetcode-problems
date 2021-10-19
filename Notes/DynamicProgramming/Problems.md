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

### 