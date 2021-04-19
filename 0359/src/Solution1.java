import java.util.HashMap;

/**
 * @author YonghShan
 * @date 4/10/21 - 14:35
 */
class Logger {
    // HashMap<massage, timestamp+10>
    /* Runtime: 26ms (faster than 83.85%)  O(1)
       Memory: 46.8MB (less than 85.66%)   O(M), where M is the size of all incoming messages.
                                           Over the time, the hashtable would have an entry for each unique message that has appeared.
     */
    // 缺点：如Space Complexity所示，这种实现没有Garbage Collection机制
    // 不能仅仅换成LinkedHashMap with constant size of 10，因为这样是假设了messages come in sequence (chronological order).
    // 但是"It is possible that several messages arrive roughly at the same time" （同步）
    HashMap<String, Integer> timetable;

    /** Initialize your data structure here. */
    public Logger() {
        timetable = new HashMap<>();
    }

    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
     If this method returns false, the message will not be printed.
     The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if (timetable.getOrDefault(message, 0) <= timestamp) {
            int endTime = timestamp + 10;
            timetable.put(message, endTime);
            return true;
        }
        return false;
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */
