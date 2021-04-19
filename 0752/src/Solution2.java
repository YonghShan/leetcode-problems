import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author YonghShan
 * @date 2/24/21 - 16:33
 */
class Solution2 {
    // 依旧是BFS逐层遍历，但是并没有取每一层的size进行for循环，而是人为在每一层最后一个元素后加个null，表示一层的结束
    // 这样使得当target因为deadens的限制而无法到达时，即使需要遍历几乎全部的node也可以很快结束，不会导致TLE
    // 可以通过Test2感受下当无法到达target时，Solution2所需要的遍历时间，相比于test，直接秒结束
    /* Runtime: 150ms O(N^2*A^N+D) where A is the number of digits in our alphabet, N is the number of digits in the lock, and D is the size of deadends.
                                   O(D) for initializing deadends set
       一共有10^4个node             O(digits^N): There are 10 x 10 x 10 x 10 possible combinations => 10^4 => A^N
       为每个node找邻居要N^2         O(N^2) for enumerating neighbors: For each combination, we are looping 4 times (which is N) and in each iteration,
                                                                     there are substring operations ( which is O(N) * constant) => O(4N*constant) => O(4N) => O(NN) => O(N^2)
       Memory: 47.4MB  O(A^N+D), for the queue and the set dead.
     */
    public int openLock(String[] deadends, String target) {
        Set<String> dead = new HashSet();
        for (String d: deadends) dead.add(d);

        Queue<String> queue = new LinkedList();
        queue.offer("0000");
        queue.offer(null);

        Set<String> seen = new HashSet();
        seen.add("0000");

        int depth = 0;
        while (!queue.isEmpty()) {
            String node = queue.poll();
            if (node == null) {
                depth++;
                if (queue.peek() != null)
                    queue.offer(null);
            } else if (node.equals(target)) {
                return depth;
            } else if (!dead.contains(node)) {
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
        return -1;
    }
}

