package thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

/*
  자바는 AtomicXxx 의 compareAndSet() 메서드를 통해 CAS 연산을 지원
  compareAndSet(0, 1) atomicInteger 가 가지고 있는 값이 0이면 이 값을 1로 변경하라는 메우 단순한 메서드
  이 메서드는 원자적으로 실행됨
*/

public class CasMainV1 {

    public static void main(String[] args){
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start value = " + atomicInteger.get());

        boolean result1 = atomicInteger.compareAndSet(0, 1);
        System.out.println("result1 = " + result1 + ", value = " + atomicInteger.get());

        boolean result2 = atomicInteger.compareAndSet(0, 1);
        System.out.println("result2 = " + result2 + ", value = " + atomicInteger.get());
    }
}
