package org.zjy.learn.code.my;

import org.zjy.learn.util.Application;
import org.zjy.learn.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by junyan zhang on 6/16/18.
 */

@Application(time = "06/16/2018 12:25")
public class SplitString implements Runnable {
	@Override
	public void run() {
		List<List<String>> result = new ArrayList<>();
		backtracking(result, new ArrayList<>(), "123456");
		Util.printList(result);
	}

	void backtracking(List<List<String>> result, List<String> current, String s) {
		if (s.length() == 0) {
			result.add(new ArrayList<>(current));
			return;
		}
		for (int i = 1; i <= s.length(); i++) {
			current.add(s.substring(0, i));
			backtracking(result, current, s.substring(i));
			current.remove(current.size() - 1);
		}
	}


}
