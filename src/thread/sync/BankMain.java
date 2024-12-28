package thread.sync;

import static util.MyLogger.log;

public class BankMain {

    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccountV6(1000);

        Thread t1 = new Thread(new WithdrawTask(account, 800), "T1");
        Thread t2 = new Thread(new WithdrawTask(account, 800), "T2");

        t1.start();
        t2.start();

        //t1.join();
        //t2.join();

        log("최종 잔액 : " + account.getBalance());
    }
}
