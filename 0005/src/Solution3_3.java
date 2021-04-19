/**
 * @author YonghShan
 * @date 1/25/21 - 17:06
 */

class Solution3_3 {
    //Solution 3_3: Dynamic Programming with vertical table filling method （纵向填表）
    //Outer loop factor is the increasing j(the end index of substring)
    //Inner loop factor is the decreasing i(the start index of substring)
    //j不能递减，不然无论i递增还是递减，都不能满足无后效性
    //Cannot reduce the space complexity with this table filling method
    /* Runtime: 220ms （合并if...else，时间=200ms,内存不变）
       Memory: 45M
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        String res = "";
        boolean[][] dp = new boolean[len][len];
        char[] a = s.toCharArray(); //char[]比s.charAt()快，本例大概快50ms

        for (int j = 0; j < len; j++) {
            for (int i = j; i >= 0; i--) {
                if (a[i] != a[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
//                //上面的if...else可以合并为：
//                //j - i < 3表示字串长度<4, 即子串长度为0，1（此时，i=j），2，3（此时，i+1 = j-1）
//                //此时，只要首尾字符相同，则子串一定为回文
//                dp[i][j] = a[i] == a[j] && (j - i < 3 || dp[i + 1][j - 1]);

                //j-i<2/3都可以，只是从dp[i][j]所表示的含义（s[i...j])来看，当子串长度为3时，i+1 = j-1，s[i+1...j-1]表示单个字符

                if (dp[i][j] && j - i + 1 > res.length()) {
                    res = s.substring(i, j + 1); //这里也可以提升速度，只返回i和lenOfSubstring，并不截取，本例大概可以提升30ms
                }
            }
        }

        return res;
    }
}
