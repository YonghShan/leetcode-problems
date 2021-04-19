/**
 * @author YonghShan
 * @date 1/22/21 - 20:44
 */

class Solution2 {
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        //Assume the slowest key is the first key pressed and its releaseTime is the longest duration
        int longestDuration = releaseTimes[0];
        char slowestKey = keysPressed.charAt(0);

        for (int i = 0; i<releaseTimes.length-1; i++) {
            int diff = releaseTimes[i+1] - releaseTimes[i]; //define i starting with 0, so there is no i-1
            //also since we define i+1 here, the condition that determines whether the loop continues is i<releaseTimes.length-1

            if (diff > longestDuration) { //the (i+1)th char is slower
                slowestKey = keysPressed.charAt(i+1);
                longestDuration = diff;
            } else if (diff == longestDuration && keysPressed.charAt(i+1) > slowestKey) { //output lexicographically
                slowestKey = keysPressed.charAt(i+1);
            }
        }

        return slowestKey;
    }
}
