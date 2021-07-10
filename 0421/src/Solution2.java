/**
 * @author YonghShan
 * @date 7/10/21 - 15:51
 */
public class Solution2 {
    // Trie + Greedy Algorithm   最佳
    /* Runtime: 114ms (faster than 58.10%)    O(n)
       Memory: 58.6MB (less than 53.36%)      O(n) for Trie
     */
    class TrieNode {
        TrieNode[] node = new TrieNode[2];   // 不同于[0421]中每一个TrieNode有26种可能，此处只有2种可能：0和1
    }

    TrieNode root = new TrieNode();

    // 向Trie中插入数据
    void add(int x) {
        TrieNode p = root;
        for (int i = 31; i >= 0; i--) {
            int u = (x >> i) & 1;   // 取x的第i位值
            if (p.node[u] == null) p.node[u] = new TrieNode();
            p = p.node[u];
        }
    }

    // 在Trie中找出与数x每位都尽可能相反的数（对于某一位，若无相反值，则跳过）
    // 假设x=3=(00011)_2，则找寻最高位为1，次高位为1，第三位为1，第四位为0，第五位为0的数
    // 如果Trie中所有数的第三位都为0，则继续找寻第四位为0的数
    int getOppoVal(int x) {
        int ans = 0;
        TrieNode p = root;
        for (int i = 31; i >= 0; i--) {
            int a = (x >> i) & 1, b = 1 - a;
            if (p.node[b] != null) { // 对于第i位，存在相反值
                ans |= (b << i);
                p = p.node[b];
            } else { // 对于第i位，不存在相反值
                ans |= (a << i);
                p = p.node[a];
            }
        }
        return ans;
    }

    public int findMaximumXOR(int[] nums) {
        int ans = 0;
        for (int i : nums) {
            add(i);
            int j = getOppoVal(i); // j为Trie中从最高位开始，每位都尽可能与数i相反的数 ==>> 故i^j的结果才会尽可能的大
            ans = Math.max(ans, i ^ j);
        }
        return ans;
    }
}
