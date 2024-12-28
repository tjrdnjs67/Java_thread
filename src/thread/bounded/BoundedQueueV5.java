package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;

public class BoundedQueueV5 implements BoundedQueue{

    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;

    private final Lock lock = new ReentrantLock();
    private final Condition produceCond = lock.newCondition();
    private final Condition consumeCond = lock.newCondition();

    public BoundedQueueV5(int max){
        this.max = max;
    }

    @Override
    public void put(String data) {
        lock.lock();
        try{
            while(queue.size() == max){
                log("[put] 큐가 가득참, 버림 : " + data );
                try {
                    produceCond.await(); // wait X 조심,
                    log("[put] 생산자 깨어남");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }

            queue.offer(data);
            log("[put] 생산자 데이터 저장, notify 호출");
            consumeCond.signal();
        }finally {
            lock.unlock();
        }
    }

    @Override
    public String take() {
        lock.lock();
        try{
            while(queue.isEmpty()){
                log("[take] 큐에 데이터가 없음, 소비자 대기");
                try {
                    consumeCond.await(); // wait X 조심,
                    log("[take] 소비자 깨어남");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            String data = queue.poll();
            log("[take] 소비자 데이터 획득, notify 호출");
            produceCond.signal();
            return data;
        }finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}