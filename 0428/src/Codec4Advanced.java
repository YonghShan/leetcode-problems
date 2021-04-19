import java.util.ArrayList;

/**
 * @author YonghShan
 * @date 4/1/21 - 17:24
 */
public class Codec4Advanced {
    // 最佳！！！！！！！！前提是node.val不可为负(有越界的风险)，[297]中不能用new int[1]代替deque的原因就是因为那题的val可为负，且最小可能为-1000，溢出
    // // 不额外去creat class WrappableInt 又不使用static variable (keep stateless)，也不使用delimiter和extra data structure地实现Codec3

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        this.rserialize(root, sb);
        return sb.toString();
    }

    private void rserialize(Node root, StringBuilder sb) {
        if(root == null) return;
        sb.append((char)(root.val + '0'));
        for(Node child: root.children) rserialize(child, sb);
        sb.append('#');
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if(data.isEmpty()) return null;
        return rdeserialize(data, new int[1]);  // new int[1]: Space complexity O(1)
    }

    private Node rdeserialize(String data, int[] index) {
        if(index[0] == data.length()) return null;
        Node node = new Node(data.charAt(index[0]) - '0', new ArrayList<Node>());
        index[0]++;
        while(data.charAt(index[0]) != '#') node.children.add(rdeserialize(data, index));
        index[0]++;
        return node;
    }
}
