import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author YonghShan
 * @date 1/20/21 - 23:52
 */
class Solution {
    // Reverse Polish Notation
    public int calPoints(String[] ops) {
        Deque<Integer> record = new ArrayDeque<>();

        for (String op : ops) {
            if (op.equals("+")) { //取出record最后两位，相加后放栈顶
                int top = record.removeFirst();
                int newTop = top + record.peekFirst();
                record.addFirst(top);
                record.addFirst(newTop);
            } else if (op.equals("D")) { //double当前top作为newTop
                int top = record.peekFirst();
                record.push(top * 2);
            } else if (op.equals("C")) { //从record中删去当前栈顶元素
                record.removeFirst();
            } else {
                //record.addFirst(new Integer(op)); //deprecated
                record.addFirst(Integer.valueOf(op));
            }
        }

        int finalScore = 0;
        for (int score : record) {
            finalScore += score;
        }

        return finalScore;
    }
}
