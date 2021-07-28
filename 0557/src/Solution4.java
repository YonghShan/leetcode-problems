import java.util.ArrayList;

/**
 * @author YonghShan
 * @date 7/26/21 - 16:53
 */
public class Solution4 {
    // 自己实现 Built-in Method，除了trim()    不考虑
    /* Runtime: 15ms (faster than 28.63%)   O(n)
       Memory: 39.9MB (less than 36.23%)    O(n)
     */
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
}
