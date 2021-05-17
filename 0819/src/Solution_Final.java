import java.util.*;

/**
 * @author YonghShan
 * @date 5/17/21 - 00:49
 */
public class Solution_Final {
    public String mostCommonWord(String paragraph, String[] banned) {
        //Step1: 对传进来的文本预处理（包括：1.将标点符号替换为空格；2.全部变成小写）
        String normalizedParagragh = paragraph.replaceAll("\\!|\\?|\\'|\\,|\\;|\\.", " ").toLowerCase();

        //Step2: 对文本进行分割
        String[] wordList = normalizedParagragh.split(" ");

        //Step3: 统计frequency
        Set<String> bannedWords = new HashSet();
        for (String word : banned) bannedWords.add(word);

        HashMap<String, Integer> record = new HashMap<>();
        for (String word : wordList) {
            if (word != null && word.length()>0) { //防止有连续的空格，导致输入进来的不是word（而是空格）
                if (!bannedWords.contains(word)) record.put(word, record.getOrDefault(word, 0)+1);
            }
        }

        //Step4: Select the word with highest frequency
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>(record.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String,Integer>>() {
            @Override
            public int compare(Map.Entry<String,Integer> o1, Map.Entry<String,Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        return list.get(0).getKey();
    }
}
