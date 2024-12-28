package thread.volatile1;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class MyVolatileV1 {

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

        boolean Flag = true;

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
