package thread.bounded;

import java.util.ArrayList;
import java.util.List;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BoundedMain {

    public static void main(String[] args){
        // 1. BoundedQueue 선택
        BoundedQueue queue = new BoundedQueueV6_4(2);

        // 2. 생산자, 소비자 실행순서 선택 반드시 하나만 선택!
        // produceFirst(queue); // 생산자 먼저 실행
         consumerFirst(queue); // 소비자 먼저 실행
    }

    private static void consumerFirst(BoundedQueue queue) {
        log("== [소비자 먼저 실행] 시작, " + queue.getClass().getSimpleName() + " ==");

        List<Thread> threads = new ArrayList<>();
        startConsumer(queue, threads);
        printAllStates(queue, threads);
        startProducer(queue, threads);
        printAllStates(queue, threads);

        log("== [소비자 먼저 실행] 종료, " + queue.getClass().getSimpleName() + " ==");
    }

    private static void produceFirst(BoundedQueue queue) {
        log("== [생산자 먼저 실행] 시작, " + queue.getClass().getSimpleName() + " ==");

        List<Thread> threads = new ArrayList<>();
        startProducer(queue, threads);
        printAllStates(queue, threads);
        startConsumer(queue, threads);
        printAllStates(queue, threads);

        log("== [생산자 먼저 실행] 종료, " + queue.getClass().getSimpleName() + " ==");
    }

    private static void startConsumer(BoundedQueue queue, List<Thread> threads) {
        System.out.println();
        log("소비자 시작");
        for(int i = 0; i < 3; i++){
            Thread consumer = new Thread(new ConsumerTask(queue), "ConsumerThread" + i);
            threads.add(consumer);
            consumer.start();
            sleep(100);
        }
    }

    private static void startProducer(BoundedQueue queue, List<Thread> threads) {
        System.out.println();
        log("생산자 시작");
        for(int i = 0; i < 3; i++){
            Thread producer = new Thread(new ProducerTask(queue,"data"+i), "ProducerThread" + i);
            threads.add(producer);
            producer.start();
            sleep(100);
        }
    }

    private static void printAllStates(BoundedQueue queue, List<Thread> threads) {
        System.out.println();
        log("현재 상태 출력, 큐 데이터 : " + queue);
        for (Thread thread : threads) {
            log(thread.getName() + " : "  + thread.getState());
        }
    }



}
