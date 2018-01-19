package org.zjy.learn.code.lintcode;

import org.zjy.learn.util.Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Junyan Zhang on 1/18/2018.
 */

@Application(time = "01/18/2018 23:34")
public class CombinationSum_135 implements Runnable {

    @Override
    public void run() {
        combinationSum(new int[]{1}, 1);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(candidates == null || candidates.length == 0){
            return result;
        }
        Arrays.sort(candidates);

        //helper(candidates, 0, target, new ArrayList<>(), result);
        helper2(candidates, 0, 0, target, new ArrayList<>(), result);
        return result;
    }

    private void helper(int[] nums, int startIndex, int remainTarget, List<Integer> combination, List<List<Integer>> result){
        if(remainTarget == 0){
            List<Integer> row = new ArrayList<>(combination);
            Collections.sort(row); // 不sort答案通不过
            result.add(row);
            return;
        }
        for(int i = startIndex; i < nums.length; i++){
            if(i != 0 && nums[i] == nums[i - 1]){
                continue;
            }
            if (remainTarget < nums[i]) {
                break;
            }
            combination.add(nums[i]);
            helper(nums, i, remainTarget - nums[i], combination, result);
            combination.remove(combination.size() - 1);
        }
    }

    private void helper2(int[] nums, int startIndex, int sum, int target, List<Integer> combination, List<List<Integer>> result) {
        if(sum == target) {
            List<Integer> row = new ArrayList<>(combination);
            Collections.sort(row); // 不sort答案通不过
            result.add(row);
            return;
        }
        for(int i = startIndex; i < nums.length; i++){
            if(i != 0 && nums[i] == nums[i - 1]){
                continue;
            }
            if (sum + nums[i] > target) {
                break;
            }
            combination.add(nums[i]);
            helper2(nums, i, sum + nums[i], target, combination, result);
            combination.remove(combination.size() - 1);
        }
    }
}
