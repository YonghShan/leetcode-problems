/**
 * @author YonghShan
 * @date 6/27/21 - 22:54
 */
public class Solution1 {
    // 从最低位逐位相加，注意进位即可
    /* Runtime: 2ms (faster than 74.66%)    O(max(n,m)), where n = a.size(), m = b.size()
       Memory: 37.8MB (less than 79.07%)    O(max(n,m)) to keep the answer
     */
    public String addBinary(String a, String b) {
        StringBuffer ans = new StringBuffer();

        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        for(; i >= 0 || j >= 0; i--, j--) {  // 从最低位开始相加，同时决定了TC为O(max(n, m))
            // a和b的长度不一定相等，较短的用0补齐
            carry += i >= 0 ? a.charAt(i) - '0' : 0;
            carry += j >= 0 ? b.charAt(j) - '0' : 0;
            // 至此，carry的值是由上一位的进位、a和b当前位的值，一共三个数相加后得到的
            // 一共有3种可能：0（三个数都为0），1（有一个数为1），2（有两个数为1），3（全为1）
            // 如果此时为0，则ans中记0，carry为0；
            // 如果此时为1，则ans中记1，carry为0；
            // 如果此时为2，则ans中记0，carry为1；
            // 如果此时为3，则ans中记1，carry为1.
            ans.append(carry % 2);
            carry = carry / 2;
        }

        // 最后如果有进位，则在前方进行字符串拼接添加进位
        ans.append(carry == 1 ? carry : "");
        return ans.reverse().toString();
    }
}
