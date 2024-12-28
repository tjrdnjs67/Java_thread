package thread.control.join;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class JoinMainV3 {

    public static void main(String[] args) throws InterruptedException {
        log("Start");

        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);

        Thread thread1 = new Thread(task1, "thread-1");
        Thread thread2 = new Thread(task2, "thread-2");

        thread1.start();
        thread2.start();

        // 스레드1이 종료될 때까지 대기
         thread1.join();

         // 스레드2가 종료될 때까지 대기
         thread2.join();

        log("task1.result = " + task1.result);
        log("task2.result = " + task2.result);

        log("task sum = " + (task1.result + task2.result));
        log("End");
    }

    static class SumTask implements Runnable {

        private int startValue;
        private int endValue;
        private int result;

        public SumTask(int startValue, int endValue){
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public void run() {
            log("작업 시작");
            sleep(2000);
            for(int i = startValue; i <= endValue; i++){
                result += i;
            }
            log("작업 완료 result = " + result);
        }
    }
}
