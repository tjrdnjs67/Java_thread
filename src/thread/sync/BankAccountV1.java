package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV1 implements BankAccount{

    private int balance;

    public BankAccountV1(int balance){
        this.balance = balance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("거래 시작 : " + getClass().getSimpleName());
        // 잔고가 출금액보다 작으면, 진행 x
        if(balance < amount){
            return false;
        }

        log("[검증 완료] 출금액 : " + amount + ", 잔액 : " + balance);
        sleep(1000);// 출금에 걸리는 시간
        balance = balance - amount;
        log("[출금 완료] 출금액 : " + amount + ", 잔액 : " + balance);

        log("거래 종료");
        return true;
    }

    @Override
    public int getBalance() {
        return balance;
    }
}
