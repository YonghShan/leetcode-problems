import java.util.*;

/**
 * @author YonghShan
 * @date 1/22/21 - 14:40
 */

class Solution {
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        //Step1: convert the element in releaseTimes into duration
        for (int i=releaseTimes.length-1; i>0; i--) {
            releaseTimes[i] -= releaseTimes[i - 1];
        }

        //Step2: determine whether the same character exists in keysPressed
        for (int i=0; i<keysPressed.length(); i++) {
            for (int j=i+1; j<keysPressed.length(); j++) {
                if (keysPressed.charAt(i) == keysPressed.charAt(j) && releaseTimes[i] > releaseTimes[j]) {
                    //if the same char exists and the duration of the previous char is larger
                    releaseTimes[j] = releaseTimes[i];
                }
            }
        }

        //Step3: creates a HashMap for the key pressed anf its duration
        HashMap<Character, Integer> res = new HashMap<>();
        for (int i=0; i<releaseTimes.length; i++) {
            res.put(keysPressed.charAt(i), releaseTimes[i]);
        }

        //Step4: Select the char with longest duration
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

        return list.get(0).getKey();
    }
}
