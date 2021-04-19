import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author YonghShan
 * @date 4/10/21 - 15:03
 */
class Logger2 {
    // HashMap + HashSet 没有同步
    Map<String, Integer> times;
    /** Initialize your data structure here. */
    public Logger2() {
        times = new HashMap<>();
    }

    private final int THRESHOLD = 10;

    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
     If this method returns false, the message will not be printed.
     The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        // Do the garbage collection first
        Set<String> keys = new HashSet<>(times.keySet());
        for(String key : keys) {
            if(timestamp - times.get(key) >= THRESHOLD) {
                times.remove(key);
            }
        }

        // Then determine and update
        if(!times.containsKey(message) || timestamp - times.get(message) >= 10) {
            times.put(message, timestamp);
            return true;
        }
        return false;
    }
}
