import java.util.Stack;

/**
 * @author YonghShan
 * @date 3/2/21 - 21:29
 */
class MinStack2 {
    /* Runtime: 4ms
     */
    Stack<Integer> minValues;
    Stack<Integer> values;

    /** initialize your data structure here. */
    public MinStack2() {
        values = new Stack<>();
        minValues=new Stack<>();

    }

    public void push(int x) {
        if(minValues.isEmpty() || x<=minValues.peek()){
            minValues.push(x);
        }
        values.push(x);
    }

    public void pop() {

        if(values.isEmpty()) return;
        int popedValue=values.pop();

        if(popedValue==minValues.peek()){
            minValues.pop();
        }
    }

    public int top() {
        return values.peek();
    }

    public int getMin() {
        return minValues.peek();
    }
}
