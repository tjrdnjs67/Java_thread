package thread.cas.spinlock;

import java.util.concurrent.atomic.AtomicBoolean;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

/*
  SpinLock의 경우 락을 기다리는 스레드가 BLOCKED, WAITING 상태로 빠지지 않지만 RUNNABLE 상태로 락을 획들할 때까지 while문을 반복
  락을 기다리는 스레드가 CPU를 계속 사용하면서 대기하고 있는 것
  비즈니스 로직이 오래 걸리는 경우에는 CAS가 적합하지 않음
*/

public class SpinLock {

    private final AtomicBoolean lock = new AtomicBoolean(false);

    public void lock(){
        log("락 획득 시도");

        while(!lock.compareAndSet(false, true)){
            // 락을 획득할 때까지 스핀 대기(바쁜 대기) 한다.
            log("락 획득 실패 - 스핀 대기");
        }

        log("락 획득 완료");
    }

    public void unlock(){
        lock.set(false);
        log("락 반납 완료");
    }
}
