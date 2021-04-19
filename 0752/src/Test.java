import java.util.*;

/**
 * @author YonghShan
 * @date 2/24/21 - 12:49
 */
public class Test {
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

        Queue<String> q = new LinkedList<>();
        List<String> visited = new ArrayList<>();
        q.add("0000");
        visited.add("0000");
        int times = -1;
        int[] counts = new int[100];
        int num = 0;
        while (!q.isEmpty()) {
            times++;
            //System.out.println(times);
            //System.out.println(q.size());
            int count = q.size();
            counts[times] = count;
            for (int i = 0; i < count; i++) {
                String node = q.poll();
                num++;
                if (node.equals("0002")) {
                    //System.out.println("FIND!!!!!!!!!!!!!");
                    //System.out.println(num);
                    break;
                } else if (!dead.contains(node)){
                    // 各位+1/-1：
                    for (int j = 0; j < 4; j++) {
                        int x = node.charAt(j) - '0'; // 如果char在'0'～'9'之间，可以通过这种方法将其转变为int
                        // +1：
                        x = (x == 9) ? 0 : x+1;
                        String neighbor1 = node .substring(0, j) + ("" + x) + node.substring(j+1);
                        if (!visited.contains(neighbor1)) {
                            //System.out.println(neighbor1);
                            q.add(neighbor1);
                            visited.add(neighbor1);
                        }
                        // -1: 注意这里x刚+1，原本的-1要变为-2
                        x = (x == 0) ? 8 : x-2;
                        if (x == -1) x = 9;
                        String neighbor2 = node .substring(0, j) + ("" + x) + node.substring(j+1);
                        if (!visited.contains(neighbor2)) {
                            //System.out.println(neighbor2);
                            q.add(neighbor2);
                            visited.add(neighbor2);
                        }
                    }
                }
                //System.out.println("-----------");
            }
            //System.out.println("+++++++++++");
        }
        System.out.println(times);
        //System.out.println(Arrays.toString(counts));
        System.out.println(num);
    }
}
