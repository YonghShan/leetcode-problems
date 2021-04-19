/**
 * @author YonghShan
 * @date 1/26/21 - 16:04
 */
class Solution5_2 {
    //Solution 5_2: Manacher Algorithm
    /* Runtime: 8ms
       Memory: 39M
     */
    public String longestPalindrome(String s) {
        int len = s.length();

        //特判
        if (len < 2) {
            return s;
        }

        // 得到预处理字符串
        String str = addBoundaries(s, '#');
        // 新字符串的长度
        int sLen = 2 * len + 1;

        // 数组 p 记录了扫描过的回文子串的信息
        int[] p = new int[sLen];

        // 双指针，它们是一一对应的，须同时更新
        int maxRight = 0;
        int center = 0;

        // 当前遍历的中心最大扩展步数，其值等于原始字符串的最长回文子串的长度
        int maxLen = 1; //maxLen设为0，就不需要上面的特判，对runtime影响不大
        // 原始字符串的最长回文子串的起始位置，与 maxLen 必须同时更新
        int start = 0;

        for (int i = 0; i < sLen; i++) {
            if (i < maxRight) {
                int mirror = 2 * center - i;
                // 这一行代码是 Manacher 算法的关键所在，要结合笔记来理解
                //不把i<maxRight && p[mirror]=maxRight-i这种情况单独拿出，而是选择给p[i]赋一个可能的最小值，是为了减少之后中心扩展的步数
                p[i] = Math.min(maxRight - i, p[mirror]);
            } //可以加个else，将p[i] = 0，也可不加，java数组默认赋0

            /* 接下来的中心扩展，不仅是对当i=maxRight时的处理，
               也是对当i<maxRight && p[mirror]=maxRight-i，p[i]被赋了一个可能的最小值时，p[i]的更新
               **见笔记** 不把i<maxRight && p[mirror]=maxRight-i这种情况单独拿出，而是选择
             */
            // 下一次尝试扩展的左右起点，能扩展的步数直接加到 p[i] 中
            int left = i - p[i] - 1;
            int right = i + p[i] + 1;

            // left >= 0 && right < sLen 保证不越界
            // str.charAt(left) == str.charAt(right) 表示可以扩展 1 次
            while (left >= 0 && right < sLen && str.charAt(left) == str.charAt(right)) {
                p[i]++;
                left--;
                right++;
            }

            // 根据 maxRight 的定义，它是遍历过的 i 中 i + p[i] 的最大者
            // 如果 maxRight 的值越大，进入上面 i < maxRight 的判断的可能性就越大，这样就可以重复利用之前判断过的回文信息了
            // center和maxRight并不是表示最终LPS的回文中心和臂展，只是用来求得所有p[i]值的辅助工具
            // LPS的选择还是要靠下面找出p[i]的最大值后截取子串来实现
            if (i + p[i] > maxRight) {
                // maxRight 和 center 需要同时更新
                maxRight = i + p[i];
                center = i;
            }

            // 找出 P 的最大值
            if (p[i] > maxLen) {
                // 记录最长回文子串的长度和相应它在原始字符串中的起点
                maxLen = p[i];
                start = (i - maxLen) / 2;
            }
        }
        return s.substring(start, start + maxLen);
    }

    /**
     * 创建预处理字符串
     *
     * @param s      原始字符串
     * @param divide 分隔字符
     * @return 使用分隔字符处理以后得到的字符串
     */
    private String addBoundaries(String s, char divide) {
        int len = s.length();
        if (len == 0) {
            return "";
        }
        if (s.indexOf(divide) != -1) {
            throw new IllegalArgumentException("参数错误，您传递的分割字符，在输入字符串中存在！");
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            stringBuilder.append(divide);
            stringBuilder.append(s.charAt(i));
        }
        stringBuilder.append(divide);
        return stringBuilder.toString();
    }
}
