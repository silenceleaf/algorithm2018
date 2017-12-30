package org.zjy.learn.code.lintcode;

import org.zjy.learn.util.Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Created by Junyan Zhang on 12/30/2017.
 */

@Application(time = "12/30/2017 16:48")
public class MajorityNumberIII_48 implements Runnable {
    private Logger logger = LogManager.getLogManager().getLogger("algorithm_lean");

    @Override
    public void run() {
        ArrayList<Integer> input = new ArrayList<>();
        for (int i : new int[]{3,1,2,3,2,3,3,4,4,4}) {
            input.add(i);
        }
        logger.info("answer: " + majorityNumber(input, 3));
    }

    public int majorityNumber(List<Integer> nums, int k) {
        // write your code here
        int[] candidate = new int[k-1];
        Arrays.fill(candidate, Integer.MIN_VALUE);
        int[] frequency = new int[k-1];
        Arrays.fill(frequency, 0);

        for (int i = 0; i < nums.size(); i++) {
            boolean found = false;
            int available = Integer.MIN_VALUE;
            for (int j = 0; j < k - 1; j++) {
                if (nums.get(i) == candidate[j]) {
                    frequency[j]++;
                    found = true;
                    break;
                }
                if (frequency[j] <= 0) {
                    available = j;
                    frequency[j] = 0;
                    candidate[j] = Integer.MIN_VALUE;
                }
            }
            if (!found) {
                if (available >= 0) {
                    candidate[available] = nums.get(i);
                    frequency[available] = 1;
                } else {
                    for (int j = 0; j < k-1; j++) {
                        frequency[j]--;
                    }
                }
            }
        }

        int maxFrequency = 0;
        int maxFrequencyNum = Integer.MIN_VALUE;
        for (int i = 0; i < k - 1; i++) {
            frequency[i] = 0;
            for (int j = 0; j < nums.size(); j++) {
                if (nums.get(j) == candidate[i]) {
                    frequency[i]++;
                    if (frequency[i] > maxFrequency) {
                        maxFrequency = frequency[i];
                        maxFrequencyNum = candidate[i];
                    }
                }
            }
        }
        return maxFrequencyNum;
    }
}
