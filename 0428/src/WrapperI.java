/**
 * @author YonghShan
 * @date 4/1/21 - 15:06
 */
public class WrapperI {
    private int value;
    public WrapperI(int x) {
        this.value = x;
    }
    public int getValue() {
        return this.value;
    }
    public void increment() {
        this.value++;
    }
}
