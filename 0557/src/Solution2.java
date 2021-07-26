/**
 * @author YonghShan
 * @date 7/26/21 - 16:00
 */
public class Solution2 {
    // [0151] Solution 3 有改动 对应LeetCode Approach #3
    /* Runtime: 6ms (faster than 58.27%)   O(n)
       Memory: 39.3MB (less than 92.63%)   O(n)
     */
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
}
