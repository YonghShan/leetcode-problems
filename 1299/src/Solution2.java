import java.util.Arrays;

/**
 * @author YonghShan
 * @date 1/29/21 - 23:47
 */

class Solution2 {
    // in-place
    /* Runtime: 205ms
       Memory: 40M
     */
    public int[] replaceElements(int[] arr) {
        int len = arr.length;
        for (int i=0; i<len-1; i++) {
            arr[i] = findGreatest(arr, i);
        }

        // 和上面合不合并，对Runtime无影响
        arr[len-1] = -1;

        return arr;
    }

    public int findGreatest(int[] arr, int index) {
        int max = arr[index+1];
        for (int i = index+1; i<arr.length-1; i++) {
            if (arr[i+1] > max) {
                max = arr[i+1];
            }
        }
        return max;
    }
}
