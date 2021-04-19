/**
 * @author YonghShan
 * @date 1/22/21 - 22:21
 */

class Solution1 {
    //Solution 1: by finding the longest common substring between S and S'
    /* Runtime: 140ms with check mothod 1.1;
                200ms with check method 1.2;
                300ms with check method 2;
       Memory: 50M
     */
    public String longestPalindrome(String s) {
        //Step1: reverse s and become s'
        String sReversed = new StringBuffer(s).reverse().toString();

        //Step2: find the longest common substring between S and S'
        char[] a = s.toCharArray();
        char[] b = sReversed.toCharArray();
        int[][] result = new int[a.length+1][b.length+1];
        int max = 0;
        int endIndex = 0;
        String lps = "";

        /* if int[][] result = new int[a.length][b.length];
           then result[i][j] = result[i - 1][j - 1] + 1;
           in for (int i = 0; i < a.length; i++) loop will cause java.lang.ArrayIndexOutOfBoundsException
         */

        //Step3: check the LCS candidates before update the value of max
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (a[i] == b[j]) {
                    result[i + 1][j + 1] = result[i][j] + 1;
                    if (result[i + 1][j + 1] > max) {
                        //Check Method 1: check the index of the last char in the substring candidate
                        int jBeforeReverse = sReversed.length() - 1 - j;
                        if (jBeforeReverse + result[i + 1][j + 1] - 1 == i) {
                            //Check Method 1.1:
                            //Only record the end index (i.e. iNow) and the length (i.e. max/result[i+1][j+1])
                            //other than get the substring each time to reduce more than half the run time
                            endIndex = i;
                            max = result[i + 1][j + 1];

//                            //Check Method 1.2:
//                            lps = s.substring(i - result[i + 1][j + 1] + 1, i + 1);
//                            max = result[i + 1][j + 1];
                        }

//                        //Check Method 2: check if the substring candidate is palindromic
//                        String temp = s.substring(i-result[i+1][j+1]+1, i+1);
//                        if (temp.equals(new StringBuffer(temp).reverse().toString())) {
//                            lps = temp;
//                            max = result[i+1][j+1];
//                        }
                    }
                }
            }
        }

        lps = s.substring(endIndex - max + 1, endIndex + 1);  //Only get the substring when the longest palindromic substring is found
        return lps;
    }
}
