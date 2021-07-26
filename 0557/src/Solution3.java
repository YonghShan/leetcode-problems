/**
 * @author YonghShan
 * @date 7/26/21 - 16:08
 */
public class Solution3 {
    // Use Built-in Method
    /* Runtime: 4ms (faster than 80.71%)   O(n)
       Memory: 39.7MB (less than 50.23%)   O(n)
     */
    public String reverseWords(String s) {
        String words[] = s.split(" ");
        StringBuilder res = new StringBuilder();
        for (String word: words)
            res.append(new StringBuilder(word).reverse().toString() + " ");
        return res.toString().trim();
    }
}
