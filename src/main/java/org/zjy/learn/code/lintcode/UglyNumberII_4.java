package org.zjy.learn.code.lintcode;

import org.zjy.learn.util.Application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Created by junyan zhang on 12/24/17.
 */

@Application(time = "12/24/2017 16:46")
public class UglyNumberII_4 implements Runnable {
	private Logger logger = LogManager.getLogManager().getLogger("algorithm_lean");

	@Override
	public void run() {
		logger.info(String.valueOf("answer:" +  nthUglyNumber2(11)));
	}

	public int nthUglyNumber(int n) {
		// write your code here
		if (n < 0) {
			return 0;
		}
		HashSet<Long> set = new HashSet<>();
		PriorityQueue<Long> heap = new PriorityQueue<>();
		heap.add(1L);
		long num = 0;
		for (int i = 1; i <= n; i++) {
			num = heap.poll();
			logger.info(String.valueOf(i) + ": "+String.valueOf(num));
			if (!set.contains(2 * num)) {
				heap.add(2 * num);
				set.add(2 * num);
			}
			if (!set.contains(3 * num)) {
				heap.add(3 * num);
				set.add(3 * num);
			}
			if (!set.contains(5 * num)) {
				heap.add(5 * num);
				set.add(5 * num);
			}
		}
		return Math.toIntExact(num);
	}

	public int nthUglyNumber2(int n) {
		List<Integer> uglys = new ArrayList<Integer>();
		uglys.add(1);

		int p2 = 0, p3 = 0, p5 = 0;
		// p2, p3 & p5 share the same queue: uglys
		for (int i = 1; i < n; i++) {
			int lastNumber = uglys.get(i - 1);
			while (uglys.get(p2) * 2 <= lastNumber) p2++;
			while (uglys.get(p3) * 3 <= lastNumber) p3++;
			while (uglys.get(p5) * 5 <= lastNumber) p5++;
			uglys.add(Math.min(
					Math.min(uglys.get(p2) * 2, uglys.get(p3) * 3), uglys.get(p5) * 5
			));
		}
		return uglys.get(n - 1);
	}
}
