package org.zjy.learn.code.lintcode;

import org.zjy.learn.util.Application;

@Application(time = "01/26/2018 11:53")
public class JumpGame_116 implements Runnable {
    @Override
    public void run() {
        canJump(new int[]{1,0});
    }

    public boolean canJump(int[] A) {
        int [] ways = new int[A.length];
        ways[0] = 1;
        for (int i = 0; i < A.length; i++) {
            if (ways[i] > 0) {
                for (int j = 1; j <= A[i] && i + j < ways.length; j++) {
                    ++ways[i+j];
                }
            }
        }
        return ways[A.length-1] > 0;
    }
}
