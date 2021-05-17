+ [0001]：Solution2 - Trick: 如何one pass同时完成对HashMap的insert和search
+ [0021]：无论是Iteration还是Recursion，关键都是比较l1.val和l2.val
+ [0572]：Recursion
  + 当s == null时: return t == null
  + 当s != null时：
    + t == null：false    // 可省略 
    + s.val == t.val && isSameTree(s, t)：    // 不能先判断s.val == t.val再判断isSameTree(s, t)
      + s == null: return t == null
      + s != null: 
        + t == null: false     // 不可省略
        + s.val != t.val: false
        + s.val == t.val: isSameTree(s.right, t.right) && isSameTree(s.left, t.left);
    + s.val != t.val：isSubtree(s.left, t) || isSubtree(s.right, t);
+ 