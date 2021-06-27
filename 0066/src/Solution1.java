/**
 * @author YonghShan
 * @date 6/26/21 - 15:10
 */
public class Solution1 {
    // 1. 当前位的值为9，+1后存在进位
    // 2. 从最低位至最高位全都存在进位，原数组长度需+1
    /* Runtime: 0ms                         O(n)
       Memory: 37.7MB (less than 25.01%)    O(n+1)
     */
    public int[] plusOne(int[] digits) {
        int len = digits.length;

        for (int i = len-1; i >= 0; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                digits[i] += 1;
                return digits;
            }
        }

        //  会到这里，说明此时digits中全为9
        digits = new int[len+1];
        digits[0] = 1;
        return digits;
    }
}
