/**
 * @author YonghShan
 * @date 1/25/21 - 15:53
 */

class Solution3_1 {
    //Solution 3_1: Dynamic Programming with oblique table filling method (斜向填表）
    //Outer loop factor is the increasing length of substring
    //Inner loop factor is the increasing i(the start index of substring) or j(the end index of substring)
    //Cannot reduce the space complexity with this table filling method
    /* Runtime: 380ms (if...else if...else语句合并，时间/内存不变)
       Memory: 47M
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        String lps = "";
        char[] a = s.toCharArray(); //char[]比s.charAt()快，本例大概快50ms

        for (int lenOfSubstring = 1; lenOfSubstring <= len; lenOfSubstring++){
            for (int i = 0; i < len; i++){
                int j = i + lenOfSubstring -1;
                if (j >= len) //下标已经越界，结束本次循环
                    break;
                if (lenOfSubstring == 1) {
                    dp[i][j] = true;
                } else if (lenOfSubstring == 2) {
                    dp[i][j] = (a[i] == a[j]);
                } else {
                    dp[i][j] = dp[i+1][j-1] && (a[i] == a[j]);
                }
//                //上面的if...else if...else语句可以合并为：
//                //长度为 1 和 2 的单独判断下
//                dp[i][j] = (lenOfSubstring == 1 || lenOfSubstring == 2 || dp[i + 1][j - 1]) && a[i] == a[j];

                if (dp[i][j]) {
                    lps = s.substring(i, j+1); //这里也可以提升速度，只返回i和lenOfSubstring，并不截取，本例大概可以提升30ms
                }
            }
        }

        return lps;
    }
}
