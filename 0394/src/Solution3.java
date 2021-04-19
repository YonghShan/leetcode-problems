import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * @author YonghShan
 * @date 3/7/21 - 23:43
 */
public class Solution3 {
    // Two Stack: countStack: The stack would store all the integer k.
    //            stringStack: The stack would store all the decoded strings.
    /* Iterate over the string s and process each character as follows:
           Case 1) If the current character is a digit (0-9), append it to the number k.
           Case 2) If the current character is a letter (a-z), append it to the currentString.
           Case 3) If current character is a opening bracket '[', push k and currentString into countStack and stringStack respectively.
           Case 4) Closing bracket ']': We must begin the decoding process,
                       We must decode the currentString. Pop currentK from the countStack and decode the pattern currentK[currentString]
                       As the stringStack contains the previously decoded string, pop the decodedString from the stringStack.
                       Update the decodedString = decodedString + currentK[currentString]
     */
    /* Runtime: 0ms    O(maxK⋅n), where maxK is the maximum value of k and n is the length of a given string s.
                       We traverse a string of size n and iterate k times to decode each pattern of form k[string].
                       This gives us worst case time complexity as O(maxK⋅n).
       Memory: 37.2MB  O(m+n), where m is the number of letters(a-z) and n is the number of digits(0-9) in string s.
                       In worst case, the maximum size of stringStack and countStack could be m and n respectively.
     */
    public String decodeString(String s) {
        Deque<Integer> countStack = new ArrayDeque<>();
        Deque<StringBuilder> stringStack = new ArrayDeque<>();
        StringBuilder currentString = new StringBuilder();
        int k = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + ch - '0';
            } else if (ch == '[') {
                // push the number k to countStack
                countStack.addFirst(k);
                // push the currentString to stringStack
                stringStack.addFirst(currentString);
                // reset currentString and k
                currentString = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                StringBuilder decodedString = stringStack.pop();
                // decode currentK[currentString] by appending currentString k times
                for (int currentK = countStack.pop(); currentK > 0; currentK--) {
                    decodedString.append(currentString);
                }
                currentString = decodedString;
            } else {
                currentString.append(ch);
            }
        }
        return currentString.toString();
    }
}
