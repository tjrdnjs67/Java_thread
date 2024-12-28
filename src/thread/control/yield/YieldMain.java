package thread.control.yield;

import static util.ThreadUtils.sleep;

public class YieldMain {

    static final int THREAD_COUNT = 1000;

    public static void main(String[] args){
        for(int i = 0; i < THREAD_COUNT; i++){
            Thread thread = new Thread(new MyRunnable(), "Thread" + i);
            thread.start();
        }
    }

    static class MyRunnable implements Runnable{


        @Override
        public void run() {
            for(int i = 0; i < 10; i++){
                System.out.println(Thread.currentThread().getName() + "-" + i);
                // empty : 1
                // sleep(1) : 2
                sleep(1);
                // Thread.yield() : 3
            }

        }
    }
}
