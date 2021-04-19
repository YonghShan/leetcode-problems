import sun.util.resources.cldr.da.CurrencyNames_da;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author YonghShan
 * @date 3/31/21 - 23:56
 */
class Codec2 {
    // 笔记本上的Approach 1: Parent Child Relationships: 对每个node记录uniqueID, val和parentID
    // 和官方使用嵌套的一个HashMap不同，这里使用HashMap<自己的unique ID，相对应的Node>
    /*                                           Serialization                                                         Deserialization
       Runtime: 7ms (faster than 73.64%)      O(n) for each node                                 O(3n) for iterating the String data: O(2n) for first pass + O(n) for second pass
       Memory: 40.7MB (less than 60.76%)      O(4n) because of Serialized String & Recursion     O(2n) for HashMap
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
        this._serializeHelper(root, sb, new WrappableInt(1), null);
        return sb.toString();
    }

    private void _serializeHelper(Node root, StringBuilder sb, WrappableInt identity, Integer parentID) {
        // To be written for every approach
        if (root == null) {
            return;
        }
        // Own Identity
        sb.append((char) (identity.getValue() + '0'));  // 不cast为char，serialized data中append的是相应数字的ASCII码 见test
        // Actual Value
        sb.append((char) (root.val + '0'));
        // Parent's val
        sb.append((char) (parentID == null ? 'N' : parentID + '0'));

        // Update parentID before DFS
        parentID = identity.getValue();
        for (Node child : root.children) {
            identity.increment(); // Unique Id for every child node
            _serializeHelper(child, sb, identity, parentID);
        }
    }
    /*            1(1)
               /   \   \
            3(2)  5(5)  6(6)      Serialized String be like: 11null 231 322 442 551 661
           /   \                                           ID为1的node的val为1，parent的ID为null
          2(3) 4(4)                                        ID为2的node的val为3，parent的ID为1
     */

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if(data.isEmpty())
            return null;

        return this._deserializeHelper(data);
    }

    private Node _deserializeHelper(String data) {
        // To be written for every approach.
        HashMap<Integer, Node> mapping = new HashMap<>();
        // First pass for constructing the HashMap
        for (int i = 0; i < data.length(); i+=3) {
            int id = data.charAt(i) - '0';
            int orgVal = data.charAt(i+1) - '0';
            mapping.put(id, new Node(orgVal, new ArrayList<>()));
        }

        // Second pass for reconstructing the tree
        for (int i = 3; i < data.length(); i+=3) {
            // Current Node
            int id = data.charAt(i) - '0';
            Node curr = mapping.get(id);

            // Parent Node
            int parentId = data.charAt(i+2) - '0';
            Node parent = mapping.get(parentId);

            // Connection
            parent.children.add(curr);
        }

        return mapping.get(1);
    }
}
