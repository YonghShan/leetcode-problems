import java.util.ArrayList;

/**
 * @author YonghShan
 * @date 3/31/21 - 23:55
 */
class Codec {
    // Codec 2/3/4/5 都是基于这个template

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
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if(data.isEmpty())
            return null;

        Node rootNode = new Node(data.charAt(0) - '0', new ArrayList<Node>());
        WrappableInt index = new WrappableInt(1);
        this._deserializeHelper(data, rootNode, index);
        return rootNode;
    }

    private void _deserializeHelper(String data, Node node, WrappableInt index) {

        // To be written for every approach.
    }
}
