package org.zjy.learn.code.longest_increasing_sequence;

import org.zjy.learn.util.Application;

/**
 * Created by Junyan Zhang on 1/7/2018.
 */

@Application(time = "01/07/2018 20:26")
public class LongestIncreaseOrDecrease implements Runnable {
    @Override
    public void run() {
        int[] nums = new int[] {5, 4, 10};
        //int[] nums = new int[] {10, 4, 3, 8, 3, 4, 5, 7, 7, 2};
        longest(nums);
    }

    public int longest(int[] nums) {
        // 1 increase; -1 decrease; 0 even
        int[] helper = new int[nums.length - 1];
        for (int i = 0; i < helper.length; i++) {
            if (nums[i+1] > nums[i]) {
                helper[i] = 1;
            } else if (nums[i+1] < nums[i]) {
                helper[i] = -1;
            } else {
                helper[i] = 0;
            }
        }
        int begin = 0;
        int end = 0;
        int maxBegin = 0;
        int maxEnd = 0;
        for (int i = 1; i < helper.length; i++) {
            if (helper[i] == helper[i-1] || helper[i] == 0) {
                end = i;
            } else {
                if (end - begin > maxEnd - maxBegin) {
                    maxBegin = begin;
                    maxEnd = end;
                }
                begin = i;
                end = i;
            }
        }
        int sum = 0;
        for (int i = maxBegin; i < maxEnd + 1; i++) {
            sum += nums[i];
        }
        return sum;
    }
}
