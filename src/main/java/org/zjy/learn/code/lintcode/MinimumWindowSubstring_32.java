package org.zjy.learn.code.lintcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by junyan zhang on 12/28/17.
 */
public class MinimumWindowSubstring_32 {
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
		int end = 0;
		while (start < source.length()) {
			if (!targetHash.contains(source.charAt(start))) {
				start++;
			} else {
				pointer = start;
				while (pointer < source.length()) {
					if (targetHash.contains(source.charAt(pointer))) {
						++get;
						if (get == target.length()) {
							if (pointer - start > end - begin) {
								begin = start;
								end = pointer;
							}
							get = 0;
							break;
						}
					}
					pointer++;
				}
			}
		}
		return source.substring(begin, end + 1);
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
