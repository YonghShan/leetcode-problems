/**
 * @author YonghShan
 * @date 1/25/21 - 16:57
 */

class Solution3_2Advanced {
    //Solution 3_2 advanced: Dynamic Programming with horizontal table filling method （横向填表）with reduced space complexity
    //Outer loop factor is the decreasing i(the start index of substring)
    //Inner loop factor is the decreasing j(the end index of substring)
    //i不能递增，不然无论j递增还是递减，都不能满足无后效性
    //Can reduce the space complexity with this table filling method
    //当然这种遍历方式也可以不优化空间（即仍使用二维的dp数组），只是时间空间都得不到提升
    /* Runtime: 110ms （合并if...else，时间/内存不变）
       Memory: 40M
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        String res = "";
        boolean[] dp = new boolean[len];
        char[] a = s.toCharArray(); //char[]比s.charAt()快，本例大概快50ms

        for (int i = len - 1; i >= 0; i--) {
            for (int j = len - 1; j >= i; j--) {
                if (a[i] != a[j]) {
                    dp[j] = false;
                } else {
                    if (j - i < 3) {
                        dp[j] = true;
                    } else {
                        dp[j] = dp[j - 1];
                    }
                }
//                //上面的if...else可以合并为：
//                //j - i < 3表示字串长度<4, 即子串长度为0，1（此时，i=j），2，3（此时，i+1 = j-1）
//                //此时，只要首尾字符相同，则子串一定为回文
//                dp[j] = a[i] == a[j] && (j - i < 3 || dp[j - 1]);

                //j-i<2/3都可以，只是从dp[i][j]所表示的含义（s[i...j])来看，当子串长度为3时，i+1 = j-1，s[i+1...j-1]表示单个字符

                if (dp[j] && j - i + 1 > res.length()) {
                    res = s.substring(i, j + 1); //这里也可以提升速度，只返回i和lenOfSubstring，并不截取，本例大概可以提升30ms
                }
            }
        }

        return res;
    }
}
