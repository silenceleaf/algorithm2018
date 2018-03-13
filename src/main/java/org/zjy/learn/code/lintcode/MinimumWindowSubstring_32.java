package org.zjy.learn.code.lintcode;

import org.zjy.learn.util.Application;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Created by junyan zhang on 12/28/17.
 */

@Application(time = "02/13/2018 10:15")
public class MinimumWindowSubstring_32 implements Runnable {
	private Logger logger = LogManager.getLogManager().getLogger("algorithm_lean");

	@Override
	public void run() {
		logger.info("answer: " + minWindow("abc", "a"));
	}

	/**
	 * wrong answer, count string is not working. such as "aab" and "bba"
	 * 双指针，动态维护一个区间。尾指针不断往后扫，当扫到有一个窗口包含了所有T的字符后，然后再收缩头指针，直到不能再收缩为止。最后记录所有可能的情况中窗口最小的
	 * @param source
	 * @param target
	 * @return
	 */
	public String minWindow(String source , String target) {
		// write your code here
		int ans = Integer.MAX_VALUE;
		String minStr = "";

		int[] targetHash = new int[256];
		for (char ch : target.toCharArray()) {
			targetHash[ch]++;
		}
		int targetCount = target.length();
		int sourceCount = 0;
		int begin = 0;
		for(int i = 0; i < source.length(); i++) {
			if(targetHash[source.charAt(i)] > 0)
				sourceCount++;

			targetHash[source.charAt(i)]--;
			while(sourceCount >= targetCount) {
				if(ans > i - begin + 1) {
					ans = Math.min(ans, i - begin + 1);
					minStr = source.substring(begin, i + 1);
				}
				targetHash[source.charAt(begin)]++;
				if(targetHash[source.charAt(begin)] > 0)
					sourceCount--;

				begin++;
			}
		}
		return minStr;
	}

}
