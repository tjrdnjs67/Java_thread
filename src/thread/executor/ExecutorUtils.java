package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import static util.MyLogger.log;

public abstract class ExecutorUtils {

    public static void printState(ExecutorService executorService){
        if(executorService instanceof ThreadPoolExecutor poolExecutor){  // == ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor)executorService
            int pool = poolExecutor.getPoolSize();  // 쓰레드풀에서 생성된 쓰레드 갯수
            int active = poolExecutor.getActiveCount(); // 쓰레드풀에서 작업하고 있는 쓰레드 갯수
            int queuedTasks = poolExecutor.getQueue().size(); // 큐에 대기중인 작업 수
            long completedTasks = poolExecutor.getCompletedTaskCount(); // 완료된 작업 수
            log("[pool=" + pool + ", active=" + active + ", queuedTasks=" + queuedTasks + ", completedTasks=" + completedTasks);
        } else {
            log(executorService);
        }

    }
}
