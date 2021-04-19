import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 3/31/21 - 16:10
 */
public class Test {
    public static void main(String[] args) {
        Node a = new Node(5, new ArrayList<>());
        Node b = new Node(6, new ArrayList<>());
        Node c = new Node(3, new ArrayList<>());
        c.children.add(a);
        c.children.add(b);
        Node d = new Node(2, new ArrayList<>());
        Node e = new Node(4, new ArrayList<>());

        Node root = new Node(1, new ArrayList<>());
        root.children.add(c);
        root.children.add(d);
        root.children.add(e);

        Codec1 obj1 = new Codec1();
        obj1.serialize(root);

        WrapperI i = new WrapperI(1);
        System.out.println(i.getValue() + '0');     // 49
        System.out.println((char)(i.getValue() + '0')); // 1

        //System.out.println(a.val);
        System.out.println((char) a.val);

    }
}
