import java.util.*;

/**
 * @author YonghShan
 * @date 1/21/21 - 20:24
 */

class Solution {
    /* Runtime: 13ms (faster than 59.85%)
       Memory: 39.7MB (less than 9.17%)
     */
    public String mostCommonWord(String paragraph, String[] banned) {
        //Step1: 对传进来的文本预处理（包括：1.将标点符号替换为空格；2.全部变成小写）
        String normalizedParagragh = paragraph.replaceAll("\\!|\\?|\\'|\\,|\\;|\\.", " ").toLowerCase();   // 双斜杠转义
        // String normalizedParagragh = paragraph.replaceAll("[^a-zA-Z0-9 ]", " ").toLowerCase();

        //Step2: 对文本进行分割
        String[] wordList = normalizedParagragh.split(" ");

        //Step3: 统计frequency
        HashMap<String, Integer> record = new HashMap<>();
        for (String word : wordList) {
            if (word != null && word.length()>0) { //防止有连续的空格，导致输入进来的不是word（而是空格）
                if (!Arrays.asList(banned).contains(word)) { //判断是否为banned words    // 有点蠢，asList大概率takes O(m), contains takes O(m), for循环n次，则takes O(nm), 不如提前用banned建立一个HashSet
//                    if (record.containsKey(word)) { //如果record中已经有这个word的记录
//                        int times = record.get(word);
//                        record.put(word, times+1); //对于HashMap来说，相同Key的值传进来，直接改变对应的value，相当于update
//                    } else {
//                        record.put(word, 1);
//                    }

                    record.put(word, record.getOrDefault(word, 0)+1);
                }
            }
        }

        //Step4: Select the word with highest frequency
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>(record.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String,Integer>>() {
            @Override
            public int compare(Map.Entry<String,Integer> o1, Map.Entry<String,Integer> o2) {
                // return (-1) * o1.getValue().compareTo(o2.getValue());  //前边不加负号变为升序
                return o2.getValue() - o1.getValue();
            }
        });

        //Collections.sort(list, Comparator.comparing(Map.Entry::getValue));  //排序结果错误

        return list.get(0).getKey();

        //return Collections.max(record.entrySet(), Map.Entry.comparingByValue()).getKey(); //Step4和return合并
    }
}
