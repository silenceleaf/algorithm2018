package org.zjy.learn.code.amazon;

import org.zjy.learn.util.Application;

@Application(time = "06/17/2019 15:08")
public class FindSongs implements Runnable {
    /**
     * Two sum. 要求找到sum是target-30，并且其中一首歌曲是符合要求的pairs里单首最长的pair
     * 一个坑，随时update结果pair，保存在一个variable里。因为如果两首歌一样长并且这两首诗最终答案，如果只keep合格的最长的歌曲，最后再retrieve index，会导致这种test case出错。
     */
    @Override
    public void run() {

    }

    private int[] findSongs(int[] songs) {
        return null;
    }
}
