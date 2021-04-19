/**
 * @author YonghShan
 * @date 1/29/21 - 17:02
 */

class Solution1 {
    /* Runtime: 1ms (用未简写的for loop需要2ms)
       Memory: 40M
     */
    public boolean validMountainArray(int[] arr) {
        int firstIndex = arr.length-1; // index of the first element in the array which is bigger than both the previous and next element
        int len = arr.length;

        if (len < 3) {
            return false;
        }

        // Step 1: find the first climax of the mountain
//        for (int j=1; j<len-1; j++) {
//            if (arr[j] > arr[j-1] && arr[j] > arr[j+1] && j < firstIndex) {
//                firstIndex = j;
//            } else if (arr[j] <= arr[j-1]) {
//                break;
//            }
//        }

        //上面的for loop可以简写为
        for (int j=1; j<len; j++) { //因为用不到arr[j+1]，所以j<len
            if (arr[j] <= arr[j-1]) {
                firstIndex = j-1;
                break;
            }
        }

        // the climax need to be in the middle of the mountain, neither the beginning nor the end
        if (firstIndex ==0 || firstIndex == len-1) return false;

        // Step 2: determine whether all the elements behind firstIndex are smaller than it
        for (int k = firstIndex + 1; k < len; k++) {
                if (arr[k] >= arr[k-1]) return false;
        }
        return true;
    }
}