## ***Concepts***

### Time Complexity

$1 < klogn < log^kn < n< nlogn = log(n!) < n^k < k^n < n! < n^n$

******

*****

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

### Iteration & Recursion

#### ***关于Recursion的Top-down和Bottom-up***

+ If recursive calls before conditional check, then it's bottom up. 
+ If recursive calls after conditional check, then it's top down.

******

******

### Misc.

#### 判断char是否为空字符或者空格

+ 空字符：`char == '\0'`
+ 空格: `Character.isSpace(char)` 或者 `char == ' '`

********

******


