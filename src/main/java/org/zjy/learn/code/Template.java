package org.zjy.learn.code;

import org.zjy.learn.util.Application;

/**
 * Created by Junyan Zhang on 12/14/2017.
 */

@Application(time = "12/14/2017 21:44")
public class Template implements Runnable {
    @Override
    public void run() {
        System.out.println("hello! (from template class)");
    }
}
