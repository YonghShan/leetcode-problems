import java.security.AccessControlContext;

/**
 * @author YonghShan
 * @date 1/24/21 - 21:36
 */

class Solution2 {
    //Solution 2: Brute force: find out all the substring of S and check if it is palindromic, then return the longest one.
    /* Runtime: TLE
     */
    public String longestPalindrome(String s) {
        int max = 0;
        int length = 0;
        String test = "";
        String lps = "";

        for (int i = 0; i<s.length(); i++) {
            for (int j = i+1; j<=s.length(); j++) {
                test = s.substring(i,j);
                length = test.length(); //length = j - i + 1;

                //If the check condition is test.equals(testReversed), TLE
                //If the check condition is isPalindromic1, TLE
                if (isPalindromic1(test) && length > max) {
                    lps = test;
                    max = length;
                }
            }
        }

        return lps;
    }

    //TLE
    public Boolean isPalindromic1(String test) {
        int length = test.length();

        for (int i = 0; i < length / 2; i++) {
            if (test.charAt(i) != test.charAt(length - i - 1)) {
                return false;
            }
        }
        return true;
    }
}
