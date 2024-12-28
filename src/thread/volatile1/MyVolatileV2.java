package thread.volatile1;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class MyVolatileV2 {

    public static void main(String[] args){

        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        log("Flag = " + task.Flag);
        thread.start();

        sleep(1000);
        log("Flag false로 변경 시도");

        task.Flag = false;
        log("Flag = " + task.Flag);
        log("main 종료");
    }

    static class MyTask implements Runnable {

        // 성능을 살짝 포기하더라도 데이터를 읽거나 변경할 때 메인메모리에 직접 접근한다.
        // V1의 경우 캐시 메모리에서 데이터를 변경하여서 work 스레드의 Flag 값이 변경되지 않았다.
        volatile boolean Flag = true;

        @Override
        public void run() {
            log("Task 시작");
            while(Flag){
                // Flag가 false로 변하면 탈출
            }
            log("Task 종료");
        }
    }
}
