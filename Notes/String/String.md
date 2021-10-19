### String

#### Built-in Method

+ `trim()` : Returns a string whose value is this string, with any leading and trailing whitespace removed.
+ `split(String reg)` : Splits this string around matches of the given [regular expression](https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html#sum).
+ `String.join(CharSequence delimiter, CharSequence... elements)` : Returns a new String composed of copies of the `CharSequence elements` joined together with a copy of the specified `delimiter`.

*****

#### Regular Expression

+ 常用：

  元字符是一个预定义的字符。

  | 正则表达式 |                             描述                             |
  | :--------: | :----------------------------------------------------------: |
  |    `\d`    |               匹配一个数字，是 `[0-9]` 的简写                |
  |   `\d+`    |                         匹配多个数字                         |
  |    `\D`    |              匹配一个非数字，是 `[^0-9]` 的简写              |
  |    `\s`    |          匹配一个空格，是 `[ \t\n\x0b\r\f]` 的简写           |
  |   `\s+`    |                         匹配多个空格                         |
  |    `\S`    |                        匹配一个非空格                        |
  |    `\w`    | 匹配一个单词字符（大小写字母、数字、下划线），是 `[a-zA-Z_0-9]` 的简写 |
  |    `\W`    | 匹配一个非单词字符（除了大小写字母、数字、下划线之外的字符），等同于 `[^\w]` |

+ Java中正则表达式字符串：

  Java 中的正则表达式字符串有两层含义，首先 Java 字符串转义出符合正则表达式语法的字符串，然后再由转义后的正则表达式进行模式匹配。

  因为反斜杠本就在Java中表示转义字符，所以==上述过程中尤其需要注意反斜杠==：

  + 对于匹配 `.` / `{` / `[` / `(` / `?` / `$` / `^` / `*` 这些特殊字符时，正则表达式即为 `\.`。但这与Java中转义 `.` 的写法重合，因此在Java中匹配 `.` 的正则表达式字符串要写为 `\\.`，第一步转义为正则表达式 `\.`，第二步在匹配。
  + 在匹配 `\` 时，对于正则表达式即为 \\\，但Java中要写为 `\\\\`。
  + 匹配多个空格的正则表达式为 `\s+`，但在Java中写为字符串则需写为 `\\s+`。

+ Java中内置的字符串正则处理方法：

  在 Java 中有四个内置的运行正则表达式的方法，分别是 `matches()`、`split())`、`replaceFirst()`、`replaceAll()`。注意 `replace()` 方法不支持正则表达式。

  |                   方法                   |                  描述                   |
  | :--------------------------------------: | :-------------------------------------: |
  |           `s.matches("regex")`           | 当仅且当正则匹配整个字符串时返回 `true` |
  |            `s.split("regex")`            |      按匹配的正则表达式切片字符串       |
  | `s.replaceFirst("regex", "replacement")` |        替换首次匹配的字符串片段         |
  |  `s.replaceAll("regex", "replacement")`  |           替换所有匹配的字符            |

+ 参考：

  + [Java 正则表达式详解](https://segmentfault.com/a/1190000009162306)
  + [Java Doc](https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html#sum)

*****

#### StringBuilder

+ StringBuilder的三种清空方式：==方法一最慢，方法二和三差不多==
  + 重新 `new StringBuilder()`；
  + 使用 `stringBuilder.delete(0, stringBuilder.length())`；
  + 使用 `setLength(0)`
+ Built-in Method:
  + 不仅同样有 `charAt(int index)` 还有 `setCharAt(int index, char c)`
  + `insert(int index, content)` : 在指定位置处插入内容，内容可为任一基本类型或者对象，原本的内容向后移动  ==可以用来reverse== [[0557]](#[0557] Reverse Words in a String III) Solution 4

******

****

##### [0151] Reverse Words in a String

Given an input string `s`, reverse the order of the **words**. A **word** is defined as a sequence of non-space characters. The **words** in `s` will be separated by at least one space. Return *a string of the words in reverse order concatenated by a single space.*

**Note** that `s` may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.

+ Solution 1: Use ==**built-in method**==: `trim()` , `split()` , `reverse()` and `join()` . $\implies \mathcal{O}(n)\ \&\ \mathcal{O}(n)$

  <img src="/Users/shanyonghao/IdeaProjects/LeetCodeProblems/Notes_img/[0151]_1.png" style="zoom:50%;" />

  ```java
  public String reverseWords(String s) {
    s = s.trim();
    List<String> wordList = Arrays.asList(s.split("\\s+"));
    Collections.reverse(wordList);
    return String.join(" ", wordList);
  }
  ```

  *解释：*

  + `trim()` : 将原字符串中首尾的空格删去，*TC和SC均为 $\mathcal{O}(n)$​*
  + `split("\\s+")` : 将原字符串按照其内一个或多个空格为界，分割为String[]   正则表达式见[Regular Expression](#Regular Expression)
  + `String.join(" ", wordList)` : 以空格作为delimiter（定界符），将 `wordList` 中的内容合并起来。

+ Solution 2: Reverse the Whole String and Then Reverse Each Word $\implies \mathcal{O}(n)\ \&\ \mathcal{O}(n)$​  ==**表现最好**==

  <img src="/Users/shanyonghao/IdeaProjects/LeetCodeProblems/Notes_img/[0151]_2.png" style="zoom:50%;" />

  ==下面的代码全程借用StringBuilder是因为Java中的String是immutable==

  ```java
  public StringBuilder trimSpaces(String s) {
    int left = 0, right = s.length() - 1;
    // remove leading spaces
    while (left <= right && s.charAt(left) == ' ') ++left;
    // remove trailing spaces
    while (left <= right && s.charAt(right) == ' ') --right;
  
    // reduce multiple spaces to single one
    StringBuilder sb = new StringBuilder();
    while (left <= right) {
      char c = s.charAt(left);
      if (c != ' ' || sb.charAt(sb.length()-1) != ' ') sb.append(c); // word后紧跟的第一个空格也保留
      ++left;
    }
    return sb;
  }
  
  public void reverse(StringBuilder sb, int left, int right) {
    while (left < right) {
      char tmp = sb.charAt(left);
      sb.setCharAt(left++, sb.charAt(right));
      sb.setCharAt(right--, tmp);
    }
  }
  
  public void reverseEachWord(StringBuilder sb) {
    int n = sb.length();
    int start = 0, end = 0;
  
    while (start < n) {
      // go to the end of the word
      while (end < n && sb.charAt(end) != ' ') ++end;
      // reverse the word
      reverse(sb, start, end - 1);
      // move to the next word
      start = ++end;
    }
  }
  
  public String reverseWords(String s) {
    // converst string to string builder 
    // and trim spaces at the same time
    StringBuilder sb = trimSpaces(s);
  
    // reverse the whole string
    reverse(sb, 0, sb.length() - 1);
  
    // reverse each word
    reverseEachWord(sb);
  
    return sb.toString();
  }
  ```

+ Solution 3: Stack of Words $\implies \mathcal{O}(n)\ \& \ \mathcal{O}(n)$

  <img src="/Users/shanyonghao/IdeaProjects/LeetCodeProblems/Notes_img/[0151]_3.png" style="zoom:50%;" />

  ```java
  public String reverseWords(String s) {
    int left = 0, right = s.length()-1;
  
    // remove leading and trailing spaces
    while (left <= right && s.charAt(left) == ' ') left++;
    while (left <= right && s.charAt(right) == ' ') right--;
  
    Deque<String> stack = new ArrayDeque<>();
    StringBuilder sb = new StringBuilder();
    // push word by word in front of stack
    while (left <= right) {
      char c = s.charAt(left);
  
      if (c == ' ' && sb.length() != 0) { // 说明找到了一个word
        stack.addFirst(sb.toString());
        sb.setLength(0);
      } else if (c != ' ') {
        sb.append(c);
      }
      left++;
    }
    stack.addFirst(sb.toString());
  
    return String.join(" ", stack);
  }
  ```

****

#####  [0557] Reverse Words in a String III

Given a string `s`, reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.      [[0151]](#[0151] Reverse Words in a String)的简化版

+ Solution 1: [0151] Solution 2 ==完全没用Built-in Method== $\implies \mathcal{O}(n)\ \&\ \mathcal{O}(n)$​​    ==**要求不用Built-in Method选这个**==

  ```java
  public String reverseWords(String s) {
    StringBuilder sb = new StringBuilder(s);
    reverseEachWord(sb);
    return sb.toString();
  }
  
  public void reverseEachWord(StringBuilder sb) {
    int n = sb.length();
    int start = 0, end = 0;
  
    while (start < n) {
      while (end < n && sb.charAt(end) != ' ') end++;
      reverse(sb, start, end-1);
      start = ++ end;
    }
  }
  
  public void reverse(StringBuilder sb, int left, int right) {
    while (left < right) {
      char tmp = sb.charAt(left);
      sb.setCharAt(left, sb.charAt(right));
      sb.setCharAt(right, tmp);
      left++;
      right--;
    }
  }
  ```

+ Solution 2: [0151] Solution 3 有改动：将Stack换为StringBuilder，且不用考虑words之间有多于1个的空格，对应LeetCode Approach #3 ==思路和下面Solution 3几乎一致，既没有做到完全不用Built-in Method `reverse()`，表现也没有Solution 3好== $\implies \mathcal{O}(n)\ \&\ \mathcal{O}(n)$​​

  ```java
  public String reverseWords(String s) {
    StringBuilder res = new StringBuilder();
    StringBuilder word = new StringBuilder();
  
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c != ' ') {
        word.append(c);
      } else {
        res.append(word.reverse());
        res.append(" "); // 应该是比res.append(word.reverse() + " ");要快些
        word.setLength(0);
      }
    }
    res.append(word.reverse());
  
    return res.toString();
  }
  ```

+ Solution 3: Use Built-in Method $\implies \mathcal{O}(n)\ \&\ \mathcal{O}(n)$​   ==**表现最好**==

  ```java
  public String reverseWords(String s) {
    String words[] = s.split(" ");
    StringBuilder res = new StringBuilder();
    for (String word: words)
      res.append(new StringBuilder(word).reverse().toString() + " ");
    return res.toString().trim();
  }
  ```

+ Solution 4: 在Solution 3的基础上，全部自己实现用到的Built-in Method（除了 `trim()` ），对应LeetCode Approach #3 $\implies \mathcal{O}(n)\ \&\ \mathcal{O}(n)$​​  ==**表现最差**==

  ```java
  public String reverseWords(String s) {
    String words[] = split(s);
    StringBuilder res = new StringBuilder();
    for (String word: words)
      res.append(reverse(word) + " ");
    return res.toString().trim();
  }
  
  public String[] split(String s) { // 和Solution 2相似
    ArrayList<String> words = new ArrayList<>(); // 无法直接用String[]，因为不知道长度
    StringBuilder word = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == ' ') {
        words.add(word.toString());
        word.setLength(0);
      } else
        word.append(s.charAt(i));
    }
    words.add(word.toString());
    return words.toArray(new String[words.size()]);
  }
  
  public String reverse(String s) { // 利用insert()实现reverse！！
    StringBuilder res = new StringBuilder();
    for (int i = 0; i < s.length(); i++)
      res.insert(0,s.charAt(i));
    return res.toString();
  }
  ```

********

##### [0819] Most Common Word

+ Solution 1: word层面

  + 正则表达式的写法: 双斜杠转义

  + 判断word是否在String[] banned时: `Arrays.asList(banned).contains(word)`  有点蠢，`asList`大概率takes $\mathcal{O}(m)$, `contains` takes $\mathcal{O}(m)$, for循环n次，则takes $\mathcal{O}(nm)$, 不如提前用banned建立一个HashSet.

  + 自定义

    ```java
    Collections.sort(要sort的对象, new Comparator<>() {
    	@Override
    	public int compare() {
    	}
    })
    ```

+ Solution 2: char层面

******

******

