import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author YonghShan
 * @date 7/25/21 - 22:31
 */
public class Solution1 {
    // Use Built-in Method
    /* Runtime: 8ms (faster than 47.90%)    O(n)
       Memory: 39.2MB (less than 64.27%)    O(n)
     */
    public String reverseWords(String s) {
        s = s.trim(); // 将原字符串中首尾的空格删去，TC和SC均为O(n)
        List<String> wordList = Arrays.asList(s.split("\\s+")); // 将原字符串按照其内一个或多个空格为界，分割为String[]
        Collections.reverse(wordList);
        return String.join(" ", wordList); // 以空格作为delimiter（定界符），将wordList中的内容合并起来
    }
}
