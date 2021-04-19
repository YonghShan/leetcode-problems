import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author YonghShan
 * @date 3/18/21 - 20:57
 */
class Solution5 {
    // 没看懂
    // Lexicographic (binary sorted) combinations: An algorithm from Donald E. Knuth, The Art of Computer Programming, 4A (2011)
    // Has the same time complexity and space complexity with Solution2
    public List<List<Integer>> combine(int n, int k) {
        // init first combination
        LinkedList<Integer> nums = new LinkedList<Integer>();
        for(int i = 1; i < k + 1; ++i)
            nums.add(i);
        nums.add(n + 1);

        List<List<Integer>> output = new ArrayList<List<Integer>>();
        int j = 0;
        while (j < k) {
            // add current combination
            output.add(new LinkedList(nums.subList(0, k)));
            // increase first nums[j] by one
            // if nums[j] + 1 != nums[j + 1]
            j = 0;
            while ((j < k) && (nums.get(j + 1) == nums.get(j) + 1))
                nums.set(j, j++ + 1); // LinkedList.set(int index, E element): Replaces the element at the specified position in this list with the specified element.
            nums.set(j, nums.get(j) + 1);
        }
        return output;
    }
}
