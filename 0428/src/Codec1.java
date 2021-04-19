import java.util.*;

/**
 * @author YonghShan
 * @date 3/31/21 - 11:17
 */
public class Codec1 {
    // Preorder with Recursion: Deserialization部分参考了[0394] Decode String: Reverse Polish Notation
    /*                                           Serialization                                Deserialization
       Runtime: 9ms (faster than 56.33%)      O(n) for each node                      O(n) for iterating the String data
       Memory: 41MB (less than 42.01%)        O(>n) because of "[""]" & Recursion     O(>n) for stack
     */
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if (root == null) return "null";
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(root.val);
        for (Node child : root.children) {
            sb.append(serialize(child));
        }
        sb.append("]");

        // System.out.println(sb.toString());
        return sb.toString();
    }
    /*         1
             / \ \
            3  5  6      Serialized String be like: [1[3[5][6]][2][4]]
           /\
          2  4
     */

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data.equals("null")) return null;
        Stack<Node> stack = new Stack<>();
        Node root = new Node(0, new LinkedList<>());
        stack.push(root);

        char[] chars = data.toCharArray();
        int i = 0;
        while(i < chars.length) {
            if(chars[i] == '[') {
                i += 1;
                int num = 0;
                while(chars[i] >= '0' && chars[i] <= '9') {
                    num = num * 10 + (chars[i] - '0');
                    i += 1;
                }

                Node node = new Node(num, new LinkedList<>());
                stack.peek().children.add(node);
                stack.push(node);

                // i -= 1;
            }

            if(chars[i] == ']') {
                i += 1;
                stack.pop();
            }

            // i += 1;
        }

        root = stack.pop();
        return root.children.get(0);
    }
}
