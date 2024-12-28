package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV3 implements BankAccount{

    private int balance;

    public BankAccountV3(int balance){
        this.balance = balance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("거래 시작 : " + getClass().getSimpleName());
        
        /* 임계영역 시작 */
        synchronized(this) {
            // 잔고가 출금액보다 작으면, 진행 x
            if (balance < amount) {
                log("잔고가 부족합니다..");
                return false;
            }

            log("[검증 완료] 출금액 : " + amount + ", 잔액 : " + balance);
            sleep(1000);// 출금에 걸리는 시간
            balance = balance - amount;
            log("[출금 완료] 출금액 : " + amount + ", 잔액 : " + balance);
        }
        /* 임계영역 끝 */

        log("거래 종료");
        return true;
    }

    @Override
    public synchronized int getBalance() {
        return balance;
    }
}
