package thread.control;

import static util.MyLogger.*;

public class ThreadStateMain {

    public static void main(String[] args){
        Thread thread = new Thread(new MyRunnable(), "myThread");

        log("myThread.state1 = " + thread.getState());
        log("myThread.start()");

        thread.start();
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            log("start");
        }
    }
}
