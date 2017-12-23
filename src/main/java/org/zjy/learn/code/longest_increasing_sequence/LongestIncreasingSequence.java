package org.zjy.learn.code.longest_increasing_sequence;

/**
 * Created by Junyan Zhang on 12/20/2017.
 */
public class LongestIncreasingSequence {

    public int findNumberOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            ans[i] = 1;
        }
        int max_ans=1;
        for (int i=1; i<nums.length; i++)
        {
            for (int j=0; j<i; j++)
                if (nums[j]<nums[i] && dp[j]+1>dp[i]) {
                    dp[i]=dp[j]+1;
                    ans[i]=ans[j];
                }
            if (dp[i]>max_ans) {
                max_ans=dp[i];
            }
        }
        int count = 0;
        for (int i = 0; i < nums.length; i++){
            if (dp[i] == max_ans)
                count += ans[i];
        }
        return count;
    }
}
