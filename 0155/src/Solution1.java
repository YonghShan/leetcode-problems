import java.util.ArrayList;
import java.util.List;

/**
 * @author YonghShan
 * @date 3/2/21 - 21:08
 */
class MinStack {
    /* Runtime: 303ms
       Memory: 40.6MB
     */

    /** initialize your data structure here. */
    private List<Integer> data;
    public MinStack() {
        data = new ArrayList<>();
    }

    public void push(int x) {
        data.add(x);
    }

    public void pop() {
        if (data.isEmpty()) return ;
        data.remove(data.size()-1);
    }

    public int top() {
        return data.get(data.size()-1);
    }

    public int getMin() {
        int min = data.get(data.size()-1);
        for (Integer i : data) {
            min = Math.min(min, i);
        }
        return min;
    }
}