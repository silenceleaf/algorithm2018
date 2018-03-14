package org.zjy.learn.code.kailin;

import org.zjy.learn.util.Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Created by Junyan Zhang on 1/20/2018.
 */

@Application(time = "01/27/2018 13:55")
public class Cow implements  Runnable {
    private Logger logger = LogManager.getLogManager().getLogger("algorithm_lean");

    @Override
    public void run() {
        splitCows(new int[]{1,2,3,4}, 5);
    }

    public void splitCows(int[] nums, int target) {
        List<Integer[]> currentRow = new ArrayList<>();
        List<List<Integer[]>> result = new ArrayList<>();

        helper(nums, 0, 7, currentRow, result);

        // output
        if (result.size() == 0) {
            logger.info("No qualified answer");
        } else {
            for (List<Integer[]> solution : result) {
                StringBuilder stringBuilder = new StringBuilder();
                for (Integer[] group : solution) {
                    stringBuilder.append(Arrays.toString(group));
                    stringBuilder.append(";");
                }
                stringBuilder.delete(stringBuilder.length()-1, stringBuilder.length());
                logger.info(stringBuilder.toString());
            }
        }
    }

    private void helper(int nums[], int startIndex, int target, List<Integer[]> current, List<List<Integer[]>> result) {
        int count = 0;
        for (Integer[] row : current)
            count += row.length;

        if (count == nums.length) {
            result.add(new ArrayList<Integer[]>(current));
            return;
        }

        for (int i = startIndex; i < nums.length; i++) {
             int sum = 0;
             for (int j = startIndex; j <= i; j++) {
                 sum += nums[j];
             }
             if (sum < target) {
                 current.add( Arrays.stream(Arrays.copyOfRange(nums, startIndex, i + 1)).boxed().toArray(Integer[]::new));
                 helper(nums, startIndex+1, target, current, result);
                 current.remove(current.size() - 1);
             } else {
                 continue;
             }
        }
    }
}
