import java.util.*;

/**
 * @author YonghShan
 * @date 2/24/21 - 11:59
 */
class Solution1 {
    // BFS找the shortest path:
    // BFS逐层遍历的实现靠 for (int i = 0; i < count; i++)，其中count = q.size()
    // 但这种遍历方式要遍历完0000～9999共10000个node，耗时太久了（可以通过Test感受下当无法到达target时，Solution1所需要的遍历时间）
    // 如果能达到target，还能依靠return times;提前结束
    // 如果deadends阻碍了到达target，就要几乎把全部node都过一遍，导致TLE
    // 为了不TLE，可以增加判断：deadends是否会使得target无法到达?

    // 揭秘了，Solution1和Solution2实现BFS的Time Complexity一样，之所以之前Solution1会TLE，是因为visited用了List，改成HashSet后Accepted
    /* Runtime: 154ms (faster than 39.26%)
       Memory: 46.8MB (less than 59.43%)
     */
    public int openLock(String[] deadends, String target) {
        Set<String> dead = new HashSet<>();
        for (String d : deadends) dead.add(d);

        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        q.add("0000");
        visited.add("0000");
        int times = -1;

        while (!q.isEmpty()) {
            int count = q.size(); // 注意在进入for循环后，一直在往queue中添加元素，就导致for循环不能按照预期地遍历完一个level就结束
            times++;
            for (int i = 0; i < count; i++) {
                String node = q.poll();
                if (node.equals(target)) {
                    return times;
                } else if (!dead.contains(node)) {
                    // 各位+1/-1：
                    for (int j = 0; j < 4; j++) {
                        int x = node.charAt(j) - '0'; // 如果char在'0'～'9'之间，可以通过这种方法将其转变为int
                        // +1：
                        x = (x == 9) ? 0 : x+1;
                        String neighbor1 = node.substring(0, j) + ("" + x) + node.substring(j+1);
                        if (!visited.contains(neighbor1)) {
                            q.add(neighbor1);
                            visited.add(neighbor1);
                        }
                        // -1: 注意这里x刚+1，原本的-1要变为-2
                        x = (x == 0) ? 8 : x-2;
                        if (x == -1) x = 9;
                        String neighbor2 = node.substring(0, j) + ("" + x) + node.substring(j+1);
                        if (!visited.contains(neighbor2)) {
                            q.add(neighbor2);
                            visited.add(neighbor2);
                        }
                    }
                }
            }
        }
        return -1;
    }
}