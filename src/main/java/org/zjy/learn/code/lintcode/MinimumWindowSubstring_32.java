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

@Application(time = "12/28/2017 12:40")
public class MinimumWindowSubstring_32 implements Runnable {
	private Logger logger = LogManager.getLogManager().getLogger("algorithm_lean");

	@Override
	public void run() {
		logger.info("answer: " + minWindow("absdfaabab", "adb"));
	}

	/**
	 * wrong answer, count string is not working. such as "aab" and "bba"
	 * @param source
	 * @param target
	 * @return
	 */
	public String minWindow(String source , String target) {
		// write your code here
		Set<Character> targetHash = new HashSet<>();
		for (int i = 0; i < target.length(); i++) {
			targetHash.add(target.charAt(i));
		}
		int get = 0;
		int pointer = 0;
		int start = 0;
		int begin = 0;
		int end = Integer.MAX_VALUE;
		while (start < source.length()) {
			if (targetHash.contains(source.charAt(start))) {
				pointer = start;
				while (pointer < source.length()) {
					if (targetHash.contains(source.charAt(pointer))) {
						++get;
						if (get == target.length()) {
							if (pointer - start < end - begin) {
								begin = start;
								end = pointer;
							}
							break;
						}
					}
					pointer++;
				}
			}
			get = 0;
			start++;
		}
		if (end != Integer.MAX_VALUE)
			return source.substring(begin, end + 1);
		else
			return "";
	}

	public Map<Character, Integer> initHash(String str) {
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < str.length(); i++) {
			if (map.containsKey(str.charAt(i))) {
				map.put(str.charAt(i), map.get(str.charAt(i)) + 1);
			} else {
				map.put(str.charAt(i), 0);
			}
		}
		return map;
	}
}
