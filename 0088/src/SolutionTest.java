import java.util.Arrays;

/**
 * @author YonghShan
 * @date 1/27/21 - 16:36
 */
public class SolutionTest {
    public static void main(String[] args) {
        int[] a = new int[6];
        a[0] = 2;
        a[3] = 1;
        a[5] = 4;

        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
