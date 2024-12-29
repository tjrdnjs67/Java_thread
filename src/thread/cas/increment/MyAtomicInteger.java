package thread.cas.increment;

import java.util.concurrent.atomic.AtomicInteger;

public class MyAtomicInteger implements IncrementInteger{

    AtomicInteger atomicInteger = new AtomicInteger(0); // 초기값 설정 가능 생략하면 0 부터 시작

    @Override
    public void increment() {
        atomicInteger.incrementAndGet(); // 값을 증가시킨 후 반환
    }

    @Override
    public int get() {
        return atomicInteger.get(); // 현재 값을 반환
    }
}
