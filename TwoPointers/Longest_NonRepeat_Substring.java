package TwoPointers;

import java.util.Map;

/**
 * 双指针是很多算法的基础，如归并排序、滑动窗口、字符匹配（左右括号匹配）等
 * 在很多情况下，双指针能帮助我们找到空间或是时间复杂度更低的解。比单个指针来回移动的效率从 O(n^2) 提高到 O(n)
 *
 * for (int i = 0, j = 0; i < n; i ++ ) {
 *     while (j < i && check(i, j)) j ++ ;
 *     // 具体问题的逻辑
 * }
 *
 * 最长不重复字串
 * 给定一个长度为n的字符串，请找出最长的不包含重复字母的连续区间，输出它的长度。
 * abbcd -> 3
 *
 * 单调性：当考虑区间[j, i]时，i++, 若出现了重复字母，j 一定是右移的，直到字母不重复
 */
public class Longest_NonRepeat_Substring {

    public int longestNonRepeatSubstring(char[] chars) {
        int n = chars.length;
        int[] cnt = new int[26];

        int res = 0;
        for (int i = 0, j = 0; i < n; i++) {
            cnt[chars[i] - 'a']++;            // cnt + 1
            while (j <= i && cnt[chars[i] - 'a'] > 1) {
                cnt[chars[j] - 'a']--;
                j++;
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }

}
