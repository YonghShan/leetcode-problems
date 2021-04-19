import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author YonghShan
 * @date 4/1/21 - 15:34
 */
class Codec3 {
    // 思路本身很好理解，但所有用WrappableInt的都该换成int[1]!!!!
    // 笔记本上的Approach 2: Depth First Search with Children Sizes: 对每个node记录val和children number
    /*                                              Serialization                                                         Deserialization
       Runtime: 1ms (faster than 100%)      O(n) for each node                                 O(2n) for one character at a time and also construct the tree along the way
       Memory: 40.6MB (less than 71.16%)    O(2n+n) because of Serialized String & Recursion     O(n) for Recursion only
     */

    // A wrapper class to pass the index in the data string by reference since the problem statement
    // says that we are not allowed to use any globals or member variables to store the states.
    // It should be stateless. Primitives are pass by value, so we create a wrapper object.
    class WrappableInt {
        private int value;
        public WrappableInt(int x) {
            this.value = x;
        }
        public int getValue() {
            return this.value;
        }
        public void increment() {
            this.value++;
        }
    }

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        this._serializeHelper(root, sb);
        return sb.toString();
    }

    private void _serializeHelper(Node root, StringBuilder sb) {
        // To be written for every approach
        if (root == null) {
            return;
        }
        // Actual Value
        sb.append((char) (root.val + '0'));
        // Children Number
        sb.append((char) (root.children.size() + '0'));

        // Update parentID before DFS
        for (Node child : root.children) {
            _serializeHelper(child, sb);
        }
    }
    /*         1
             / \ \
            3  5  6      Serialized String be like: 13 32 20 40 50 60
           /\
          2  4
     */

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if(data.isEmpty())
            return null;

        return this._deserializeHelper(data, new WrappableInt(0));
    }

    private Node _deserializeHelper(String data, WrappableInt index) {
        // To be written for every approach.
        if (index.getValue() == data.length()) return null;

        Node root = new Node(data.charAt(index.getValue()) - '0', new ArrayList<>());
        index.increment();
        int childrenNum = data.charAt(index.getValue()) - '0';
        for (int i = 0; i < childrenNum; i++) {
            index.increment();
            root.children.add(_deserializeHelper(data, index));
        }

        return root;
    }
}
