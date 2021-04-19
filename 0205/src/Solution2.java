/**
 * @author YonghShan
 * @date 4/9/21 - 16:11
 */
public class Solution2 {
    // 2ms: 存数字，和Solution1思路一致
    public boolean isIsomorphic(String s1, String t1) {
        int[] m1 = new int[256];
        int[] m2 = new int[256];

        int n = s1.length();
        char[] s = s1.toCharArray();
        char[] t = t1.toCharArray();
        for(int i=0;i<n;i++) {
            if(m1[s[i]] != m2[t[i]]) return false;
            m1[s[i]] = i+1;
            m2[t[i]] = i+1;
        }
        return true;
    }
}
