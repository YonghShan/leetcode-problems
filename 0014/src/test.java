/**
 * @author YonghShan
 * @date 7/19/21 - 12:08
 */
public class test {
    public static void main(String[] args) {
        char c = 2 + 'a';
        System.out.println(2+'a');
        System.out.println(c);

        StringBuilder sb = new StringBuilder();
        System.out.println(sb.toString());

        sb.append(2+'a');
        System.out.println(sb.toString());
        sb.append(c);
        System.out.println(sb.toString());
    }
}
