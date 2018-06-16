package org.zjy.learn.code.my;

import org.zjy.learn.util.Application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by junyan zhang on 6/16/18.
 */

@Application(time = "06/16/2018 13:15")
public class WordPatternII implements Runnable {
	@Override
	public void run() {
		//wordPatternMatch2("abab", "cccdddcccddd");
		wordPatternMatch2("aaa", "helloworldhelloworldhelloworld");
	}

	// My own methord
	public boolean wordPatternMatch2(String pattern, String str) {
		List<List<String>> result = new ArrayList<>();
		backtracking(result, new ArrayList<>(), str, pattern);
		return !result.isEmpty();
	}

	private void backtracking(List<List<String>> result, List<String> current, String str, String pattern) {
		if (str.length() == 0) {
			if (validWordPattern(pattern, current))
				result.add(new ArrayList<>(current));
			return;
		}
		for (int i = 1; i <= str.length(); i++) {
			current.add(str.substring(0, i));
			backtracking(result, current, str.substring(i), pattern);
			current.remove(current.size() - 1);
		}
	}

	private boolean validWordPattern(String pattern, List<String> words) {
		if (pattern.length() != words.size())
			return false;

		String[] patternedWords = new String[256];
		Set<String> set = new HashSet<String>();
		for (int i = 0; i < words.size(); i++) {
			char c = pattern.charAt(i);
			if (patternedWords[(int)c] == null) { //new pattern
				patternedWords[(int)c] = words.get(i);
				if (set.contains(words.get(i)))
					return false;
				set.add(words.get(i));
			} else {
				if (!patternedWords[(int)c].equals(words.get(i)))
					return false;
			}
		}
		return true;
	}
}
