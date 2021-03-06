/**
 * @author YonghShan
 * @date 4/9/21 - 16:12
 */
public class Solution3 {
    // 1ms: 你存我，我存你，相互检查
    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        int[] map = new int[256];
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();

        for (int i = 0; i < sc.length; i++) {
            if (map[sc[i]] == 0) {
                map[sc[i]] = tc[i];
            } else {
                if (map[sc[i]] != tc[i]) {
                    return false;
                }
            }
        }

        int[] map2 = new int[256];
        for (int i = 0; i < tc.length; i++) {
            if (map2[tc[i]] == 0) {
                map2[tc[i]] = sc[i];
            } else {
                if (map2[tc[i]] != sc[i]) {
                    return false;
                }
            }
        }

        return true;
    }
}
