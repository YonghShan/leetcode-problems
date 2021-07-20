/**
 * @author YonghShan
 * @date 7/20/21 - 01:02
 */
public class Solution4 {
    // Divide and Conquer
    /* Runtime: 0ms                         O(S)
       Memory: 37.1MB (less than 70.81%)    O(mlogn)
     */
    public String longestCommonPrefix(String[] strs) {
        return divideAndConquer(strs, 0, strs.length-1);
    }

    public String divideAndConquer(String[] strs, int left, int right) {
        if (left == right) { // 不停分割，直到不能再分割
            return strs[left];
        } else {
            int mid = left + (right - left) / 2;
            String lcpLeft = divideAndConquer(strs, left, mid);
            String lcpRight = divideAndConquer(strs, mid+1, right);
            return commonPrefix(lcpLeft, lcpRight); // Conquer
        }
    }

    public String commonPrefix(String left, String right) {
        int minLen = Math.min(left.length(), right.length());
        for (int i = 0; i < minLen; i++) {
            if (left.charAt(i) != right.charAt(i))
                return left.substring(0, i);
        }
        return left.substring(0, minLen);
    }
}
