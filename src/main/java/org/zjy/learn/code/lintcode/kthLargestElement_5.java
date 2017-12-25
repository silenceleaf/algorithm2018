package org.zjy.learn.code.lintcode;

import org.zjy.learn.util.Application;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Created by junyan zhang on 12/24/17.
 */

@Application(time = "12/24/2017 21:25")
public class kthLargestElement_5 implements Runnable {
	private Logger logger = LogManager.getLogManager().getLogger("algorithm_lean");

	@Override
	public void run() {
		logger.info(String.valueOf("answer:" +  kthLargestElement(4, new int[]{1,2,3,4,5,6,7,8,9})));
	}

	public int kthLargestElement(int k, int[] nums) {
		// write your code here
		PriorityQueue<Integer> heap = new PriorityQueue<>(nums.length, Collections.reverseOrder());
		for (int n : nums) {
			heap.add(n);
		}
		int num = 0;
		for (int i = 0; i < k; i++) {
			num = heap.poll();
		}
		return num;
	}
}
