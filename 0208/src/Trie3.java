import java.util.Arrays;

/**
 * @author YonghShan
 * @date 7/10/21 - 01:29
 */

// 利用二维数组实现Trie
/* Runtime: 56ms ~ 104ms       O(words以及prefixes的总长度)
   Memory: 65.8MB ~ 76.1MB     O(1e5)
 */
class Trie3 {
    // 以下 static 成员独一份，被创建的多个 Trie 共用  ([0421]中涉及多次创建Trie)
    static int N = 100009; // 直接设置为十万级
    static int[][] trie = new int[N][26];
    static int[] count = new int[N];
    static int index = 0;

    // 在构造方法中完成重置 static 成员数组的操作
    // 这样做的目的是为减少 new 操作（无论有多少测试数据，上述 static 成员只会被 new 一次）
    public Trie3() {
        for (int row = index; row >= 0; row--) { // 注意这里row=index，是因为index指示上一次Trie用了多少行
            Arrays.fill(trie[row], 0);
        }
        Arrays.fill(count, 0);
        index = 0;
    }

    public void insert(String s) {
        int p = 0;
        for (int i = 0; i < s.length(); i++) {
            int u = s.charAt(i) - 'a';
            if (trie[p][u] == 0) trie[p][u] = ++index;  // 要嵌套插入新的TrieNode，而此时第一个还未使用的TrieNode位于二维数组中的哪一行由++index指定
            p = trie[p][u];
        }
        count[p]++;
    }

    public boolean search(String s) {
        int p = 0;
        for (int i = 0; i < s.length(); i++) {
            int u = s.charAt(i) - 'a';
            if (trie[p][u] == 0) return false;
            p = trie[p][u];
        }
        return count[p] != 0;
    }

    public boolean startsWith(String s) {
        int p = 0;
        for (int i = 0; i < s.length(); i++) {
            int u = s.charAt(i) - 'a';
            if (trie[p][u] == 0) return false;
            p = trie[p][u];
        }
        return true;
    }
}
