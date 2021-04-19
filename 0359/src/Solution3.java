import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author YonghShan
 * @date 4/10/21 - 15:07
 */
class Logger3 {
    // LinkedHashMap 没有同步
    private Map<String, Integer> cache;

    public Logger3() {
        cache = new LinkedHashMap<>();
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        // iterator over beginning of hashmap to cleanup old entries,
        // until we find a time < 10 seconds
        Iterator<Map.Entry<String, Integer>> iterator = cache.entrySet().iterator();
        while (iterator.hasNext()) {
            if (timestamp - iterator.next().getValue() >= 10) {
                iterator.remove();
            } else {
                break;
            }
        }

        if (cache.containsKey(message)) {
            return false;
        }

        cache.put(message, timestamp);

        return true;
    }
}
