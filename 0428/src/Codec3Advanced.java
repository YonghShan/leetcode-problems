import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author YonghShan
 * @date 4/1/21 - 16:13
 */
public class Codec3Advanced {
    // [297] CodecAdvanced的对应版本
    // 不额外去creat class WrappableInt 又不使用static variable (keep stateless)地实现Codec3
    // 把Codec3中的WrappableInt换成int[1]，更好
    /*                                              Serialization                                                         Deserialization
       Runtime: 6ms (faster than 88.03%)      O(n) for each node                                 O(3n) for String.split(), deque.removeFirst(), for loop for adding children
       Memory: 40.7MB (less than 60.76%)      O(2n) because of Serialized String & Recursion     O(2n) for Recursion and deque
     */

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuilder sb=new StringBuilder();
        buildString(root,sb);
        return sb.toString();
    }
    private void buildString(Node node, StringBuilder sb){
        if(node==null){
            sb.append("X");
            sb.append(",");
        }else{
            sb.append(node.val);  // 这里不需要cast为char，是因为这里用了delimiter ","
            sb.append(",");
            sb.append(node.children.size());
            sb.append(",");
            for (Node child:node.children){
                buildString(child,sb);
            }
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        Deque<String> deque=new ArrayDeque<>(Arrays.asList(data.split(","))); // String.split() : O(n)    Arrays.asList() : O(1)
        return buildTree(deque);
    }
    private Node buildTree(Deque<String> deque){
        String s1=deque.removeFirst();
        if(s1.equals("X")) return null;

        int rootVal=Integer.valueOf(s1);
        int childrenNumber=Integer.valueOf(deque.removeFirst());

        Node root=new Node(rootVal, new ArrayList<>());
        for (int i = 0; i < childrenNumber; i++){
            root.children.add(buildTree(deque));
        }
        return root;
    }
}
