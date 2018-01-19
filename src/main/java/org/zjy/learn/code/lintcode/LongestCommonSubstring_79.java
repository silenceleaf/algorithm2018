package org.zjy.learn.code.lintcode;

import org.zjy.learn.util.Application;

/**
 * Created by Junyan Zhang on 1/9/2018.
 */

@Application(time = "01/09/2018 23:11")
public class LongestCommonSubstring_79 implements  Runnable {
    @Override
    public void run() {
        longestCommonSubstring("abccccccccccde", "dbccccccabccde");
        //longestCommonSubstring("abccccccccccde", "dbccccccabccde");
    }

    public int longestCommonSubstring(String A, String B) {
        int n = A.length();
        int m = B.length();
        int max = 0;
        // function: f[i][j] = f[i - 1][j - 1] + 1 or 0
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int current = 0;
                if (A.charAt(i) == B.charAt(j)) {
                    while (i + current < n && j + current < m && A.charAt(i + current) == B.charAt(j + current))
                        current++;
                    max = Math.max(max, current);
                }
            }
        }
        return max;
    }
}
