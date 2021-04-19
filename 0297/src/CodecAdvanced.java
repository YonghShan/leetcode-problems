import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author YonghShan
 * @date 4/1/21 - 16:19
 */
public class CodecAdvanced {
    // [428] Codec3Advanced的对应版本，推荐！！！
    // 不能向[428]中Codec4Advanced使用的new int[1]，是因为本题中node.val可为负，且最小可为-1000，溢出
    /* Runtime: 7ms (faster than 91.87%)
       Memory: 40.6MB (less than 81.39%)
     */

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb=new StringBuilder();
        buildString(root,sb);
        return sb.toString();
    }
    private void buildString(TreeNode node, StringBuilder sb){
        if(node==null){
            sb.append("X");
            sb.append(",");
        }else{
            sb.append(node.val);
            sb.append(",");
            buildString(node.left,sb);
            buildString(node.right,sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) { // 和Codec中使用LinkedList相比，ArrayDeque更快
        Deque<String> deque=new ArrayDeque<>(Arrays.asList(data.split(",")));
        return buildTree(deque);

    }

    private TreeNode buildTree(Deque<String> deque){
        String s=deque.removeFirst();
        if(s.equals("X")){
            return null;
        }else{
            int val=Integer.valueOf(s);
            TreeNode node=new TreeNode(val);
            node.left=buildTree(deque);
            node.right=buildTree(deque);
            return node;
        }
    }
}
