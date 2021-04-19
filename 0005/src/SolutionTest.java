/**
 * @author YonghShan
 * @date 1/23/21 - 23:33
 */
public class SolutionTest {
    public static void main(String[] args) {
        String s = "adcbbdcb";
        String sReversed = new StringBuffer(s).reverse().toString();
        //System.out.println(sReversed);

        Solution1 solution = new Solution1();
        System.out.println(solution.longestPalindrome(s));

        Solution1Advanced so = new Solution1Advanced();
        System.out.println(so.longestPalindrome(s));

        Solution2 s2 = new Solution2();
        System.out.println(s2.longestPalindrome(s));

        //String s = "abcfbbdcba";
        System.out.println(s.substring(1, 1));

        //System.out.println(5/2);

        Solution4_1 s4 = new Solution4_1();
        System.out.println(s4.longestPalindrome(s));


    }
}
