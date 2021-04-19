/**
 * @author YonghShan
 * @date 1/21/21 - 00:12
 */
public class SolutionTest {
    public static void main(String[] args) {
        String[] ops1 = new String[]{"5","2","C","D","+"};
        String[] ops2 = new String[]{"5","-2","4","C","D","9","+","+"};

        Solution s = new Solution();
        int finalScore1 = s.calPoints(ops1);
        int finalScore2 = s.calPoints(ops2);
        System.out.println(finalScore1);
        System.out.println(finalScore2);
    }
}
