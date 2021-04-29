/**
 * @author YonghShan
 * @date 4/28/21 - 23:46
 */
public class Solution3Advanced {
    // 推荐!!!!
    // Genius idea: 依据Constraint: You may assume that all elements in the array are unique.
    public int search(ArrayReader reader, int target) {
        return mySearch(reader,target);
    }

    /* The Key here is to figure out the easiest way to set our Boundary */
    public int mySearch(ArrayReader reader, int target) {
        int left = 0 , right = target-reader.get(0);
		 /*  Why right = target - reader.get(0) ?
		Explanation: Given all the elements in the Array are unique. If first value is n and we have all possible integers from n to target .
		We will find the target at worst case at (target - n)
		Example: If First Element is -1 and target = 5 if the array had all elements between -1 and 5 i.e.,
		[-1,0,1,2,3,4,5] Worst case 5 will be at (target - firstValue)
		*/

        if(reader.get(right) == target)
            return right;

        while(left <= right){
            int mid = left+(right-left)/2;
            if(reader.get(mid) == target)
                return mid;
            else if (reader.get(mid) < target)
                left = mid+1;
            else
                right = mid - 1;
        }

        return -1;
    }
}
