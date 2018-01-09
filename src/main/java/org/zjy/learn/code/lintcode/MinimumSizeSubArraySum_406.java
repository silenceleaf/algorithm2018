package org.zjy.learn.code.lintcode;


import org.zjy.learn.util.Application;

import java.util.logging.LogManager;
import java.util.logging.Logger;

@Application(time = "01/08/2018 19:36")
public class MinimumSizeSubArraySum_406 implements Runnable {
    private Logger logger = LogManager.getLogManager().getLogger("algorithm_lean");

    @Override
    public void run() {
        logger.info(String.valueOf(minimumSize(new int[]{2,3,1,2,4,3}, 7)));
    }

    public int minimumSize(int[] nums, int s) {
        // write your code here
        int j = 0, i = 0;
        int sum =0;
        int ans = Integer.MAX_VALUE;
        for(i = 0; i < nums.length; i++) {
            while(j < nums.length && sum < s) {
                sum += nums[j];
                j ++;
            }
            if(sum >=s) {
                ans = Math.min(ans, j - i);
            }
            sum -= nums[i];
        }
        if(ans == Integer.MAX_VALUE)
            ans = -1;
        return ans;
    }

    /**
     * wrong solution
     * @param nums
     * @param s
     * @return
     */
    public int minimumSize_wrong(int[] nums, int s) {
        // write your code here
        int minStart = 0;
        int minEnd = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        int sum = 0;
        while (end < nums.length && start <= end) {
            if (sum < s) {
                sum += nums[end];
                end++;
            } else {
                if (minEnd - minStart > end - start) {
                    minStart = start;
                    minEnd = end;
                }
                sum -= nums[start];
                ++start;
            }
        }
        return minEnd - minStart;
    }
}
