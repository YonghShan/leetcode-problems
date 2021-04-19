import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 3/18/21 - 16:51
 */
public class Solution1 {
    // Backtracking:
    /* Runtime: 133ms (faster than 5.15%)
       Memory: 40MB (less than 89.51%)
     */
    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> rowRes = new ArrayList<>();
    private int n;
    private int k;
    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;
        backtrack(0);
        return res;
    }

    public void backtrack(int idx) {
        for (int i = 1; i <= n; i++) { // 和Solution2的区别：这里还要是从1开始，Solution2中直接从加过1的idx开始，就不用再去判断i > rowRes.get(rowRes.size()-1)
            // idx为0时，放什么都行；idx不为0时，新加入的数不能小于当前rowRes的最后一位
            if (idx == 0 || i > rowRes.get(rowRes.size()-1)) { // 如果这里的判断条件是!rowRes.contains(i)，则res的输出为所有排列的可能
                rowRes.add(i);
                if (idx == k-1) { // 此时，找到了一种排列的方式，将当前的rowRes加入res中：
                    res.add(new ArrayList<>(rowRes));
                    // 注意！！！ 不可以直接res.add(rowRes); 因为之后rowRes只要发生变化，res中所添加的rowRes也会相应地变化
//                    // 或者，下面这种操作：
//                    List<Integer> temp = new ArrayList<>();
//                    temp.addAll(rowRes); // 将当前的rowRes值copy到一个新的List<Integer> temp中
//                    res.add(temp); // 再将temp加入res中，这样之后rowRes发生变化，此时存放的temp不会变化
                } else {
                    backtrack(idx+1);
                }
                rowRes.remove(rowRes.size()-1); // 注意！！ArrayList.remove(int index)是删除指定index处的元素，不要记成remove(int value)
                // 如果res和rowRes用的是ArrayList，可以直接调用removeList()
            }
        }
    }
}
