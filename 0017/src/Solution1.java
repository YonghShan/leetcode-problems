import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 3/20/21 - 15:23
 */
public class Solution1 {
    /* Runtime: 0ms   通用Backtracking的TC通式 O(b^d), where b is the number of branches (in this case, up to 4) (大部分按键都只对应3个字母，7和9对应4个）
                                                           d is the depth of recursion (in this case, equals to length of digits, L)
                      => O(4^L)，这是找到所有可能的组合所需要的TC，而在每找到一个可能的过程中，还要花费O(L)去创建curr(append()), thus, in total, O(4^L * L)
       Memory: 37.6MB (less than 89.27%)  O(L) for recursion call stack, regardless the space for output
     */
    private String digits;
    private List<String> res = new ArrayList<>();
    private String[] mapping = {null, null, "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        this.digits = digits;
        if (digits.length() == 0) return res;
        backtrack(0, new StringBuffer());
        return res;
    }

    public void backtrack(int first, StringBuffer curr) {
        if (curr.length() == digits.length()) {
            res.add(new String(curr.toString()));
        } else{
            int num = digits.charAt(first) - '0'; // 通过first(index)得到对应的数字键
            for (int i = 0; i < mapping[num].length(); i++) {
                char guess = mapping[num].charAt(i); // 依次取得该数字键所对应的字母：e.g. num=2，则guess依次为a, b, c
                curr.append(guess);
                backtrack(first+1, curr);
                curr.deleteCharAt(curr.length()-1);
            }
        }
    }
}
