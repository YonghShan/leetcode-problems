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

+ [0682]：Reverse Polish Notation

  + String —> Integer: Integer.valueOf(String)     // new Integer(String): deprecated

+ [0819]：

  + Solution 1: word层面

    + 正则表达式的写法

    + 判断word是否在String[] banned时: Arrays.asList(banned).contains(word)  // 有点蠢，asList大概率takes O(m), contains takes O(m), for循环n次，则takes O(nm), 不如提前用banned建立一个HashSet.

    + 自定义

      Collections.sort(, new Comparator<>() {

      ​		@Override

      ​		public int compare() {

      ​		}

      })

  + Solution 2: char层面