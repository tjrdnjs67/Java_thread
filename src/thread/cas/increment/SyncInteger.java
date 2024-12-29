package thread.cas.increment;

/*
  synchronized 블록에 진입하기 전에 CPU 캐시 메모리와 메인 메모리 값을 동기화하여 가시성을 해결함 따라서 volatile 필요 X
*/

public class SyncInteger implements IncrementInteger{

    private int value;

    @Override
    public synchronized void increment() {
        value++;
    }

    @Override
    public synchronized int get() {
        return value;
    }
}
