/**
 * @author YonghShan
 * @date 7/19/21 - 12:01
 */
public class Solution1 {
    // Trie
    /* Runtime: 1ms (faster than 63.41%)    O(S+P), where S is the total length of all str in strs together and P is the length of the potential longest prefix
       Memory: 37.1MB (less than 70.48%)    O(S) for TrieNodes
     */
    class TrieNode {
        private TrieNode[] node;
        private int childNodesNum;

        public TrieNode() {
            node = new TrieNode[26];
            childNodesNum = 0;
        }
    }

    class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }


        public void insert(String s) {
            TrieNode p = root;
            for (int i = 0; i < s.length(); i++) {
                int idx = s.charAt(i) - 'a';
                if (p.node[idx] == null) {
                    p.node[idx] = new TrieNode();
                    p.childNodesNum++;
                }
                p = p.node[idx];
            }
        }

        public String findLongestPrefix() {
            TrieNode p = root;
            StringBuilder sb = new StringBuilder();

            while (p.childNodesNum == 1) {
                int idx = 0;
                for (int i = 0; i < 26; i++) {
                    if (p.node[i] != null) idx = i;
                }
                sb.append((char)(idx + 'a'));
                p = p.node[idx];
            }

            return sb.toString();
        }
    }

    public String longestCommonPrefix(String[] strs) {
        Trie trie = new Trie();

        // 假设strs为["a", "ab"]，则调用findLongestPrefix()的结果为"ab"，但实际结果应为"a"
        // 为防止这种情况，在遍历strs时，记录长度最小的str
        int shortestLen = Integer.MAX_VALUE;
        String shortestStr = "";
        for (String str : strs) {    // O(S), where S is the total length of all str in strs together
            if (str.length() == 0) return ""; // 只要strs中有str为""，则return "";
            if (str.length() < shortestLen) {
                shortestLen = str.length();
                shortestStr = str;
            }
            trie.insert(str);
        }

        // the longest prefix的长度不可能比shortestLen大
        String res= trie.findLongestPrefix();   // O(P), where P is the length of the potential longest prefix
        return res.length() > shortestLen ? shortestStr : res;
    }
}
