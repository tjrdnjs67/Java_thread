package thread.control.printer;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class MyPrinterV3 {

    public static void main(String[] args){
        Printer printer = new Printer();
        Thread printerThread = new Thread(printer, "printer");

        printerThread.start();
        Scanner userInput = new Scanner(System.in);

        while(true){
            log("프린터 문서를 입력하세요 종료 버튼 (q) : ");
            String input = userInput.nextLine();
            if(input.equals("q")){
                printerThread.interrupt();
                break;
            }

            printer.addJob(input);
                
            }

        }

    static class Printer implements Runnable {

        Queue<String> jobQueue = new ConcurrentLinkedQueue<>();

        @Override
        public void run() {
            while(!Thread.interrupted()){

                if(jobQueue.isEmpty()){
                    Thread.yield(); // 추가
                    continue;
                }


                String job = jobQueue.poll();

                log("출력 시작 : " + job + " 대기 문서 : " + jobQueue);
                sleep(3000);
                log("출력 완료");

            }

            log("printer 종료");
        }

        public void addJob(String input){
            jobQueue.offer(input);
        }
    }
}
