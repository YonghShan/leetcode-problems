import java.util.Arrays;

public class SolutionTest {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{2,7,11,15};
        System.out.println(Arrays.toString(nums));
        int target = 9;
        int[] result = s.twoSum(nums, target);
        System.out.println(Arrays.toString(result));
    }
}