package thread.cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

/*
  CAS(Compare-And-Swap)는 락을 사용하지 않지만 스레드가 값을 먼저 증가해서 문제가 발생할 것 같은 경우 루프를 돌며 재시도하는 방식으로 문제를 해결
  단, 충돌이 빈번하게 발생하는 환경에서는 성능에 문제가 될 수 있음
*/

public class CasMainV3 {

    private static final int THREAD_COUNT = 2;

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start value = " + atomicInteger.get());

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                incrementAndGet(atomicInteger);
            }
        };

        List<Thread> threads = new ArrayList<>();

        for(int i = 0; i < THREAD_COUNT; i++){
            Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }

        for(Thread thread : threads){
            thread.join();
        }

        int result = atomicInteger.get();
        System.out.println(atomicInteger.getClass().getSimpleName() + ", result : " + result);

    }

    private static int incrementAndGet(AtomicInteger atomicInteger) {
        int getValue;
        boolean result;
        do{
            getValue = atomicInteger.get(); // 0
            sleep(100); // 스레드 동시 실행을 위한 대기
            log("getValue : " + getValue);
            result = atomicInteger.compareAndSet(getValue, getValue + 1);
            log("result : " + result);
        }while(!result);

        return getValue + 1;
    }
}
