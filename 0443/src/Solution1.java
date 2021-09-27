import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author YonghShan
 * @date 9/26/21 - 21:49
 */
public class Solution1 {
    public int compress(char[] chars) {
        // 双指针，返回的是长度，但是原数组也需要更改
        int left = 0, right = 0;
        int ans = 0;
        while(right < chars.length) {
            while(right < chars.length && chars[right] == chars[left]) right++;
            if(left != right - 1) { // 需要更改
                chars[ans++] = chars[left]; // 将当前代表的字符写入数组中
                char[] str = String.valueOf(right - left).toCharArray(); // 字符数量转化成字符型数组   因为right-left最多为2000，所以str的长度最多为2000，看作O(1)
                for(char s : str) chars[ans ++] = s;
            }else chars[ans++] = chars[left];
            left = right;
        }
        return ans;
    }
}
