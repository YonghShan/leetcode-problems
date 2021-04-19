import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author YonghShan
 * @date 3/19/21 - 11:26
 */
public class Solution3Advanced {
    // DP
    public List<String> generateParenthesis(int n) {
        List<List<String>> lists = new ArrayList<>();
        lists.add(Collections.singletonList(""));

        for (int i = 1; i <= n; ++i) {
            final List<String> list = new ArrayList<>();

            for (int j = 0; j < i; ++j) {
                for (final String first : lists.get(j)) {
                    for (final String second : lists.get(i - 1 - j)) {
                        // Let the "(" always at the first position, to produce a valid result,
                        // we can only put ")" in a way that there will be i pairs () inside the extra () and n - 1 - i pairs () outside the extra pair.
                        list.add("(" + first + ")" + second);
                    }
                }
            }
            lists.add(list);
        }

        return lists.get(lists.size() - 1);
    }
}
