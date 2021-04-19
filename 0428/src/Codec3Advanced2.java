import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author YonghShan
 * @date 4/1/21 - 18:05
 */
public class Codec3Advanced2 {
    // 用new int[1]代替deque，new int[1]的space complexity为O(1)
    // 不额外去creat class WrappableInt 又不使用static variable (keep stateless)，也不使用extra data structure地实现Codec3
    /*                                              Serialization                                                         Deserialization
       Runtime: 1ms (faster than 100%)      O(n) for each node                                 O(n) for loop for adding children
       Memory: 40.6MB (less than 71.16%)    O(2n) because of Serialized String & Recursion     O(2n) for Recursion and deque
     */

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuilder sb=new StringBuilder();
        buildString(root,sb);
        return sb.toString();
    }
    private void buildString(Node node, StringBuilder sb){
        if(node==null){
            return;
        }

        sb.append((char) (node.val + '0'));
        sb.append((char) (node.children.size() + '0'));
        for (Node child:node.children){
            buildString(child,sb);
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data.isEmpty()) return null;
        return buildTree(data, new int[1]);
    }
    private Node buildTree(String data, int[] index){
        // To be written for every approach.
        if (index[0] == data.length()) return null;

        Node root = new Node(data.charAt(index[0]) - '0', new ArrayList<>());
        index[0]++;
        int childrenNum = data.charAt(index[0]) - '0';
        for (int i = 0; i < childrenNum; i++) {
            index[0]++;
            root.children.add(buildTree(data, index));
        }

        return root;
    }
}
