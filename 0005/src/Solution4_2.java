/**
 * @author YonghShan
 * @date 1/26/21 - 00:11
 */

class Solution4_2 {
    //Solution 4_2: 中心扩展 （学习一下为什么中心位置枚举到 len - 2 即可）
    /* Runtime: 30ms
       Memory: 40M
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        String res = s.substring(0, 1);
        // 中心位置枚举到 len - 2 即可
        for (int i = 0; i < len - 1; i++) {
            String oddStr = centerSpread(s, i, i);
            String evenStr = centerSpread(s, i, i + 1);
            String maxLenStr = oddStr.length() > evenStr.length() ? oddStr : evenStr;
            if (maxLenStr.length() > maxLen) {
                maxLen = maxLenStr.length();
                res = maxLenStr;
            }
        }
        return res;
    }

    private String centerSpread(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // 和4_1一样，这里左index = left+1, 右index = right-1
        return s.substring(left + 1, right);
    }
}
