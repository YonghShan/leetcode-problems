
/**
 * @author YonghShan
 * @date 3/7/21 - 03:06
 */
public class Solution1 {
    // Recursion: 从头开始遍历，遇到数字先记录下来，然后跳过'['，对之后的内容进行decode，返回decodedString后再根据记录好的数字重复
    /* Runtime: 0ms     O(maxK⋅n), where maxK is the maximum value of k and n is the length of a given string s.
       Memory: 37.3MB   O(n). This is the space used to store the internal call stack used for recursion.
                        As we are recursively decoding each nested pattern, the maximum depth of recursive call stack would not be more than n.
     */
    int index = 0;
    public String decodeString(String s) {
        StringBuilder result = new StringBuilder();
        while (index < s.length() && s.charAt(index) != ']') {
            if (!Character.isDigit(s.charAt(index)))
                result.append(s.charAt(index++));
            else {
                int k = 0;
                // build k while next character is a digit
                while (index < s.length() && Character.isDigit(s.charAt(index)))
                    k = k * 10 + s.charAt(index++) - '0';
                // ignore the opening bracket '['
                index++;
                String decodedString = decodeString(s); // ATTENTION：虽然传的还是s，但是现在的index已经变了！！！
                // ignore the closing bracket ']'
                index++;
                // build k[decodedString] and append to the result
                while (k-- > 0)
                    result.append(decodedString);
            }
        }
        return new String(result);
    }
}
