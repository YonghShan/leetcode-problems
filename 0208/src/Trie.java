/**
 * @author YonghShan
 * @date 7/9/21 - 23:45
 */

// 利用TrieNode实现Trie
/* Runtime: 38ms ~ 93ms      O(words以及prefixes的总长度)
   Memory: 49MB ~ 64.1MB     结点数量为n，字符集大小为k（=26）。复杂度为O(nk)
 */
class Trie {
    class TrieNode {
        private TrieNode[] node;
        private final int n = 26;
        private boolean isEnd;

        public TrieNode() {
            node = new TrieNode[n];
        }
    }

    private TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {            // O(m) & O(m), m == word.length()
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int idx = c - 'a';
            if (p.node[idx] == null) p.node[idx] = new TrieNode();
            p = p.node[idx];
        }
        p.isEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {            // O(m) & O(1), m == word.length()
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int idx = c - 'a';
            if (p.node[idx] == null) return false;
            p = p.node[idx];
        }
        return p.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {            // O(m) & O(1), m == prefix.length()
        TrieNode p = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            int idx = c - 'a';
            if (p.node[idx] == null) return false;
            p = p.node[idx];
        }
        return true;
    }
}
