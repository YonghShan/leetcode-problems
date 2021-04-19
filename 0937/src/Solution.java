import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author YonghShan
 * @date 1/22/21 - 00:16
 */

class Solution {
    public String[] reorderLogFiles(String[] logs) {
        ArrayList<String> letLogs = new ArrayList<>();
        ArrayList<String> digLogs = new ArrayList<>();

        //Divide the input into two ArrayLists
        //One for letter logs, and then sort according to the rules
        //One for digit logs, no need to sort, just append it at the end of the sorted letLogs

        /*
         The identifiers can begin with any letter, not only l for letter logs and d for digit logs
                for (String log : logs) {
                    if (log.charAt(0) == 'l') {
                        letLogs.add(log);
                    } else {
                        digLogs.add(log);
                    }
                }
         */

        //Split the String in the logs into String[] based on space
        //Classify the input based on the content of the second element in the String[]
        for (String log : logs) {
            String[] word = log.split(" ");
            if (Character.isLetter(word[1].charAt(0))) {
                letLogs.add(log);
            } else {
                digLogs.add(log);
            }
        }

        //Use Comparator to define the sort rules of letLogs
        //Ordered lexicographically ignoring identifier, with the identifier used in case of ties.
        Comparator<String> com = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                //Positioned to the beginning of the content (behind the identifier) by locating to the first space
                int index1 = o1.indexOf(" ");
                int index2 = o2.indexOf(" ");

                int result = o1.substring(index1 + 1).compareTo(o2.substring(index2 + 1));
                if (result < 0) {
                    //no need to swa
                    return -1;
                } else if (result == 0) {
                    /*  Attention!!
                        Since the mechanism of Collections.sort() is quick sort,
                        the output may not be maintained as the original order.
                        That is why we need to separate the filter (result <= 0)
                        to make sure the output keep lexicographical even if the contents are same.
                     */
                    //Cannot only use the last number of identifier to make the decision
                    //Because the identifiers can be a2 and g1
                    if (o1.substring(0, index1).compareTo(o2.substring(0, index2)) <= 0) {
                        return -1;
                    } else {
                        return 1;
                    }
                } else {
                    return 1;
                }
            }
        };

        Collections.sort(letLogs, com);
        letLogs.addAll(digLogs);

        return letLogs.toArray(new String[0]);
    }
}