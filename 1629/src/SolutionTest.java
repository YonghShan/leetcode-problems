import java.util.*;

/**
 * @author YonghShan
 * @date 1/22/21 - 15:12
 */

public class SolutionTest {
    public static void main(String[] args) {
        int[] releaseTimes = new int[]{9,29,49,50};
        String keysPressed = "cbcd";

//        Solution s = new Solution();
//        s.slowestKey(releaseTimes, keysPressed);
//        System.out.println(s.slowestKey(releaseTimes, keysPressed));

        //Step1
        for (int i=releaseTimes.length-1; i>0; i--) {
            releaseTimes[i] -= releaseTimes[i - 1];
        }
        System.out.println(Arrays.toString(releaseTimes));

        //Step2
        for (int i=0; i<keysPressed.length(); i++) {
            for (int j=i+1; j<keysPressed.length(); j++) {
                if (keysPressed.charAt(i) == keysPressed.charAt(j) && releaseTimes[i] > releaseTimes[j]) {
                    releaseTimes[j] = releaseTimes[i];
                }
            }
        }
        System.out.println(Arrays.toString(releaseTimes));

        //Step3
        HashMap<Character, Integer> res = new HashMap<>();
        for (int i=0; i<releaseTimes.length; i++) {
            res.put(keysPressed.charAt(i), releaseTimes[i]);
        }

        //Step4
        ArrayList<Map.Entry<Character, Integer>> list = new ArrayList<>(res.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Character,Integer>>() {
            @Override
            public int compare(Map.Entry<Character,Integer> o1, Map.Entry<Character,Integer> o2) {
                if (o1.getValue() < o2.getValue()) {
                    return 1;
                } else if (o1.getValue() == o2.getValue()) {
                    if (o1.getKey() < o2.getKey()) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else {
                    return -1;
                }
            }
        });

        System.out.println(list.get(0).getKey());
    }
}
