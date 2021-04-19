import java.util.Arrays;

/**
 * @author YonghShan
 * @date 1/27/21 - 00:50
 */

class Solution1 {
    // 自身平方后，sort
    /* Runtime: 3ms    O(nlogn)
       Memory: 41MB    O(1)
     */
    public int[] sortedSquares(int[] nums) {
        /* for each循环中，不能改变基本类型的值，但是可以改变对象的值
           只是因为利用for each循环遍历，只是将原数组中的元素的值赋给了num
           而Java中，基本类型是直接值拷贝，改变num并不会改变原元素
           但是对象是Reference赋值，一变全变
         */
//        for (int num : nums){
//            num = num * num;
//        }

        //当需要以其他顺序遍历数组或改变数组中的元素（指基本对象）时，还是必须使用传统for循环
        for (int i = 0; i < nums.length; i++) {
            nums[i] *= nums[i];
        }

        Arrays.sort(nums);

        return nums;
    }
}
