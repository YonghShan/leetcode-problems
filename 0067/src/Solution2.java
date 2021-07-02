import java.math.BigInteger;

/**
 * @author YonghShan
 * @date 6/29/21 - 11:29
 */
public class Solution2 {
    // 不涉及加法操作，只使用位操作（bit manipulation）
    // BigInteger导致java性能不高
    /* Runtime: 6ms (faster than 18.12%)    O(n+m), where n = a.size(), m = b.size()
       Memory: 39.1MB (less than 42.34%)    O(max(n,m)) to keep the answer
     */
    public String addBinary(String a, String b) {
        // 1. convert a and b into integers x and y
        //    x will be used to keep an answer, y for the carry
        BigInteger x = new BigInteger(a, 2);
        BigInteger y = new BigInteger(b, 2);
        BigInteger zero = new BigInteger("0", 2);
        BigInteger carry, answer;
        // 2. while carry is nonzero:
        while (y.compareTo(zero) != 0) {
            answer = x.xor(y); // 异或：不加进位的加法
            carry = x.and(y).shiftLeft(1); // 进位：与操作后，左移一位
            x = answer;
            y = carry;
        }
        // 3. return x in the binary form
        return x.toString(2);
    }
}
