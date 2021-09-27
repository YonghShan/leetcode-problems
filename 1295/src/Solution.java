/**
 * @author YonghShan
 * @date 1/27/21 - 00:34
 */

class Solution {
    public int findNumbers(int[] nums) {
        int res = 0;
        int digit = 1;

        for (int num:nums) {
            while (num > 9) {
                num /= 10;
                digit ++;
                System.out.println(digit);
            }
            System.out.println("------");

            if (digit % 2 == 0) {
                res++;
                System.out.println(res);
                System.out.println("+++++++");
            }

            digit = 1;
        }
        return res;
    }
}
