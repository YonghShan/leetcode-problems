/**
 * @author YonghShan
 * @date 1/25/21 - 17:16
 */
class Solution3_3Advanced {
    //Solution 3_3 advanced: Dynamic Programming with vertical table filling method （纵向填表）with reduced space complexity
    //Outer loop factor is the increasing j(the end index of substring)
    //Inner loop factor is the increasing i(the start index of substring)
    //j不能递减，不然无论i递增还是递减，都不能满足无后效性
    //Can reduce the space complexity with this table filling method
    //当然这种遍历方式也可以不优化空间（即仍使用二维的dp数组），只是时间空间都得不到提升
    /* Runtime: 100ms （合并if...else，时间=110ms,内存不变） *最后返回时才截取子串，runtime约70ms
       Memory: 40M
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        String res = "";
        boolean[] dp = new boolean[len];
        char[] a = s.toCharArray(); //char[]比s.charAt()快，本例大概快50ms

        for (int j = 0; j < len; j++) {
            for (int i = 0; i <= j; i++) {
                if (a[i] != a[j]) {
                    dp[i] = false;
                } else {
                    if (j - i < 3) {
                        dp[i] = true;
                    } else {
                        dp[i] = dp[i + 1];
                    }
                }
//                //上面的if...else可以合并为：
//                //j - i < 3表示字串长度<4, 即子串长度为0，1（此时，i=j），2，3（此时，i+1 = j-1）
//                //此时，只要首尾字符相同，则子串一定为回文
//                dp[i] = a[i] == a[j] && (j - i < 3 || dp[i + 1]);

                //j-i<2/3都可以，只是从dp[i][j]所表示的含义（s[i...j])来看，当子串长度为3时，i+1 = j-1，s[i+1...j-1]表示单个字符

                if (dp[i] && j - i + 1 > res.length()) {
                    res = s.substring(i, j + 1); //这里也可以提升速度，只返回i和lenOfSubstring，并不截取，本例大概可以提升30ms
                }
            }
        }

        return res;
    }
}
