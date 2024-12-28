package thread.sync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV6 implements BankAccount{

    private int balance;

    private final Lock lock = new ReentrantLock(true);

    public BankAccountV6(int balance){
        this.balance = balance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("거래 시작 : " + getClass().getSimpleName());

        try {
            if(!lock.tryLock(500, TimeUnit.MILLISECONDS)){
                log("[진입 실패] 이미 처리중인 작업이 있습니다.");
                return false;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        /* 임계영역 시작 */
        lock.lock(); // lock을 걸고나면 무조건 try-finally를 통해서 unlock을 해주자

        try{
            // 잔고가 출금액보다 작으면, 진행 x
            if (balance < amount) {
                log("잔고가 부족합니다..");
                return false;
            }

            log("[검증 완료] 출금액 : " + amount + ", 잔액 : " + balance);
            sleep(1000);// 출금에 걸리는 시간
            balance = balance - amount;
            log("[출금 완료] 출금액 : " + amount + ", 잔액 : " + balance);
        }finally {
            lock.unlock();
        }
        /* 임계영역 끝 */

        log("거래 종료");
        return true;
    }

    @Override
    public int getBalance() {
        lock.lock();
        try{
            return balance;
        }finally {
            lock.unlock();
        }

    }
}
