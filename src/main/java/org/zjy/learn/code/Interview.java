package org.zjy.learn.code;


import org.zjy.learn.util.Application;

@Application(time = "03/07/2018 16:00")
public class Interview implements Runnable {
    @Override
    public void run() {
        System.out.println("hello! (from template class)");
    }


}
