/**
 * @author YonghShan
 * @date 7/14/21 - 15:38
 */
public class test {
    public static void main(String[] args) {
        int base = 26;
        int L = 17;
        long modulus = (long)Math.pow(2, 32);

        long adjustedWeight = 1;
        for (int i = 1; i <= L; ++i) {
            adjustedWeight = (adjustedWeight * base) % modulus;
            System.out.println("i: " + i + " adjustedWeight: " + adjustedWeight);
        }

        long adjustedWeight2 = 1;
        for (int i = 1; i <= L; ++i) {
            adjustedWeight2 = (long) (Math.pow(base, i)) % modulus;
            System.out.println("i: " + i + " adjustedWeight2: " + adjustedWeight2);
        }


        long adjustedWeight3 = 1;
        for (int i = 1; i <= L; ++i) {
            adjustedWeight3 = (long) (Math.pow(base, i) % modulus);
            System.out.println("i: " + i + " adjustedWeight3: " + adjustedWeight3);
        }
    }
}
