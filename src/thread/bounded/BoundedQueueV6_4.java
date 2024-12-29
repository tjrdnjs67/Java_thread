package thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static util.MyLogger.log;

/*
 Throws Exception - 만약 큐가 가득차거나 비어있어서 대기해야하는 상황이 발생하면 기다리지 않고 바로 예외를 발생시킴
    BlockingQueue.add(e) : 큐가 가득차면 IllegalStateException 예외를 던짐
    BlockingQueue.remove() : 큐가 비어 있으면 NoSuchElementException 예외를 던짐

 Special Value - 만약 큐가 가득차거나 비어있어서 대기해야하는 상황이 발생하면 즉시 반환
    BlockingQueue.offer(e) : 큐가 가득차면 false 반환
    BlockingQueue.poll() : 큐가 비어 있으면 null 반환
    BlockingQueue.peek() : 큐의 머리 요소 반환, 요소를 큐에서 제거하지 않음, 큐가 비어있으면 null 반환

 Blocks - 대기
    BlockingQueue.put(e) : 큐가 가득차면 공간이 생길 때까지 대기
    BlockingQueue.take() : 큐가 비어 있으면 요소가 준비될 때까지 대기

 Times Out - 시간 대기
    BlockingQueue.offer(e, time, unit) : 큐가 가득차면 대기하다가 시간이 초과되면 false 반환
    BlockingQueue.poll(time, unit) : 큐가 비어 있으면 대기하다가 시간이 초과되면 null 반환

    ex) offer(data, 1, TimeUnit.NANOSECONDS)
        poll(2, TimeUnit.SECONDS)
 */

public class BoundedQueueV6_4 implements BoundedQueue{

    private BlockingQueue<String> queue;

    public BoundedQueueV6_4(int max){
        this.queue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
        queue.add(data); // java.lang.IllegalStateException : Queue full
    }

    @Override
    public String take() {
        return queue.remove(); // java.util.NoSuchElementException
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
