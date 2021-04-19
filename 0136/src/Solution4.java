/**
 * @author YonghShan
 * @date 4/8/21 - 15:34
 */
public class Solution4 {
    // Clever Trick: Bit Manipulation
    // 1. Take XOR of 0 and some bit, return that bit: a ⊕ 0 = a
    // 2. Take XOR of two same bits, return 0: a ⊕ a = 0
    // 3. XOR满足交换律：a ⊕ b ⊕ a = (a ⊕ a) ⊕ b = 0 ⊕ b = b
    /* Runtime: 1ms (faster than 95.14%)   O(n)
       Memory: 39MB (less than 70.25%)     O(1)
     */
    public int singleNumber(int[] nums){
        int a = 0;
        for (int i : nums) a ^= i;
        return a;
    }
}


