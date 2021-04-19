/**
 * @author YonghShan
 * @date 4/9/21 - 22:55
 */
public class Solution2 {
    // 既然Alphabet一共就26个字母，那就用一个length=26的array，代替HashMap
    /* Runtime: 7ms (faster than 84.23%)   O(n)    和HashMap相比快很多
       Memory: 39.4MB (less than 66.29%)   O(1)
     */
    public int firstUniqChar(String s) {
        int[] alphabet = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            alphabet[idx]++;
        }
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            if (alphabet[idx] == 1) return i;
        }

        return -1;
    }
}
