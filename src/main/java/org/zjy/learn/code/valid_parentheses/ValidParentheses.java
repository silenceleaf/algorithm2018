package org.zjy.learn.code.valid_parentheses;

import org.zjy.learn.util.Application;

import java.util.logging.LogManager;
import java.util.logging.Logger;


@Application(time = "12/19/2017 12:30")
public class ValidParentheses implements Runnable {
    private Logger logger = LogManager.getLogManager().getLogger("algorithm_lean");
    private String[] testCases = {"()", "(*)", "(*))", "(((*))", "(((*))))"};

    @Override
    public void run() {
        for (String test : testCases) {
            logger.info(String.valueOf(parentheses(test)));
        }
    }

    private boolean parentheses(String input) {
        int l = 0, cp = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                l++;
                cp++;
            } else if (input.charAt(i) == '*') {
                if (l > 0)
                    l--;

                cp++;
            } else if (input.charAt(i) == ')') {
                if (l > 0)
                    l--;
                cp--;

                if (cp < 0)
                    return false;
            } else {
                return false;
            }
        }
        return l == 0;
    }
}

