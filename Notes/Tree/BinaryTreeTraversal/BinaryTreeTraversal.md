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

