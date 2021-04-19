import java.util.*;

/**
 * @author YonghShan
 * @date 2/24/21 - 16:12
 */
public class Test2 {
    public static void main(String[] args) {
        String[] deadends = new String[8];
        deadends[0] = "0001";
        deadends[1] = "0003";
        deadends[2] = "0092";
        deadends[3] = "0012";
        deadends[4] = "0902";
        deadends[5] = "0102";
        deadends[6] = "1002";
        deadends[7] = "9002";
        Set<String> dead = new HashSet<>();
        for (String d : deadends) dead.add(d);

        Queue<String> queue = new LinkedList();
        queue.offer("0000");
        queue.offer(null);

        Set<String> seen = new HashSet();
        seen.add("0000");

        int depth = 0;
        int num = 0;
        while (!queue.isEmpty()) {
            String node = queue.poll();
            num++;
            if (node == null) {
                depth++;
                if (queue.peek() != null)
                    queue.offer(null);
            } else if (node.equals("0002")) {
                break;
            } else  if (!dead.contains(node)){
                for (int i = 0; i < 4; ++i) {
                    for (int d = -1; d <= 1; d += 2) {
                        int y = ((node.charAt(i) - '0') + d + 10) % 10;
                        String nei = node.substring(0, i) + ("" + y) + node.substring(i+1);
                        if (!seen.contains(nei)) {
                            seen.add(nei);
                            queue.offer(nei);
                        }
                    }
                }
            }
        }
        System.out.println(depth);
        System.out.println(num);
    }
}
