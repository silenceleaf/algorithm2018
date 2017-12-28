package org.zjy.learn.code.lintcode;

import org.zjy.learn.util.Application;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Created by junyan zhang on 12/25/17.
 */

@Application(time = "12/26/2017 10:00")
public class Permutations_15 implements Runnable {
	private Logger logger = LogManager.getLogManager().getLogger("algorithm_lean");

	@Override
	public void run() {
		List<List<Integer>> result = permute(new int[]{1, 2, 3});
		for (List<Integer> out : result) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("[");
			for (Integer value: out) {
				stringBuilder.append(value).append(",");
			}
			stringBuilder.append("]");
			logger.info(stringBuilder.toString());
		}
	}

	/**
	 * wrong method. 没有列举全部情况
	 *
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> permute_wrong(int[] nums) {
		// write your code here
		List<List<Integer>> result = new ArrayList<>();
		ArrayList<Integer> self = new ArrayList<Integer>(nums.length);
		for (int number : nums) {
			self.add(number);
		}
		result.add(self);
		for (int i = 0; i < nums.length-1; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				ArrayList<Integer> copy = new ArrayList<>(self);
				int temp = copy.get(i);
				copy.set(i, copy.get(j));
				copy.set(j, temp);
				result.add(copy);
			}
		}
		return result;
	}

	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		helper(nums, 0, result);
		return result;
	}

	public void helper(int[] nums, int from, List<List<Integer>> result) {
		if (from == nums.length) {
			ArrayList<Integer> p1 =  new ArrayList<>();
			for (int n : nums)
				p1.add(n);
			result.add(p1);
		} else {
			for (int i = from; i < nums.length; i++) {
				swap(nums, from, i);
				helper(nums, from + 1, result);
				swap(nums, from, i);
			}
		}
	}

	public void swap(int[] array, int from, int to) {
		if (from == to)
			return;
		int temp = array[from];
		array[from] = array[to];
		array[to] = temp;
	}


}
