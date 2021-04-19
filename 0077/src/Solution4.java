import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 3/18/21 - 20:51
 */
class Solution4 {
    // Recursion
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        if(k > n) return ans;
        if(k == 0) {
            ans.add(new ArrayList<>());
            return ans;
        }
        for(int i = n; i > 0; i--){
            List<List<Integer>> rec = combine(i-1, k-1);
            for(List<Integer> l : rec){
                l.add(i);
                ans.add(l);
            }
        }
        return ans;
    }
}
