/**
 * @author YonghShan
 * @date 9/26/21 - 18:56
 */
public class Test {
    public static void main(String[] args) {
        Solution1 s1 = new Solution1();
        int[] nums = new int[]{1,2,3,6,8};
        int target = 4;
        int res = s1.searchInsert(nums, target);
        System.out.println(res);
    }
}
