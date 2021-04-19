/**
 * @author YonghShan
 * @date 1/24/21 - 15:37
 */

class Solution1Advanced {
    //Solution 1 advanced: by using result[j] instead of result[i][j] to reduce the space complexity O(n^2) to O(n)
    /* Runtime: 120ms with check mothod 1.1;
                275ms with check method 1.2;
                380ms with check method 2;
       Memory: 40M
     */
    public String longestPalindrome(String s) {
        //Step1: reverse s and become s'
        String sReversed = new StringBuffer(s).reverse().toString();

        //Step2: find the longest common substring between S and S'
        char[] a = s.toCharArray();
        char[] b = sReversed.toCharArray();
        int[] result = new int[b.length+1];
        int max = 0;
        int endIndex = 0;
        String lps = "";

        //Step3: check the LCS candidates before update the value of max
        for (int i = 0; i < a.length; i++) {
            for (int j = b.length-1; j >= 0; j--) {  //DIFFERENCE!!
                if (a[i] == b[j]) {
                    result[j + 1] = result[j] + 1;
                    if (result[j + 1] > max) {
                        //Check Method 1: check the index of the last char in the substring candidate
                        int jBeforeReverse = sReversed.length() - 1 - j;
                        if (jBeforeReverse + result[j + 1] - 1 == i) {
                            //Check Method 1.1:
                            //Only record the end index (i.e. iNow) and the length (i.e. max/result[j+1])
                            //other than get the substring each time to reduce the run time
                            endIndex = i;
                            max = result[j + 1];

//                            //Check Method 1.2:
//                            lps = s.substring(i - result[j + 1] + 1, i + 1);
//                            max = result[j + 1];
                        }

//                        //Check Method 2: check if the substring candidate is palindromic
//                        String temp = s.substring(i-result[j+1]+1, i+1);
//                        if (temp.equals(new StringBuffer(temp).reverse().toString())) {
//                            lps = temp;
//                            max = result[j+1];
//                        }
                    }
                } else { //Attention!! If not match, we need to set the result back to 0 manually
                    result[j + 1] = 0;
                }
            }
        }

        lps = s.substring(endIndex - max + 1, endIndex + 1);  //Only get the substring when the longest palindromic substring is found
        return lps;
    }
}

