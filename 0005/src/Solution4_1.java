/**
 * @author YonghShan
 * @date 1/25/21 - 22:56
 */

class Solution4_1 {
    //Solution 4_1: 中心扩展
    /* Runtime: 23ms
       Memory: 40M
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);  //单字符为轴
            int len2 = expandAroundCenter(s, i, i + 1);  //双字符为轴
            int len = Math.max(len1, len2);
            if (len > end - start) {  //根据此判断条件，保证最后输出的是Longest palindromic substring
                //利用Java int会将计算结果的小数部分直接抹去的特性，将单字符为轴和双字符为轴的两组start—end公式进行了合并
                /* if (单字符为轴) { //len一定为奇，i-start+1=(len+1)/2
                      start = i-(len-1)/2;
                      end = i+(len-1)/2; //len为奇时，(len-1)/2 = len/2 -> end = i+len/2
                   } else if (双字符为轴) {  //len一定为偶，i-start+1=len/2
                      start = i+1-len/2; //len为偶时，len/2 = (len-1)/2 + 1 -> start = i+1-[(len-1)/2 + 1] = i-(len-1)/2
                      end = i+len/2;
                   }
                 */
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        //因为即使while循环停止前，left和right仍会被修改，所以此时的left的值等于真正需要的子串的左index-1，此时的right的值等于真正需要的子串的右index+1，
        //因此，真正的左index=left+1，真正的右index=right-1
        //返回的是子串的长度，等于右index-左index+1，i.e. length = (right-1) - (left+1) + 1 = right -left -1
        return right - left - 1;
    }
}
