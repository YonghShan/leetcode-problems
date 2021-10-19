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

### 