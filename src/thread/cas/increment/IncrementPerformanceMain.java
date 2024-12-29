package thread.cas.increment;

/*
  AtomicInteger 가 제공하는 incrementAndGet() 메서드는 락을 사용하지 않고 원자적 연산을 만들어냄
  그래서 synchronized, Lock ( ReentrantLock ) 을 사용하는 경우보다 1.5 ~ 2 배 정도 빠름
*/

public class IncrementPerformanceMain {

    public static final long COUNT =100_000_000;

    public static void main(String[] args){
        test(new BasicInteger());
        test(new VolatileInteger());
        test(new SyncInteger());
        test(new MyAtomicInteger());
    }

    private static void test(IncrementInteger incrementInteger){
        long startMs = System.currentTimeMillis();

        for(long i = 0; i < COUNT; i++){
            incrementInteger.increment();
        }

        long endMs = System.currentTimeMillis();
        System.out.println(incrementInteger.getClass().getSimpleName() + " : " + (endMs - startMs) + "ms");
    }
}
