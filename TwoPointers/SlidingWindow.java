package TwoPointers;

/**
 * 1. 使用双指针中的左右指针技巧，初始化 left = right = 0，把索引闭区间 [left, right] 称为一个「窗口」。
 * 2. 先不断地增加 right 指针扩大窗口 [left, right]，直到窗口符合要求
 * 3. 停止增加 right，转而不断增加 left 指针缩小窗口 [left, right]，直到窗口中的字符串不再符合要求。同时，每次增加 left，我们都要更新一轮结果。
 * 4. 重复第 2 和第 3 步，直到 right 到达尽头。
 *
 *
 *
 * int left = 0, right = 0;    // 左右指针
 *
 * while (right < s.size()) {  // 右指针遍历直到边界
 *     window.add(s[right]);   // 右元素进窗
 *     right++;                // 右指针移动
 *
 *     while (valid(window) && left < right) {    // 窗口满足条件(优化窗口)
 *
 *         // update d here if finding minimum
 *         // increase begin to make it invalid/valid again
 *
 *         window.remove(s[left]); // 左元素出窗
 *         left++;                 // 左指针移动；直到窗口不满足条件
 *     }
 *
 *     // update d here if finding maximum
 * }
 */
public class SlidingWindow {

    /**
     * Given a string S and a string T, find the minimum window in S which will contain all the characters in T.
     * Input: S = "ADOBECODEBANC", T = "ABC"
     * Output: "BANC"
     *
     * We use cnt to count unique chars in T.
     * If cnt == 0, we have collected all chars in the window.
     */
    public String minWindow(String s, String t) {
        int m = s.length(), n = t.length();
        int[] map = new int[128];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (map[t.charAt(i)] == 0) {
                cnt++;
            }
            map[t.charAt(i)]++;
        }
        int min = m + 1;
        int l = -1, r = -1;
        for (int i = 0, j = 0; i < m; i++) {
            map[s.charAt(i)]--;
            if (map[s.charAt(i)] == 0) {
                cnt--;
            }
            while (j <= i && cnt == 0) {            // narrow the window
                if (min > i - j + 1) {
                    min = i - j + 1;
                    l = j; r = i;
                }
                if (map[s.charAt(j)] == 0) {
                    cnt++;
                }
                map[s.charAt(j)]++;
                j++;
            }
        }
        return min == m + 1 ? "" : s.substring(l, r + 1);
    }
}
