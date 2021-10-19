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

### 