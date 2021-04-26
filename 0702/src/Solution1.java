/**
 * @author YonghShan
 * @date 4/25/21 - 23:50
 */
public class Solution1 {
    // Just do iteration
    /* Runtime: 8ms      O(n), where n is the length of array \in [1, 10^4]
       Memory: 51.9MB    O(1)
     */
    public int search(ArrayReader reader, int target) {
        int i = 0;
        while (reader.get(i) != 2147483647) {
            if (reader.get(i) == target) return i;
            i++;
        }

        return -1;
    }
}
