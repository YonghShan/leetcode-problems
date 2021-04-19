/**
 * @author YonghShan
 * @date 1/29/21 - 16:02
 */

class Solution1 {
    // 双层循环遍历
    /* Runtime: 1ms
       Memory: 39M
     */
    public boolean checkIfExist(int[] arr) {
        int len = arr.length;

        if (arr == null || len == 0) {
            return false;
        }

        for (int i=0; i<len; i++) {
            for (int j=0; j<len; j++) {
                if (i!=j && arr[i] == 2*arr[j]) { // in case arr[i] = 0
                    return true;
                }
            }
        }

        return false;
    }
}
