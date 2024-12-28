package thread.sync.lock;

import java.util.concurrent.locks.LockSupport;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class LockSupportMainV1 {

    public static void main(String[] args){
        Thread thread1 = new Thread(new ParkTest(), "Thread-1");
        thread1.start();

        // 잠시 대기하여 thread1이 park 상태에 빠질 시간을 준다.
        sleep(100);
        log("thread1-state : " + thread1.getState());

        log("main -> unpark(thread1)");
         LockSupport.unpark(thread1);  // 1. unpack 사용
        //thread1.interrupt(); // 2. interrupt() 사용

    }

    static class ParkTest implements Runnable{

        @Override
        public void run() {
            log("park 시작");
            LockSupport.park();
            log("park 종료, state : " + Thread.currentThread().getState());
            log("interrupt 상태 : " + Thread.currentThread().isInterrupted());
        }
    }
}
