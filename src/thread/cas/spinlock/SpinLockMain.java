package thread.cas.spinlock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class SpinLockMain {

    public static void main(String[] args){
        SpinLock spinLock = new SpinLock();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                spinLock.lock();
                try {
                    // critical section
                    log("비즈니스 로직 실행");
                    sleep(1); // 오래 걸리는 로직에서 스핀 락 사용 X
                } finally {
                    spinLock.unlock();
                }
            }
        };

        Thread th1 = new Thread(runnable, "Thread-1");
        Thread th2 = new Thread(runnable, "Thread-2");

        th1.start();
        th2.start();
    }
}
