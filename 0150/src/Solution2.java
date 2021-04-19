import java.util.HashSet;
import java.util.Set;

/**
 * @author YonghShan
 * @date 3/3/21 - 22:36
 */
public class Solution2 {
    // In-place: 从左到右遍历，找到第一个操作符的index i，将tokens[i-2]update为result，将i+1之后的元素依次当前移两位，第一次移动的时候记得tokens最后两位设为null
    /* Runtime: 506ms    O(n^2)
       Memory: 38.4MB    O(1)
     */
    public int evalRPN(String[] tokens) {
        if (tokens.length == 1) return Integer.parseInt(tokens[0]);

        while (tokens[1] != null) {
            int i = 0;
            // 找到第一个操作符的index：
            for (; i < tokens.length; i++) {
                if (tokens[i].equals("+") || tokens[i].equals("-") || tokens[i].equals("*") ||tokens[i].equals("/")) break;
            }
            // update tokens[i-2]:
            switch (tokens[i]) {
                case "+" :
                    tokens[i-2] = Integer.toString(Integer.parseInt(tokens[i-2]) + Integer.parseInt(tokens[i-1]));
                    break;
                case "-" :
                    tokens[i-2] = Integer.toString(Integer.parseInt(tokens[i-2]) - Integer.parseInt(tokens[i-1]));
                    break;
                case "*" :
                    tokens[i-2] = Integer.toString(Integer.parseInt(tokens[i-2]) * Integer.parseInt(tokens[i-1]));
                    break;
                case "/" :
                    tokens[i-2] = Integer.toString(Integer.parseInt(tokens[i-2]) / Integer.parseInt(tokens[i-1]));
                    break;
            }
            // 将将i+1之后的元素依次当前移两位：
            for (int j = i-1; j < tokens.length-2; j++) {
                tokens[j] = tokens[j+2];
            }
            // 这里其实第一次移动的时候tokens最后两位设为null即可
            tokens[tokens.length-2] = null;
            tokens[tokens.length-1] = null;
        }
        return Integer.parseInt(tokens[0]);
    }
}
